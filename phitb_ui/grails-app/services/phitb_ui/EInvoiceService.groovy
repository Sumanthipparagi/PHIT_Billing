package phitb_ui

import grails.gorm.transactions.Transactional
import org.bouncycastle.util.encoders.Base64Encoder
import org.glassfish.jersey.logging.LoggingFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.einvoice.AESEncryption
import phitb_ui.einvoice.EinvoiceHelper
import phitb_ui.einvoice.NICEncryption
import phitb_ui.einvoice.NicV4TokenPayloadGen

import javax.servlet.http.HttpSession
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Feature
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.SimpleDateFormat
import java.util.logging.Level
import java.util.logging.Logger;

@Transactional
class EInvoiceService {
    private static JSONObject entityIrnDetails = new JSONObject()

    private generateSignatureAndAuthToken(HttpSession session) {
        try {
            SimpleDateFormat tokenDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            //String entityId = session.getAttribute("entityId").toString()
            String entityId = "1"
            entityIrnDetails = new EntityService().getEntityIrnByEntity(entityId)
            //don't proceed if inactive
            if (!entityIrnDetails.get("active")) {
                return null
            }
            boolean isAuthTokenValid = false
            if (entityIrnDetails && entityIrnDetails.has("authToken")) {
                Date currentDate = new Date()
                Date authTokenExpiryDate = tokenDateFormat.parse(entityIrnDetails.get("tokenExpiry").toString())
                if (currentDate.before(authTokenExpiryDate))
                    isAuthTokenValid = false
            }
            if (!isAuthTokenValid) {
                String aspId = Constants.E_INVOICE_ASP_ID;
                String ts = new EinvoiceHelper().getCurrTs();
                System.out.println("AspId : " + aspId);
                System.out.println("TimeStamp : " + ts);
                String aspData = aspId + ts;
                String sign = ""
                try {
                    sign = new EinvoiceHelper().generateSignature(aspData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = new JSONObject()
                jsonObject.put("timestamp", ts)
                jsonObject.put("signed_content", sign)
                Logger logger = Logger.getLogger(getClass().getName());
                Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
                Client client = ClientBuilder.newClient();
                client.register(feature)
                WebTarget target = client.target(new Links().E_INVOICE_GET_KEY)
                try {
                    Response apiResponse = target
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .header("aspid", new Constants().E_INVOICE_ASP_ID)
                            .header("message-id", ts)
                            .header("filler1", "")
                            .header("filler2", "")
                            .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
                    if (apiResponse.status == 200) {
                        JSONObject sessionData = new JSONObject(apiResponse.readEntity(String.class))
                        JSONObject authToken = generateAuthToken(sessionData)
                        return authToken
                    } else
                        return null
                }
                catch (Exception ex) {
                    System.err.println('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
                    log.error('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
                    return null
                }
            } else {
                return entityIrnDetails
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
            return null
        }
    }

    private generateAuthToken(JSONObject jsonObject) {
        //To encrypt auth-token payload payload
        String randomAppKey = Base64.getEncoder().encodeToString(new EinvoiceHelper().createAESKey());
        String base64EncodedAppKey = Base64.getEncoder().encodeToString(randomAppKey.getBytes());

        JSONObject authPayload = new JSONObject()
        authPayload.put("UserName", entityIrnDetails.get("irnUsername"))
        authPayload.put("Password", entityIrnDetails.get("irnPassword"))
        authPayload.put("AppKey", base64EncodedAppKey)
        authPayload.put("ForceRefreshAccessToken", true)

        String base64EncodedPayload = Base64.getEncoder().encodeToString(authPayload.toString().getBytes());
        byte[] b = new NicV4TokenPayloadGen().readFile("C:\\Users\\arjun\\Desktop\\publicKey.pem");
        NicV4TokenPayloadGen gen = new NicV4TokenPayloadGen(b);
        String encData = gen.encryptPayload(base64EncodedPayload);
        JSONObject finalPayLoad = new JSONObject()
        finalPayLoad.put("Data", encData)
        String encAspSecret = new AESEncryption().encryptAspSecret(jsonObject.get("enc_key").toString(), Constants.E_INVOICE_ASP_SECRET)
        Logger logger = Logger.getLogger(getClass().getName())
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        Client client = ClientBuilder.newClient();
        client.register(feature)
        WebTarget target = client.target(new Links().E_INVOICE_AUTH_TOKEN)
        try {
            Response apiResponse = target
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("aspid", new Constants().E_INVOICE_ASP_ID)
                    .header("asp_secret_key", encAspSecret)
                    .header("session_id", jsonObject.get("session_id"))
                    .header("gstin", entityIrnDetails.get("irnGSTIN"))
                    .post(Entity.entity(finalPayLoad.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject authToken = new JSONObject(apiResponse.readEntity(String.class))
                //update entityIrnDetails
                entityIrnDetails.put("sessionId", jsonObject.get("session_id").toString())
                entityIrnDetails.put("appKey", randomAppKey)
                entityIrnDetails.put("aspSecretKey", encAspSecret)
                entityIrnDetails.put("authToken", authToken.get("authtoken").toString())
                entityIrnDetails.put("sek", authToken.get("sek").toString())
                entityIrnDetails.put("tokenExpiry", authToken.get("tokenExp").toString())
                new EntityService().updateEntityIRN(entityIrnDetails)
                return entityIrnDetails
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
        }
    }

    def generateIRN(HttpSession session, JSONObject saleBillDetail, JSONArray saleProductDetails) {

        JSONObject authData = generateSignatureAndAuthToken(session)
        if (authData == null) {
            return
        }
        String invoiceId = saleBillDetail.get("id")
        String irnJson = buildIrnPayload(saleBillDetail, saleProductDetails)
        String appKey = authData.get("appKey")
        String sek = authData.get("sek")
        String encryptedPayload = new NICEncryption(appKey, sek).EncryptPayload(irnJson)

        JSONObject finalPayLoad = new JSONObject()
        finalPayLoad.put("Data", encryptedPayload)

        Logger logger = Logger.getLogger(getClass().getName())
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        Client client = ClientBuilder.newClient();
        client.register(feature)
        WebTarget target = client.target(new Links().E_INVOICE_GEN_IRN)
        try {
            Response apiResponse = target
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("aspid", new Constants().E_INVOICE_ASP_ID)
                    .header("asp_secret_key", authData.get("aspSecretKey"))
                    .header("session_id", authData.get("sessionId"))
                    .header("gstin", authData.get("irnGSTIN"))
                    .header("authtoken", authData.get("authToken"))
                    .header("username", authData.get("irnUsername"))
                    .post(Entity.entity(finalPayLoad.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject generatedIRN = new JSONObject(apiResponse.readEntity(String.class))
                if (generatedIRN) {
                    if (generatedIRN.get("Status").toString().equalsIgnoreCase("1")) {
                        def irnDetails = new NICEncryption(appKey, sek).DecryptResponse(generatedIRN.get("Data").toString())
                        Response apiResp = new SalesService().getSaleInvoiceById(invoiceId)
                        if (apiResp.status == 200) {
                            JSONObject salesInvoice = new JSONObject(apiResp.readEntity(String.class))
                            JSONObject irnDetailsJSON = new JSONObject()
                            irnDetailsJSON.put("id", salesInvoice.get("id"))
                            irnDetailsJSON.put("irnDetails", irnDetails)
                            new SalesService().updateSaleBillIRNDetails(irnDetailsJSON)
                        }
                    } else {
                        println(generatedIRN.get("ErrorDetails").toString())
                    }
                }
                return generatedIRN
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
        }
    }


    private static String buildIrnPayload(JSONObject saleBillDetail, JSONArray saleProductDetails) {
        String irnJson = null
        if (saleBillDetail && saleProductDetails) {
            JSONObject sellerDetails = new EntityService().getEntityById(saleBillDetail.get("entityId").toString())
            JSONObject buyerDetails = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())

            JSONObject sellerCity = new SystemService().getCityById(sellerDetails.get("cityId").toString())
            JSONObject sellerState = sellerCity.get("state") as JSONObject

            JSONObject buyerCity = new SystemService().getCityById(buyerDetails.get("cityId").toString())
            JSONObject buyerState = buyerCity.get("state") as JSONObject
            SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd")
            Date ordrDt = originalDateFormat.parse(saleBillDetail.get("orderDate").toString().split("T")[0])
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            String orderDate = sdf.format(ordrDt)
            JSONObject irnObject = new JSONObject()
            irnObject.put("Version", "1.1")

            //Transaction Details
            JSONObject TranDtls = new JSONObject()
            TranDtls.put("TaxSch", "GST")
            TranDtls.put("SupTyp", "B2B")
            TranDtls.put("RegRev", "Y")
            TranDtls.put("EcmGstin", null)
            TranDtls.put("IgstOnIntra", "N")
            irnObject.put("TranDtls", TranDtls)

            //Document Details
            JSONObject DocDtls = new JSONObject()
            DocDtls.put("Typ", "INV")
            DocDtls.put("No", saleBillDetail.get("invoiceNumber"))
            DocDtls.put("Dt", orderDate)
            irnObject.put("DocDtls", DocDtls)

            //Seller Details
            JSONObject SellerDtls = new JSONObject()
            //SellerDtls.put("Gstin", sellerDetails.get("gstn"))
            SellerDtls.put("Gstin", entityIrnDetails.get("irnGSTIN")) //TODO: to be removed in production
            SellerDtls.put("LglNm", sellerDetails.get("entityName"))
            SellerDtls.put("TrdNm", sellerDetails.get("entityName"))
            SellerDtls.put("Addr1", sellerDetails.get("addressLine1"))
            String sellerAddressLine2 = ""
            if (sellerDetails.get("addressLine2")?.toString()?.length() > 0)
                sellerAddressLine2 = sellerDetails.get("addressLine2")
            SellerDtls.put("Addr2", sellerAddressLine2)
            SellerDtls.put("Loc", sellerCity.get("name"))
            //SellerDtls.put("Pin", Long.parseLong(sellerDetails.get("pinCode").toString()))
            SellerDtls.put("Pin", 431132) //TODO: to be removed in production
            SellerDtls.put("Ph", sellerDetails.get("mobileNumber"))
            SellerDtls.put("Em", sellerDetails.get("email"))
            //SellerDtls.put("Stcd", sellerState.get("irnStateCode"))
            SellerDtls.put("Stcd", "27") //TODO: to be removed in production
            irnObject.put("SellerDtls", SellerDtls)

            //Buyer Details
            JSONObject BuyerDtls = new JSONObject()
            //BuyerDtls.put("Gstin", buyerDetails.get("gstn"))
            BuyerDtls.put("Gstin", "27AAACA4410D2ZD") //TODO: to be removed in production
            BuyerDtls.put("LglNm", buyerDetails.get("entityName"))
            BuyerDtls.put("TrdNm", buyerDetails.get("entityName"))
            BuyerDtls.put("Addr1", buyerDetails.get("addressLine1"))
            String buyerAddressLine2 = ""
            if (sellerDetails.get("addressLine2")?.toString()?.length() > 0)
                buyerAddressLine2 = buyerDetails.get("addressLine2")
            BuyerDtls.put("Addr2", buyerAddressLine2)
            BuyerDtls.put("Loc", buyerCity.get("name"))
          //  BuyerDtls.put("Pin", Long.parseLong(buyerDetails.get("pinCode").toString()))
            BuyerDtls.put("Pin", 431132)  //TODO: to be removed in production
            BuyerDtls.put("Ph", buyerDetails.get("mobileNumber"))
            BuyerDtls.put("Em", buyerDetails.get("email"))
           // BuyerDtls.put("Stcd", buyerState.get("irnStateCode"))
            BuyerDtls.put("Stcd", "27") //TODO: to be removed in production
           // BuyerDtls.put("Pos",  buyerState.get("irnStateCode")) //TODO: to be added
            BuyerDtls.put("Pos",  "27")  //TODO: to be removed in production
            irnObject.put("BuyerDtls", BuyerDtls)

            //Dispatch Details
            JSONObject DispDtls = new JSONObject()
            DispDtls.put("Nm", sellerDetails.get("entityName"))
            DispDtls.put("Loc", sellerCity.get("name"))
            DispDtls.put("Addr1", sellerDetails.get("addressLine1"))
            String dispDtlsAddressLine2 = ""
            if (sellerDetails.get("addressLine2")?.toString()?.length() > 0)
                dispDtlsAddressLine2 = sellerDetails.get("addressLine2")
            DispDtls.put("Addr2", dispDtlsAddressLine2)
           // DispDtls.put("Pin", Long.parseLong(sellerDetails.get("pinCode").toString()))
            DispDtls.put("Pin", 431132) //TODO: to be removed in production
           // DispDtls.put("Stcd", sellerState.get("irnStateCode"))
            DispDtls.put("Stcd", "27") //TODO: to be removed in production
            irnObject.put("DispDtls", DispDtls)

            //Ship Details
           /* JSONObject ShipDtls = new JSONObject()
            irnObject.put("ShipDtls", ShipDtls)*/

            //ITEM List
            JSONArray ItemList = new JSONArray()
            double TotAssVal = 0.00
            double TotCgstVal = 0.00
            double TotSgstVal = 0.00
            double TotIgstVal = 0.00
            double TotDiscount = 0.00
            double TotInvVal = 0.00
            int slNo = 1
            for (JSONObject saleProduct : saleProductDetails) {
                JSONObject product = new ProductService().getProductById(saleProduct.get("productId").toString())
                JSONObject item = new JSONObject()
                double sRate = Double.parseDouble(saleProduct.get("sRate").toString())
                Integer sqty = Integer.parseInt(saleProduct.get("sqty").toString())
                double totalAmount = UtilsService.round((sRate * sqty), 2)
                double assAmt = UtilsService.round(totalAmount - Double.parseDouble(saleProduct.get("discount").toString()), 2)
                double igst = UtilsService.round(Double.parseDouble(saleProduct.get("igstAmount").toString()), 2)
                double cgst = UtilsService.round(Double.parseDouble(saleProduct.get("cgstAmount").toString()), 2)
                double sgst = UtilsService.round(Double.parseDouble(saleProduct.get("sgstAmount").toString()), 2)
                double discount = UtilsService.round(Double.parseDouble(saleProduct.get("discount").toString()), 2)
                double amount = UtilsService.round(Double.parseDouble(saleProduct.get("amount").toString()), 2)
                TotAssVal += assAmt
                TotCgstVal += cgst
                TotIgstVal += igst
                TotSgstVal += sgst
                TotDiscount += discount
                TotInvVal += amount
                item.put("SlNo", slNo.toString())
                item.put("PrdDesc", product.get("productName"))
                item.put("IsServc", "N")
                item.put("HsnCd", product.get("hsnCode"))
                item.put("Qty", Integer.parseInt(saleProduct.get("sqty").toString()))
                item.put("FreeQty", Integer.parseInt(saleProduct.get("freeQty").toString()))
                item.put("UnitPrice", UtilsService.round(sRate, 2))
                item.put("Unit", "OTH")
                item.put("TotAmt", totalAmount)
                item.put("Discount", discount)
                item.put("AssAmt", assAmt)
                item.put("GstRt", UtilsService.round(Double.parseDouble(saleProduct.get("gstPercentage").toString()), 2))
                item.put("IgstAmt", igst)
                item.put("CgstAmt", cgst)
                item.put("SgstAmt", sgst)
                item.put("TotItemVal", amount)
                item.put("OrgCntry", sellerState.get("country")["irnCountryCode"])
                //item.put("Barcde", "")
                // item.put("Unit", "OTH")
                //item.put("PreTaxVal", "")
                //item.put("CesRt", "")
                //item.put("CesAmt", "")
                //item.put("CesNonAdvlAmt", "")
                //item.put("StateCesRt", "")
                //item.put("StateCesAmt", "")
                //item.put("StateCesNonAdvlAmt", "")
                //item.put("OthChrg", "")
                //item.put("OrdLineRef", "")
                //item.put("PrdSlNo", "")

                JSONObject BchDtls = new JSONObject()
                BchDtls.put("Nm", saleProduct.get("batchNumber"))
                //BchDtls.put("ExpDt", saleProduct.get("expiryDate"))
                //BchDtls.put("WrDt","")
                item.put("BchDtls", BchDtls)

                /* JSONObject AttribDtls = new JSONObject()
                 AttribDtls.put("Nm","")
                 AttribDtls.put("Val","")
                 item.put("AttribDtls", AttribDtls)*/

                ItemList.put(item)
                slNo++
            }
            irnObject.put("ItemList", ItemList)

            //Total Value Details
            JSONObject ValDtls = new JSONObject()
            ValDtls.put("AssVal", TotAssVal)
            ValDtls.put("CgstVal", TotCgstVal)
            ValDtls.put("SgstVal", TotSgstVal)
            ValDtls.put("IgstVal", TotIgstVal)
            ValDtls.put("TotInvVal", TotInvVal)
            ValDtls.put("Discount", TotDiscount)
            //ValDtls.put("CesVal", )
            // ValDtls.put("StCesVal", )
            //ValDtls.put("OthChrg", )
            //ValDtls.put("RndOffAmt", )
            //ValDtls.put("TotInvValFc", )
            irnObject.put("ValDtls", ValDtls)

            //convert to string
            irnJson = irnObject.toString()
        }
        return irnJson
    }

}

package phitb_ui

import grails.gorm.transactions.Transactional
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.glassfish.jersey.logging.LoggingFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.einvoice.AESEncryption
import phitb_ui.einvoice.EInvoiceTokenGenerator
import phitb_ui.einvoice.EinvoiceHelper
import phitb_ui.einvoice.NICEncryption
import phitb_ui.einvoice.NicV4TokenPayloadGen

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.servlet.http.HttpSession
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Feature
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Security
import java.text.SimpleDateFormat
import java.util.logging.Level
import java.util.logging.Logger;

@Transactional
class EInvoiceService {

    private static JSONObject entityIrnDetails = new JSONObject()

    private generateAuthToken(HttpSession session)
    {
        Security.addProvider(new BouncyCastleProvider());
        SimpleDateFormat tokenDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        String entityId = session.getAttribute("entityId").toString()
        entityIrnDetails = new EntityService().getEntityIrnByEntity(entityId)
        //don't proceed if null
        if (entityIrnDetails == null) {
            println("Entity IRN Details NULL")
            return null
        }
        //don't proceed if inactive
        if (!entityIrnDetails.get("active")) {
            println("Entity IRN Details Inactive")
            return null
        }
        boolean isAuthTokenValid = false
        if (entityIrnDetails && entityIrnDetails.has("authToken")) {
            Date currentDate = new Date()
            Date authTokenExpiryDate = tokenDateFormat.parse(entityIrnDetails.get("tokenExpiry").toString())
            if (currentDate.before(authTokenExpiryDate))
                isAuthTokenValid = false
        }
        if(!isAuthTokenValid) {
            String ts = new EinvoiceHelper().getCurrTs();
            String transId = entityId + new EInvoiceTokenGenerator().generateTransactionId()
            String authToken = "v2.0:" + Constants.E_INVOICE_ASP_ID + "::"+transId+":" + ts + ":" + entityIrnDetails.get("irnGSTIN") + ":EINV_GEN"
            //String authToken = "v2.0:" + Constants.E_INVOICE_ASP_ID + "::"+transId+":" + ts + ":27ABFPD4021L002:EINV_GEN"
            String signedToken = ""
            String publicKeyPath = Objects.requireNonNull(this.class.classLoader.getResource("KeyStore/test-publickey.pem")).getPath();
            String privateKeyPath = Objects.requireNonNull(this.class.classLoader.getResource("KeyStore/test-privatekey.pem")).getPath();
            PrivateKey privateKey = new EInvoiceTokenGenerator().readPemPrivateKey(privateKeyPath)
            PublicKey publicKey = new EInvoiceTokenGenerator().readPemPublicKey(publicKeyPath)
            try {
                signedToken = new EInvoiceTokenGenerator().signAndVerify(authToken,privateKey, publicKey)
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if(signedToken)
            {
                String base64EncodedAppKey = new EInvoiceTokenGenerator().generateAppKey()
                JSONObject authPayload = new JSONObject()
                authPayload.put("UserName", entityIrnDetails.get("irnUsername"))
                authPayload.put("Password", entityIrnDetails.get("irnPassword"))
                authPayload.put("AppKey", base64EncodedAppKey)
                authPayload.put("ForceRefreshAccessToken", false)
                String base64EncodedPayload = Base64.getEncoder().encodeToString(authPayload.toString().bytes)

                // Generate a 128-bit AES key
                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(128);
                SecretKey aesKey = keyGen.generateKey();

                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                byte[] cipherData = cipher.doFinal(base64EncodedPayload.bytes)
                String encData = Base64.getEncoder().encodeToString(cipherData)

                JSONObject finalPayLoad = new JSONObject()
                finalPayLoad.put("Data", encData)
                println(finalPayLoad.toString())

                Logger logger = Logger.getLogger(getClass().getName());
                Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
                Client client = ClientBuilder.newBuilder().register(feature).build();
                WebTarget target = client.target(new Links().E_INVOICE_V2_AUTH_TOKEN)
                try {
                    Response apiResponse = target
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .header("Gstin", entityIrnDetails.get("irnGSTIN"))
                            .header("X-Asp-Auth-Token", authToken)
                            .header("X-Asp-Auth-Signature", signedToken)
                            .post(Entity.entity(finalPayLoad, MediaType.APPLICATION_JSON_TYPE))
                    if (apiResponse.status == 200) {
                        JSONObject sessionData = new JSONObject(apiResponse.readEntity(String.class))
                        if(sessionData == null)
                        {
                            println("Auth Token Null")
                        }
                        else
                        {
                            println(sessionData.toString())
                        }
                        return null
                    }
                    else {
                        def resp = apiResponse.readEntity(String.class)
                        println(resp)
                        log.error(resp)
                        return null
                    }
                }
                catch (Exception ex) {
                    System.err.println('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
                    log.error('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
                    return null
                }
            }

            return authToken
        }
        else
            return null
    }


    private generateSignatureAndAuthToken(HttpSession session) {
        try {
            println("Inside generateSignatureAndAuthToken")
            SimpleDateFormat tokenDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            String entityId = session.getAttribute("entityId").toString()
            entityIrnDetails = new EntityService().getEntityIrnByEntity(entityId)
            //don't proceed if null
            if (entityIrnDetails == null) {
                println("Entity IRN Details NULL")
                return null
            }
            //don't proceed if inactive
            if (!entityIrnDetails.get("active")) {
                println("Entity IRN Details Inactive")
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
                        if(authToken == null)
                        {
                            println("Auth Token Null")
                        }
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
                println("Auth Token Valid")
                return entityIrnDetails
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
            return null
        }
    }

    private generateAuthToken(JSONObject jsonObject) {
        println("Inside generateAuthToken")
        //To encrypt auth-token payload payload
        String randomAppKey = Base64.getEncoder().encodeToString(new EinvoiceHelper().createAESKey());
        String base64EncodedAppKey = Base64.getEncoder().encodeToString(randomAppKey.getBytes());

        JSONObject authPayload = new JSONObject()
        authPayload.put("UserName", entityIrnDetails.get("irnUsername"))
        authPayload.put("Password", entityIrnDetails.get("irnPassword"))
        authPayload.put("AppKey", base64EncodedAppKey)
        authPayload.put("ForceRefreshAccessToken", true)

        println(authPayload.toString())

        String base64EncodedPayload = Base64.getEncoder().encodeToString(authPayload.toString().getBytes());
        //byte[] b = new NicV4TokenPayloadGen().readFile(this.class.classLoader.getResource('KeyStore/publicKey-prod').file)
        byte[] b = new NicV4TokenPayloadGen().readFile(this.class.classLoader.getResource('KeyStore/publicKey-test').file)
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

                entityIrnDetails.put("entity",  entityIrnDetails.get("entity").id)
                entityIrnDetails.put("entityType",  entityIrnDetails.get("entityType").id)
                entityIrnDetails.put("isActive", entityIrnDetails.get("active"))

                new EntityService().updateEntityIRN(entityIrnDetails)
                return entityIrnDetails
            } else {
                def resp = apiResponse.readEntity(String.class)
                println(resp)
                log.error(resp)
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
        }
    }

    def generateIRN(HttpSession session, JSONObject saleBillDetail, JSONArray saleProductDetails) {

        JSONObject authData1 = generateAuthToken(session)
        return
        JSONObject authData = generateSignatureAndAuthToken(session)
        if (authData == null) {
            println("AUTH DATA is NULL")
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
                        log.error(generatedIRN.get("ErrorDetails").toString())
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


    public static String buildIrnPayload(JSONObject saleBillDetail, JSONArray saleProductDetails) {
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
           /* TranDtls.put("RegRev", "Y")
            TranDtls.put("EcmGstin", null)
            if(sellerState.get("id") == buyerState.get("id"))
                TranDtls.put("IgstOnIntra", "N")
            else
                TranDtls.put("IgstOnIntra", "Y")*/
            irnObject.put("TranDtls", TranDtls)

            //Document Details
            JSONObject DocDtls = new JSONObject()
            DocDtls.put("Typ", "INV")
            DocDtls.put("No", saleBillDetail.get("invoiceNumber"))
            DocDtls.put("Dt", orderDate)
            irnObject.put("DocDtls", DocDtls)

            //Seller Details
            JSONObject SellerDtls = new JSONObject()
            SellerDtls.put("Gstin", sellerDetails.get("gstn"))
            //SellerDtls.put("Gstin", "27ABFPD4021L002") //TODO: to be removed
            SellerDtls.put("LglNm", sellerDetails.get("entityName"))
            SellerDtls.put("TrdNm", sellerDetails.get("entityName"))
            SellerDtls.put("Addr1", new UtilsService().truncateString(sellerDetails.get("addressLine1").toString(),100))
            String sellerAddressLine2 = ""
            if (sellerDetails.get("addressLine2")?.toString()?.length() > 2) {
                sellerAddressLine2 = new UtilsService().truncateString(sellerDetails.get("addressLine2").toString(), 100)
                SellerDtls.put("Addr2", sellerAddressLine2)
            }

            if(sellerCity.has("name"))
                SellerDtls.put("Loc", sellerCity.get("name"))
            else
                SellerDtls.put("Loc", sellerCity.get("areaName"))

            SellerDtls.put("Pin", Long.parseLong(sellerDetails.get("pinCode").toString()))
            //SellerDtls.put("Pin", 431116) //TODO:to be removed

            if(new UtilsService().isValidPhoneNumber())
                SellerDtls.put("Ph", sellerDetails.get("mobileNumber"))
            if(new UtilsService().isValidEmailAddress(sellerDetails?.get("email")?.toString()))
                SellerDtls.put("Em", sellerDetails.get("email"))
            SellerDtls.put("Stcd", sellerState.get("irnStateCode"))
            //SellerDtls.put("Stcd", "27") //TODO: to be removed
            irnObject.put("SellerDtls", SellerDtls)

            //Buyer Details
            JSONObject BuyerDtls = new JSONObject()
            BuyerDtls.put("Gstin", buyerDetails.get("gstn"))
            BuyerDtls.put("LglNm", buyerDetails.get("entityName"))
            BuyerDtls.put("TrdNm", buyerDetails.get("entityName"))
            BuyerDtls.put("Addr1", new UtilsService().truncateString(buyerDetails.get("addressLine1").toString(), 100))
            String buyerAddressLine2 = ""
            if (buyerDetails.get("addressLine2")?.toString()?.length() > 2) {
                buyerAddressLine2 = new UtilsService().truncateString(buyerDetails.get("addressLine2").toString(), 100)
                BuyerDtls.put("Addr2", buyerAddressLine2)
            }
            if(buyerCity.has("name"))
                BuyerDtls.put("Loc", buyerCity.get("name"))
            else
                BuyerDtls.put("Loc", buyerCity.get("areaName"))

            BuyerDtls.put("Pin", Long.parseLong(buyerDetails.get("pinCode").toString()))
            if(new UtilsService().isValidPhoneNumber())
                BuyerDtls.put("Ph", buyerDetails.get("mobileNumber"))
            if(new UtilsService().isValidEmailAddress(buyerDetails?.get("email")?.toString()))
                BuyerDtls.put("Em", buyerDetails.get("email"))
            BuyerDtls.put("Stcd", buyerState.get("irnStateCode"))
            BuyerDtls.put("Pos",  buyerState.get("irnStateCode"))

            //TODO: to be removed
/*            BuyerDtls.put("Pin", 431116)
            BuyerDtls.put("Stcd", "27")
            BuyerDtls.put("Pos",  "27")*/

            irnObject.put("BuyerDtls", BuyerDtls)

            //Dispatch Details
            JSONObject DispDtls = new JSONObject()
            DispDtls.put("Nm", sellerDetails.get("entityName"))
            if(sellerCity.has("name"))
                DispDtls.put("Loc", sellerCity.get("name"))
            else
                DispDtls.put("Loc", sellerCity.get("areaName"))

            DispDtls.put("Addr1", new UtilsService().truncateString(sellerDetails.get("addressLine1").toString(), 100))
            String dispDtlsAddressLine2 = ""
            if (sellerDetails.get("addressLine2")?.toString()?.length() > 2) {
                dispDtlsAddressLine2 = new UtilsService().truncateString(sellerDetails.get("addressLine2").toString(), 100)
                DispDtls.put("Addr2", dispDtlsAddressLine2)
            }
            DispDtls.put("Pin", Long.parseLong(sellerDetails.get("pinCode").toString()))
            DispDtls.put("Stcd", sellerState.get("irnStateCode"))

            //TODO: to be removed
/*            DispDtls.put("Pin", 431116)
            DispDtls.put("Stcd", "27")*/

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
            double cashDiscount = 0.00
            double TotInvVal = 0.00
            int slNo = 1
            for (JSONObject saleProduct : saleProductDetails) {
                JSONObject product = new ProductService().getProductById(saleProduct.get("productId").toString())
                JSONObject item = new JSONObject()
                double sRate = Double.parseDouble(saleProduct.get("sRate").toString())
                String saleQty = saleProduct.get("sqty").toString()
                saleQty = saleQty.replaceAll("\\.0","")

                String freeQty = saleProduct.get("freeQty").toString()
                freeQty = freeQty.replaceAll("\\.0","")

                Integer sqty = Integer.parseInt(saleQty)
                Integer fqty = Integer.parseInt(freeQty)

                //replacement quantity added to free quantity
                String repQty = saleProduct.get("repQty").toString()
                repQty = repQty.replaceAll("\\.0","")
                if(repQty != null) {
                    Integer rQty = Integer.parseInt(repQty)
                    if(rQty>0) {
                        fqty = fqty + rQty
                    }
                }

                double itemAmount = UtilsService.round((sRate * sqty), 2)
                double discountAmt = 0;
                double discountPercentage = UtilsService.round(Double.parseDouble(saleProduct.get("discount").toString()), 2) //Percentage
                if(saleProduct.get("discount")!=0){
                   discountAmt =  itemAmount*(discountPercentage/100)
                }
                double assAmt = UtilsService.round(itemAmount - discountAmt, 2)
                double igst = UtilsService.round(Double.parseDouble(saleProduct.get("igstAmount").toString()), 2)
                double cgst = UtilsService.round(Double.parseDouble(saleProduct.get("cgstAmount").toString()), 2)
                double sgst = UtilsService.round(Double.parseDouble(saleProduct.get("sgstAmount").toString()), 2)
                //double amount = UtilsService.round(Double.parseDouble(saleProduct.get("amount").toString()), 2)
                double amount = assAmt + igst + cgst + sgst
                TotAssVal += assAmt
                TotCgstVal += cgst
                TotIgstVal += igst
                TotSgstVal += sgst
                TotInvVal += amount
                item.put("SlNo", slNo.toString())
                item.put("PrdDesc", product.get("productName"))
                item.put("IsServc", "N")
                item.put("HsnCd", product.get("hsnCode"))
                item.put("Qty", sqty)
                item.put("FreeQty", fqty)
                item.put("UnitPrice", UtilsService.round(sRate, 2))
                item.put("Unit", "OTH")
                item.put("TotAmt", itemAmount)
                item.put("Discount", UtilsService.round(discountAmt,2))
                item.put("AssAmt", assAmt)
                item.put("GstRt", UtilsService.round(Double.parseDouble(saleProduct.get("gstPercentage").toString()), 2))
                item.put("IgstAmt", igst)
                item.put("CgstAmt", cgst)
                item.put("SgstAmt", sgst)
                item.put("TotItemVal", UtilsService.round(amount,2))
                //item.put("OrgCntry", sellerState.get("country")["irnCountryCode"])
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
            TotAssVal = UtilsService.round(TotAssVal, 2)
            TotCgstVal = UtilsService.round(TotCgstVal, 2)
            TotSgstVal = UtilsService.round(TotSgstVal, 2)
            TotIgstVal = UtilsService.round(TotIgstVal, 2)
            TotInvVal = UtilsService.round(TotInvVal, 2)

            //TODO: this is cash discount which is calculated on invoice amount,
            // not calculated from individual products, so set to 0 as of now
            cashDiscount = UtilsService.round(cashDiscount, 2)

            ValDtls.put("AssVal",  TotAssVal)
            ValDtls.put("CgstVal", TotCgstVal)
            ValDtls.put("SgstVal", TotSgstVal)
            ValDtls.put("IgstVal", TotIgstVal)
            ValDtls.put("Discount", cashDiscount)
            ValDtls.put("TotInvVal", TotInvVal)

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

    def cancelIRN(HttpSession session, String irn, String invoiceId)
    {
        JSONObject cancelJson = new JSONObject()
        cancelJson.put("Irn", irn)
        cancelJson.put("CnlRsn", "1")
        cancelJson.put("CnlRem", "Wrong entry")
        JSONObject authData = generateSignatureAndAuthToken(session)
        if (authData == null) {
            return
        }
        String appKey = authData.get("appKey")
        String sek = authData.get("sek")
        String encryptedPayload = new NICEncryption(appKey, sek).EncryptPayload(cancelJson.toString())

        JSONObject finalPayLoad = new JSONObject()
        finalPayLoad.put("Data", encryptedPayload)

        Logger logger = Logger.getLogger(getClass().getName())
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        Client client = ClientBuilder.newClient();
        client.register(feature)
        WebTarget target = client.target(new Links().E_INVOICE_CANCEL_IRN)
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
                JSONObject cancelledIRN = new JSONObject(apiResponse.readEntity(String.class))
                if (cancelledIRN) {
                    if (cancelledIRN.get("Status").toString().equalsIgnoreCase("1")) {
                        JSONObject cancelledIRNDetails = new JSONObject(new NICEncryption(appKey, sek).DecryptResponse(cancelledIRN.get("Data").toString()))
                        Response apiResp = new SalesService().getSaleInvoiceById(invoiceId)
                        if (apiResp.status == 200) {
                            JSONObject salesInvoice = new JSONObject(apiResp.readEntity(String.class))
                            JSONObject cancelledIRNDetailsJSON = new JSONObject()
                            cancelledIRNDetailsJSON.put("id", salesInvoice.get("id"))
                            cancelledIRNDetailsJSON.put("cancelledDate", cancelledIRNDetails.get("CancelDate").toString())
                            new SalesService().updateSaleBillIRNDetails(cancelledIRNDetailsJSON)
                        }
                    } else {
                        println(cancelledIRN.get("ErrorDetails").toString())
                        log.error(cancelledIRN.get("ErrorDetails").toString())
                    }
                }
                return cancelledIRN
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  cancelIRN  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  cancelIRN  , Ex:' + ex)
        }
    }

    //TODO: e-Way Bill generation to be included
    def generateEWayBill(HttpSession session, JSONObject saleBillDetail)
    {
        JSONObject authData = generateSignatureAndAuthToken(session)
        if (authData == null) {
            println("AUTH DATA is NULL")
            return
        }
        String invoiceId = saleBillDetail.get("id")
        JSONObject eWayBillObject = new JSONObject()
        eWayBillObject.put("Irn","")
        eWayBillObject.put("TransId","")
        eWayBillObject.put("TransMod","")
        eWayBillObject.put("TrnDocNO","")
        eWayBillObject.put("TrnDocDt","")
        eWayBillObject.put("VehNo","")
        eWayBillObject.put("Distance","")
        eWayBillObject.put("VehType","")
        eWayBillObject.put("TransName","")
        eWayBillObject.put("ExpShipDtls","")
        eWayBillObject.put("DispDtls","")

        String appKey = authData.get("appKey")
        String sek = authData.get("sek")
        String encryptedPayload = new NICEncryption(appKey, sek).EncryptPayload(eWayBillObject.toString())

        JSONObject finalPayLoad = new JSONObject()
        finalPayLoad.put("Data", encryptedPayload)

        Logger logger = Logger.getLogger(getClass().getName())
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        Client client = ClientBuilder.newClient();
        client.register(feature)
        WebTarget target = client.target(new Links().E_WAYBILL_GEN)
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
                JSONObject generatedEWayBill = new JSONObject(apiResponse.readEntity(String.class))
                if (generatedEWayBill) {
                    if (generatedEWayBill.get("Status").toString().equalsIgnoreCase("1")) {
                        def ewayBillDetails = new NICEncryption(appKey, sek).DecryptResponse(generatedEWayBill.get("Data").toString())
                        Response apiResp = new SalesService().getSaleInvoiceById(invoiceId)
                        if (apiResp.status == 200) {
                            JSONObject salesInvoice = new JSONObject(apiResp.readEntity(String.class))
                            JSONObject ewayDetailsJSON = new JSONObject()
                            ewayDetailsJSON.put("id", salesInvoice.get("id"))
                            ewayDetailsJSON.put("ewayBillDetails", ewayBillDetails)
                            new SalesService().updateSaleBillEwayBillDetails(ewayDetailsJSON)
                        }
                    } else {
                        println(generatedEWayBill.get("ErrorDetails").toString())
                        log.error(generatedEWayBill.get("ErrorDetails").toString())
                    }
                }
                return generatedEWayBill
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  generateEWayBill  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  generateEWayBill  , Ex:' + ex)
        }

    }

    def cancelEwayBillDetails(HttpSession session, String ewbNo, String invoiceId)
    {
        JSONObject cancelJson = new JSONObject()
        cancelJson.put("ewbNo", ewbNo)
        cancelJson.put("cancelRsnCode", "2")
        cancelJson.put("cancelRmrk", "Cancelled the order")
        JSONObject authData = generateSignatureAndAuthToken(session)
        if (authData == null) {
            return
        }
        String appKey = authData.get("appKey")
        String sek = authData.get("sek")
        String encryptedPayload = new NICEncryption(appKey, sek).EncryptPayload(cancelJson.toString())

        JSONObject finalPayLoad = new JSONObject()
        finalPayLoad.put("Data", encryptedPayload)

        Logger logger = Logger.getLogger(getClass().getName())
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        Client client = ClientBuilder.newClient();
        client.register(feature)
        WebTarget target = client.target(new Links().E_WAYBILL_CANCEL)
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
                JSONObject cancelledEwayBill = new JSONObject(apiResponse.readEntity(String.class))
                if (cancelledEwayBill) {
                    if (cancelledEwayBill.get("Status").toString().equalsIgnoreCase("1")) {
                        JSONObject cancelledEwayBillDetails = new JSONObject(new NICEncryption(appKey, sek).DecryptResponse(cancelledEwayBill.get("Data").toString()))
                        Response apiResp = new SalesService().getSaleInvoiceById(invoiceId)
                        if (apiResp.status == 200) {
                            JSONObject salesInvoice = new JSONObject(apiResp.readEntity(String.class))
                            JSONObject cancelledEwayBillDetailsJSON = new JSONObject()
                            cancelledEwayBillDetailsJSON.put("id", salesInvoice.get("id"))
                            cancelledEwayBillDetailsJSON.put("cancelledDate", cancelledEwayBillDetails.get("CancelDate").toString())
                            new SalesService().updateSaleBillEwayBillDetails(cancelledEwayBillDetailsJSON)
                        }
                    } else {
                        println(cancelledEwayBill.get("ErrorDetails").toString())
                        log.error(cancelledEwayBill.get("ErrorDetails").toString())
                    }
                }
                return cancelledEwayBill
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  cancelEwayBillDetails  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  cancelEwayBillDetails  , Ex:' + ex)
        }
    }



    private generateAuthTokenV2(JSONObject jsonObject) {
        println("Inside generateAuthToken")
        //To encrypt auth-token payload payload
        String randomAppKey = Base64.getEncoder().encodeToString(new EinvoiceHelper().createAESKey());
        String base64EncodedAppKey = Base64.getEncoder().encodeToString(randomAppKey.getBytes());

        JSONObject authPayload = new JSONObject()
        authPayload.put("UserName", entityIrnDetails.get("irnUsername"))
        authPayload.put("Password", entityIrnDetails.get("irnPassword"))
        authPayload.put("AppKey", base64EncodedAppKey)
        authPayload.put("ForceRefreshAccessToken", true)

        println(authPayload.toString())

        String base64EncodedPayload = Base64.getEncoder().encodeToString(authPayload.toString().getBytes());
        //byte[] b = new NicV4TokenPayloadGen().readFile(this.class.classLoader.getResource('KeyStore/publicKey-prod').file)
        byte[] b = new NicV4TokenPayloadGen().readFile(this.class.classLoader.getResource('KeyStore/publicKey-test').file)
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

                entityIrnDetails.put("entity",  entityIrnDetails.get("entity").id)
                entityIrnDetails.put("entityType",  entityIrnDetails.get("entityType").id)
                entityIrnDetails.put("isActive", entityIrnDetails.get("active"))

                new EntityService().updateEntityIRN(entityIrnDetails)
                return entityIrnDetails
            } else {
                def resp = apiResponse.readEntity(String.class)
                println(resp)
                log.error(resp)
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
        }
    }
}

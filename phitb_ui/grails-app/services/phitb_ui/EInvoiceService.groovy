package phitb_ui

import grails.gorm.transactions.Transactional
import org.bouncycastle.util.encoders.Base64Encoder
import org.glassfish.jersey.logging.LoggingFeature
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
    String userName = "nsdlTest"
    String password = "Test@123"
    String gstin = "27ABFPD4021L002"
    JSONObject entityIrnDetails = new JSONObject()

    def generateSignatureAndAuthToken(HttpSession session) {
        try {
            SimpleDateFormat tokenDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            //String entityId = session.getAttribute("entityId").toString()
            String entityId = "1"
            entityIrnDetails = new EntityService().getEntityIrnByEntity(entityId)
            boolean isAuthTokenValid = true
            if(entityIrnDetails && entityIrnDetails.has("authToken"))
            {
                Date authTokenExpiryDate = tokenDateFormat.parse(entityIrnDetails.get("tokenExpiry").toString())
                if(authTokenExpiryDate.before(new Date()))
                    isAuthTokenValid = false
            }
            if(!isAuthTokenValid)
            {
                String aspId = Constants.E_INVOICE_ASP_ID;
                String ts = "";
                ts = new EinvoiceHelper().getCurrTs();
                System.out.println("AspId : " + aspId);
                System.out.println("TimeStamp : " + ts);
                String aspData = aspId + ts;
                String sign = ""
                try {
                    sign = new EinvoiceHelper().generateSignature(aspData);
                    //System.out.println("sign:" + sign);
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
            }
            else
            {
                return entityIrnDetails
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
            return null
        }
    }

    def generateAuthToken(JSONObject jsonObject) {
        //To encrypt auth-token payload payload
        String randomAppKey = Base64.getEncoder().encodeToString(new EinvoiceHelper().createAESKey());
        String base64EncodedAppKey = Base64.getEncoder().encodeToString(randomAppKey.getBytes());
        String authPayload = "{\"UserName\":\"nsdlTest\", \"Password\":\"Test@123\", \"AppKey\":\"" + base64EncodedAppKey + "\", \"ForceRefreshAccessToken\":true}";
        String base64EncodedPayload = Base64.getEncoder().encodeToString(authPayload.getBytes());
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
                    .header("gstin", gstin)
                    .post(Entity.entity(finalPayLoad.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject authToken = new JSONObject(apiResponse.readEntity(String.class))
                //update entityIrnDetails
                entityIrnDetails.put("sessionId", jsonObject.get("session_id").toString())
                entityIrnDetails.put("appKey", authToken.get("appKey").toString())
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

    def generateIRN(HttpSession session) {

        JSONObject authData = generateSignatureAndAuthToken(session)
       // JSONObject authData = generateAuthToken(sessionData)
        String sampleIRN = "\n" +
                "{\n" +
                "  \"Version\": \"1.1\",\n" +
                "  \"TranDtls\": {\n" +
                "    \"TaxSch\": \"GST\",\n" +
                "    \"SupTyp\": \"B2B\",\n" +
                "    \"RegRev\": \"Y\",\n" +
                "    \"EcmGstin\": null,\n" +
                "    \"IgstOnIntra\": \"N\"\n" +
                "  },\n" +
                "  \"DocDtls\": {\n" +
                "    \"Typ\": \"INV\",\n" +
                "    \"No\": \"DOC/003\",\n" +
                "    \"Dt\": \"29/04/2022\"\n" +
                "  },\n" +
                "  \"SellerDtls\": {\n" +
                "    \"Gstin\": \"27ABFPD4021L002\",\n" +
                "    \"LglNm\": \"NIC company pvt ltd\",\n" +
                "    \"TrdNm\": \"NIC Industries\",\n" +
                "    \"Addr1\": \"5th block, kuvempu layout\",\n" +
                "    \"Addr2\": \"kuvempu layout\",\n" +
                "    \"Loc\": \"GANDHINAGAR\",\n" +
                "    \"Pin\": 410506,\n" +
                "    \"Stcd\": \"27\",\n" +
                "    \"Ph\": \"9000000000\",\n" +
                "    \"Em\": \"abc@gmail.com\"\n" +
                "  },\n" +
                "  \"BuyerDtls\": {\n" +
                "    \"Gstin\": \"29AWGPV7107B1Z1\",\n" +
                "    \"LglNm\": \"XYZ company pvt ltd\",\n" +
                "    \"TrdNm\": \"XYZ Industries\",\n" +
                "    \"Pos\": \"12\",\n" +
                "    \"Addr1\": \"7th block, kuvempu layout\",\n" +
                "    \"Addr2\": \"kuvempu layout\",\n" +
                "    \"Loc\": \"GANDHINAGAR\",\n" +
                "    \"Pin\": 562160,\n" +
                "    \"Stcd\": \"29\",\n" +
                "    \"Ph\": \"91111111111\",\n" +
                "    \"Em\": \"xyz@yahoo.com\"\n" +
                "  },\n" +
                "  \"DispDtls\": {\n" +
                "    \"Nm\": \"ABC company pvt ltd\",\n" +
                "    \"Addr1\": \"7th block, kuvempu layout\",\n" +
                "    \"Addr2\": \"kuvempu layout\",\n" +
                "    \"Loc\": \"Banagalore\",\n" +
                "    \"Pin\": 562160,\n" +
                "    \"Stcd\": \"29\"\n" +
                "  },\n" +
                "  \"ShipDtls\": {\n" +
                "    \"Gstin\": \"29AWGPV7107B1Z1\",\n" +
                "    \"LglNm\": \"CBE company pvt ltd\",\n" +
                "    \"TrdNm\": \"kuvempu layout\",\n" +
                "    \"Addr1\": \"7th block, kuvempu layout\",\n" +
                "    \"Addr2\": \"kuvempu layout\",\n" +
                "    \"Loc\": \"Banagalore\",\n" +
                "    \"Pin\": 562160,\n" +
                "    \"Stcd\": \"29\"\n" +
                "  },\n" +
                "  \"ItemList\": [\n" +
                "    {\n" +
                "      \"SlNo\": \"1\",\n" +
                "      \"PrdDesc\": \"Rice\",\n" +
                "      \"IsServc\": \"N\",\n" +
                "      \"HsnCd\": \"1001\",\n" +
                "      \"Barcde\": \"123456\",\n" +
                "      \"Qty\": 100.345,\n" +
                "      \"FreeQty\": 10,\n" +
                "      \"Unit\": \"BAG\",\n" +
                "      \"UnitPrice\": 99.545,\n" +
                "      \"TotAmt\": 9988.84,\n" +
                "      \"Discount\": 10,\n" +
                "      \"PreTaxVal\": 1,\n" +
                "      \"AssAmt\": 9978.84,\n" +
                "      \"GstRt\": 12.0,\n" +
                "      \"IgstAmt\": 1197.46,\n" +
                "      \"CgstAmt\": 0,\n" +
                "      \"SgstAmt\": 0,\n" +
                "      \"CesRt\": 5,\n" +
                "      \"CesAmt\": 498.94,\n" +
                "      \"CesNonAdvlAmt\": 10,\n" +
                "      \"StateCesRt\": 12,\n" +
                "      \"StateCesAmt\": 1197.46,\n" +
                "      \"StateCesNonAdvlAmt\": 5,\n" +
                "      \"OthChrg\": 10,\n" +
                "      \"TotItemVal\": 12897.7,\n" +
                "      \"OrdLineRef\": \"3256\",\n" +
                "      \"OrgCntry\": \"AG\",\n" +
                "      \"PrdSlNo\": \"12345\",\n" +
                "      \"BchDtls\": {\n" +
                "        \"Nm\": \"123456\",\n" +
                "        \"ExpDt\": \"01/08/2020\",\n" +
                "        \"WrDt\": \"01/09/2020\"\n" +
                "      },\n" +
                "      \"AttribDtls\": [\n" +
                "        {\n" +
                "          \"Nm\": \"Rice\",\n" +
                "          \"Val\": \"10000\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"ValDtls\": {\n" +
                "    \"AssVal\": 9978.84,\n" +
                "    \"CgstVal\": 0,\n" +
                "    \"SgstVal\": 0,\n" +
                "    \"IgstVal\": 1197.46,\n" +
                "    \"CesVal\": 508.94,\n" +
                "    \"StCesVal\": 1202.46,\n" +
                "    \"Discount\": 10,\n" +
                "    \"OthChrg\": 20,\n" +
                "    \"RndOffAmt\": 0.3,\n" +
                "    \"TotInvVal\": 12908,\n" +
                "    \"TotInvValFc\": 12897.7\n" +
                "  },\n" +
                "  \"PayDtls\": {\n" +
                "    \"Nm\": \"ABCDE\",\n" +
                "    \"AccDet\": \"5697389713210\",\n" +
                "    \"Mode\": \"Cash\",\n" +
                "    \"FinInsBr\": \"SBIN11000\",\n" +
                "    \"PayTerm\": \"100\",\n" +
                "    \"PayInstr\": \"Gift\",\n" +
                "    \"CrTrn\": \"test\",\n" +
                "    \"DirDr\": \"test\",\n" +
                "    \"CrDay\": 100,\n" +
                "    \"PaidAmt\": 10000,\n" +
                "    \"PaymtDue\": 5000\n" +
                "  },\n" +
                "  \"RefDtls\": {\n" +
                "    \"InvRm\": \"TEST\",\n" +
                "    \"DocPerdDtls\": {\n" +
                "      \"InvStDt\": \"01/08/2020\",\n" +
                "      \"InvEndDt\": \"01/09/2020\"\n" +
                "    },\n" +
                "    \"PrecDocDtls\": [\n" +
                "      {\n" +
                "        \"InvNo\": \"DOC/002\",\n" +
                "        \"InvDt\": \"01/08/2020\",\n" +
                "        \"OthRefNo\": \"123456\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"ContrDtls\": [\n" +
                "      {\n" +
                "        \"RecAdvRefr\": \"Doc/003\",\n" +
                "        \"RecAdvDt\": \"01/08/2020\",\n" +
                "        \"TendRefr\": \"Abc001\",\n" +
                "        \"ContrRefr\": \"Co123\",\n" +
                "        \"ExtRefr\": \"Yo456\",\n" +
                "        \"ProjRefr\": \"Doc-456\",\n" +
                "        \"PORefr\": \"Doc-789\",\n" +
                "        \"PORefDt\": \"01/08/2020\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"AddlDocDtls\": [\n" +
                "    {\n" +
                "      \"Url\": \"https://einv-apisandbox.nic.in\",\n" +
                "      \"Docs\": \"Test Doc\",\n" +
                "      \"Info\": \"Document Test\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"ExpDtls\": {\n" +
                "    \"ShipBNo\": \"A-248\",\n" +
                "    \"ShipBDt\": \"01/08/2020\",\n" +
                "    \"Port\": \"INABG1\",\n" +
                "    \"RefClm\": \"N\",\n" +
                "    \"ForCur\": \"AED\",\n" +
                "    \"CntCode\": \"AE\",\n" +
                "    \"ExpDuty\": null\n" +
                "  },\n" +
                "  \"EwbDtls\": {\n" +
                "    \"TransId\": \"12AWGPV7107B1Z1\",\n" +
                "    \"TransName\": \"XYZ EXPORTS\",\n" +
                "    \"Distance\": 100,\n" +
                "    \"TransDocNo\": \"DOC01\",\n" +
                "    \"TransDocDt\": \"18/08/2020\",\n" +
                "    \"VehNo\": \"ka123456\",\n" +
                "    \"VehType\": \"R\",\n" +
                "    \"TransMode\": \"1\"\n" +
                "  }\n" +
                "}\n" +
                "\t\t "
        String appKey = authData.get("appKey")
        String sek = authData.get("sek")
        String encryptedPayload = new NICEncryption(appKey, sek).EncryptPayload(sampleIRN)

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
                    .header("asp_secret_key", authData.get("asp_secret_key"))
                    .header("session_id", authData.get("session_id"))
                    .header("gstin", gstin)
                    .header("authtoken", authData.get("authtoken"))
                    .header("username", authData.get("userName"))
                    .post(Entity.entity(finalPayLoad.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject generatedIRN = new JSONObject(apiResponse.readEntity(String.class))
                if (generatedIRN) {
                    if (generatedIRN.get("Status").toString().equalsIgnoreCase("1")) {
                        def tmp = new NICEncryption(appKey, sek).DecryptResponse(generatedIRN.get("Data").toString())
                        println(tmp)
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

}

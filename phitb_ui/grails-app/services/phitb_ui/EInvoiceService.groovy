package phitb_ui

import grails.gorm.transactions.Transactional
import org.glassfish.jersey.logging.LoggingFeature
import org.grails.web.json.JSONObject
import phitb_ui.einvoice.AESEncryption
import phitb_ui.einvoice.EinvoiceHelper

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Feature
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import java.util.logging.Level
import java.util.logging.Logger;

@Transactional
class EInvoiceService {
    String userName = "nsdlTest"
    String password = "Test@123"
    String gstin = "27ABFPD4021L002"
    def generateSignature() {
        try {
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
                        .header("message-id", ts) //TODO: logic to be implemented
                        .header("filler1", "")
                        .header("filler2", "")
                        .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
                if(apiResponse.status == 200)
                {
                    JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                    generateAuthToken(jsonObject1)
                }
            }
            catch (Exception ex) {
                System.err.println('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
                log.error('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }

    def generateAuthToken(JSONObject jsonObject)
    {
        String appKey = Base64.getEncoder().encodeToString(new EinvoiceHelper().createAESKey());
        JSONObject payload = new JSONObject()
        payload.put("UserName", userName)
        payload.put("Password ", password)
        payload.put("AppKey ",appKey)
        payload.put("ForceRefreshAccessToken ",true)
        String payLoadBase64 = Base64.getEncoder().encodeToString(payload.toString().getBytes());
        payLoadBase64 = "{\"Data\":\""+new EinvoiceHelper().encryptAsymmentricKey(payLoadBase64)+"\"}";
        String encAspSecret = new AESEncryption().encryptAspSecret(jsonObject.get("enc_key").toString(), Constants.E_INVOICE_ASP_SECRET)
        Logger logger = Logger.getLogger(getClass().getName());
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
                    .post(Entity.entity(payLoadBase64, MediaType.APPLICATION_JSON_TYPE))
            if(apiResponse.status == 200)
            {
                String tmp = apiResponse.readEntity(String.class)
                println(tmp)
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
            log.error('Service :EInvoiceService , action :  generateAuthToken  , Ex:' + ex)
        }
    }

}

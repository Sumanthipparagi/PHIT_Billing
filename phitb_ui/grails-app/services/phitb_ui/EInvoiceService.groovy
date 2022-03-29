package phitb_ui

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import sun.text.resources.FormatData

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.security.Key
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.cert.Certificate
import java.text.SimpleDateFormat

/*import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.grails.web.json.JSONObject
import sun.misc.BASE64Decoder

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.security.Key
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.X509EncodedKeySpec
import java.util.logging.Level
import java.util.logging.Logger*/

@Transactional
class EInvoiceService {

    def generateSignature() {
        try {
            String alias = "localhost"
            char[] keyStorePassword = "123456".toCharArray();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream keyStoreData = new FileInputStream("C:\\Users\\arjun\\Desktop\\KeyStore.jks")
            keyStore.load(keyStoreData, keyStorePassword)
            final Key privateKey = (PrivateKey) keyStore.getKey(alias, keyStorePassword);

            final Certificate cert = keyStore.getCertificate(alias);
            final PublicKey publicKey = cert.getPublicKey();

            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey)
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmssSSS000")
            byte[] bytes = (Constants.E_INVOICE_ASP_ID + sdf.format(new Date())).getBytes()
            sign.update(bytes)
            byte[] signature = sign.sign()
            String base64EncodedSignature = Base64.encoder.encodeToString(signature)
            println(base64EncodedSignature)

            JSONObject jsonObject = new JSONObject()
            jsonObject.put("timestamp", sdf.format(new Date()))
            jsonObject.put("signed_content", base64EncodedSignature)
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(new Links().E_INVOICE_GET_KEY);
            try {
                Response apiResponse = target
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .header("aspid", new Constants().E_INVOICE_ASP_ID)
                        .header("message-id", "XXXXXXXXXXXXXXXX")
                        .header("filler1", "")
                        .header("filler2", "")
                        .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
                if(apiResponse.status == 200)
                {
                    String tmp = apiResponse.readEntity(String.class)
                    println(tmp)
                }
            }
            catch (Exception ex) {
                System.err.println('Service :showSalesService , action :  show  , Ex:' + ex)
                log.error('Service :showSalesService , action :  show  , Ex:' + ex)
            }
            finally{
                client.close()
            }

        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }
    /*static String folderPath = "";
    static byte[] appKey = null;
    static String userName = "<User Id>";
    static String password = "<Password>";
    static String gstin = "<GSTIN>";
    static String encPayload = "";
    static String authtoken = "";
    static String sek = "";
    static ObjectMapper objectMapper;
    public static void main(String[] args) {
        authtoken = "";
        folderPath = getPath();
        objectMapper = new ObjectMapper();
        try {
            String appKey = Base64.getEncoder().encodeToString(createAESKey());
            String payload = "{\"username\":\"" + userName + "\",\"password\":\"" + password + "\",\"appkey\":\"" + appKey + "\",\"ForceRefreshAccessToken\": true}";
            System.out.println("Payload: Plain: " + payload);
            payload = Base64.getEncoder().encodeToString(payload.getBytes());
            payload = "{\"Data\":\"" + encryptAsymmentricKey(payload) + "\"}";
            System.out.println("Payload: Encrypted: " + payload);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("<URL>/v1.04/auth");
            postRequest.setHeader("client-id", "<Client Id>");
            postRequest.setHeader("client-secret", "Client Secret");
            postRequest.setHeader("gstin", "GSTIN");
            postRequest.addHeader("KeepAlive", "true");
            postRequest.addHeader("AllowAutoRedirect", "false");
            StringEntity input = new StringEntity(payload);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String output;
            String responseText = "";
            while ((output = br.readLine()) != null) {
                responseText = output;
            }
            System.out.println("Response:" + responseText);
            String status = objectMapper.readTree(responseText).get("Status").asText();
            if (status.equals("0")) {
                String errorDesc = "";
                errorDesc = objectMapper.readTree(responseText).get("error").asText();
                //errorDesc = new String(Base64.getDecoder().decode(errorDesc), "utf-8");
                System.out.println("Error: " + errorDesc);
            }
            if (status.equals("1")) {
                authtoken = objectMapper.readTree(responseText).get("Data").get("AuthToken").asText();
                sek = objectMapper.readTree(responseText).get("Data").get("Sek").asText();
                System.out.println("Authtoken: " + authtoken);
                System.out.println("Encrypted SEK: " + sek);
                sek = decrptBySymmetricKeySEK(sek);
                System.out.println("Decrypted SEK: " + sek);
            }
            httpClient.getConnectionManager().shutdown();
        } catch (Exception ex) {
            Logger.getLogger(EWayBillAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static PublicKey getPublicKey() throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream inp = new FileInputStream("<Path to File>/einv_sandbox.pem");
        byte[] keyBytes = new byte[inp.available()];
        inp.read(keyBytes);
        inp.close();
        String pubKey = new String(keyBytes, "UTF-8");
        pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        BASE64Decoder decoder = new BASE64Decoder();
        keyBytes = decoder.decodeBuffer(pubKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }
    public static byte[] createAESKey() {
        try {
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128);
            *//* 128-bit AES *//*
            SecretKey secret = gen.generateKey();
            appKey = secret.getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EWayBillAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appKey;
    }
    private static String encryptAsymmentricKey(String clearText) throws Exception {
        PublicKey publicKeys = getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, publicKeys);
        byte[] encryptedText = cipher.doFinal(clearText.getBytes());
        String encryptedPassword = Base64.getEncoder().encodeToString(encryptedText);
        return encryptedPassword;
    }
    public static String getPath() {
        String folderPath = "";
        try {
            File tempFile = new File(EWayBillAuth.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            folderPath = tempFile.getParentFile().getPath() + "\\";
            return folderPath;
        } catch (URISyntaxException ex) {
            Logger.getLogger(EWayBillAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return folderPath;
    }
    public static String decrptBySymmetricKeySEK(String encryptedSek) {
        Key aesKey = new SecretKeySpec(appKey, "AES"); // converts bytes(32 byte random generated) to key
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // encryption type = AES with padding PKCS5
            cipher.init(Cipher.DECRYPT_MODE, aesKey); // initiate decryption type with the key
            byte[] encryptedSekBytes = Base64.getDecoder().decode(encryptedSek); // decode the base64 encryptedSek to bytes
            byte[] decryptedSekBytes = cipher.doFinal(encryptedSekBytes); // decrypt the encryptedSek with the initialized cipher containing the key(Results in bytes)
            byte[] sekBytes = decryptedSekBytes;
            String decryptedSek = Base64.getEncoder().encodeToString(decryptedSekBytes); // convert the decryptedSek(bytes) to Base64 String
            return decryptedSek;  // return results in base64 string
        } catch (Exception e) {
            return "Exception; " + e;
        }
    }*/
    /* def GetKey()
     {
         Client client = ClientBuilder.newClient();
         WebTarget target = client.target(new Links().API_GATEWAY);
         try
         {
             Response apiResponse = target
                     .path(new Links().E_INVOICE_GET_KEY)
                     .request(MediaType.APPLICATION_JSON_TYPE)
                     .header("aspid", new Constants().E_INVOICE_ASP_ID)
                     .header("message-id", "XXXXXXXXXXXXXXXX")
                     .get()
             return apiResponse
         }
         catch (Exception ex)
         {
             System.err.println('Service :showSalesService , action :  show  , Ex:' + ex)
             log.error('Service :showSalesService , action :  show  , Ex:' + ex)
         }
     }*/

}

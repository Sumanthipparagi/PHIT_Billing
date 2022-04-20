package phitb_ui

import grails.gorm.transactions.Transactional
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.glassfish.jersey.logging.LoggingFeature
import org.grails.web.json.JSONObject
import sun.text.resources.FormatData

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Feature
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.security.Key
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.cert.Certificate
import java.text.SimpleDateFormat
import java.util.logging.Level
import java.util.logging.Logger

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.util.Store;

import sun.misc.BASE64Encoder;

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

    private static final char[] JKSPassword;
    private static final char[] PFXPassword;
    private static KeyStore ks = null;
    private static String alias = null;
    private static X509Certificate UserCert = null;
    private static PrivateKey UserCertPrivKey = null;
    private static PublicKey UserCertPubKey = null;
    private static X509Certificate myPubCert = null;

    static {
        JKSPassword = "123456".toCharArray();
        PFXPassword = "tcs".toCharArray();
    }

    def generateSignature() {
        try {
           /* String alias = "localhost"
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

*/
            String aspId = Constants.E_INVOICE_ASP_ID;
            String ts = "";

            ts = getCurrTs();

            System.out.println("AspId : " + aspId);
            System.out.println("TimeStamp : " + ts);

            String aspData = aspId + ts;
            String sign = ""
            try {
                sign = generateSignature(aspData);
                System.out.println("sign:" + sign);
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
                        .header("message-id", "2022032712345678")
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
                System.err.println('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
                log.error('Service :EInvoiceService , action :  generateSignature  , Ex:' + ex)
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }

    public static String generateSignature(String data) throws Exception {

        System.out.println("@@inside generateSignature: " + data);

        String signature;

        String jksFilepath = "C:\\Users\\arjun\\Desktop\\KeyStore.jks";

        try {
            // Adding Security Provider for PKCS 12
            Security.addProvider(new BouncyCastleProvider());
            // Setting password for the e-Token

            // logging into token
            ks = KeyStore.getInstance("jks");


            FileInputStream fileInputStream = new FileInputStream(jksFilepath);

            // Loading Keystore
            // System.out.println("loading keystore");
            ks.load(fileInputStream, JKSPassword);
            Enumeration<String> e = ks.aliases();

            while (e.hasMoreElements()) {
                alias = e.nextElement();
                // System.out.println("Alias of the e-Token : "+ alias);

                UserCert = (X509Certificate) ks.getCertificate(alias);

                UserCertPubKey = (PublicKey) ks.getCertificate(alias).getPublicKey();

                // System.out.println("loading Private key");
                UserCertPrivKey = (PrivateKey) ks.getKey(alias, JKSPassword);
            }

            // Method Call to generate Signature
            signature = MakeSignature(data);

            return signature;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("generateSignature" + e.getCause());
            throw new Exception();
        }

    }

    private static String MakeSignature(String data) {

        System.out.println("@@inside MakeSignature...");

        try {
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, JKSPassword);
            myPubCert = (X509Certificate) ks.getCertificate(alias);
            Store certs = new JcaCertStore(Arrays.asList(myPubCert));

            CMSSignedDataGenerator generator = new CMSSignedDataGenerator();

            generator.addSignerInfoGenerator(new JcaSimpleSignerInfoGeneratorBuilder().setProvider("BC").build("SHA256withRSA", privateKey, myPubCert));

            generator.addCertificates(certs);

            CMSTypedData data1 = new CMSProcessableByteArray(data.getBytes());

            CMSSignedData signed = generator.generate(data1, true);

            BASE64Encoder encoder = new BASE64Encoder();

            String signedContent = encoder.encode((byte[]) signed.getSignedContent().getContent());

            String envelopedData = encoder.encode(signed.getEncoded());

            return envelopedData;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MakeSignature ==" + e.getCause());
            return "";
        }
    }

    public static String getCurrTs() {
        System.out.println("@@inside getCurrTs...");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyyHHmmssSSS111");
        String tmpstmp = format1.format(cal.getTime());
        return tmpstmp;
    }
}

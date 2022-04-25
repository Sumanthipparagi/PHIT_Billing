package phitb_ui.einvoice;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Store;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Enumeration;

public class EinvoiceHelper {

    private static final char[] JKSPassword;
    private static final char[] PFXPassword;
    private static KeyStore ks = null;
    private static String alias = null;
    private static X509Certificate UserCert = null;
    private static PrivateKey UserCertPrivKey = null;
    private static PublicKey UserCertPubKey = null;
    private static X509Certificate myPubCert = null;

    static byte[] appKey = null;


    static {
        JKSPassword = "123456".toCharArray();
        PFXPassword = "tcs".toCharArray();
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

    public static byte[] createAESKey() {
        try {
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128);
            /* 128-bit AES */
            SecretKey secret = gen.generateKey();
            appKey = secret.getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return appKey;
    }

    public static String encryptAsymmentricKey(String clearText) throws Exception
    {
        PublicKey publicKeys = getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, publicKeys);
        byte[] encryptedText = cipher.doFinal(clearText.getBytes());
        String encryptedPassword = Base64.getEncoder().encodeToString(encryptedText);
        return encryptedPassword;
    }

    public static PublicKey getPublicKey() throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\arjun\\Desktop\\publicKey.pem");
        byte[] keyBytes = new byte[inputStream.available()];
        inputStream.read(keyBytes);
        inputStream.close();
        String pubKey = new String(keyBytes, "UTF-8");
        pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        BASE64Decoder decoder = new BASE64Decoder();
        keyBytes = decoder.decodeBuffer(pubKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }
}
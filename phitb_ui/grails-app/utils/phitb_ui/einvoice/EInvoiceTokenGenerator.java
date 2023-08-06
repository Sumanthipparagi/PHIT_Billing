package phitb_ui.einvoice;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class EInvoiceTokenGenerator {

    private void showAuthenticationPem(String privateKeyPath, String publicKeyPath)
            throws KeyStoreException, UnrecoverableKeyException, CertificateException,
            NoSuchAlgorithmException, IOException, SignatureException,
            InvalidKeyException,
            InvalidKeySpecException {
        String message = "a quick brown fox jumped over the crazy dog";
        PrivateKey privateKey = readPemPrivateKey(privateKeyPath);
        PublicKey publicKey = readPemPublicKey(publicKeyPath);
        signAndVerify(message, privateKey, publicKey);
    }

    public String signAndVerify(String message, PrivateKey privateKey, PublicKey
            publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            IOException {
        String signature = sign(message, privateKey);
        if(verify(message, signature, publicKey))
            return signature;
        else
            return null;
    }

    public PublicKey readPemPublicKey(String publicKeyPath) throws
            CertificateException, IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        File file = new File(publicKeyPath);
        DataInputStream is = new DataInputStream(Files.newInputStream(file.toPath()));
        byte[] keyBytes = new byte[(int) file.length()];
        is.readFully(keyBytes);
        is.close();
        String temp = new String(keyBytes);
        String pubKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----", "");
        pubKeyPEM = pubKeyPEM.replace("-----END PUBLIC KEY-----", "");
        pubKeyPEM = pubKeyPEM.replace("\n", "");
        Base64.Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(pubKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public PrivateKey readPemPrivateKey(String privateKeyPath) throws
            CertificateException, IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        File file = new File(privateKeyPath);
        DataInputStream is = new DataInputStream(Files.newInputStream(file.toPath()));
        byte[] keyBytes = new byte[(int) file.length()];
        is.readFully(keyBytes);
        is.close();
        String temp = new String(keyBytes);
        String privKeyPEM = temp.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replace("-----END RSA PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replace("\n", "");
        Base64.Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(privKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private Boolean verify(String message, String signature, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            IOException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(publicKey);
        sig.update(message.getBytes());
        Boolean verified = sig.verify(Base64.getDecoder().decode(signature));
        System.out.println("Verification status is: " + verified);
        return verified;
    }

    private String sign(String message, PrivateKey privateKey) throws
            NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA1WithRSA");
        sig.initSign(privateKey);
        sig.update(message.getBytes());
        byte[] signatureBytes = sig.sign();
        String signature = Base64.getEncoder().encodeToString(signatureBytes);
        System.out.println("Signature:" + signature);
        return signature;
    }


    // A method to generate a 10 digit random alphanumeric string
    public static String generateTransactionId() {
        // Define the characters to use
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Create a random object
        Random random = new Random();
        // Create a string builder to store the result
        StringBuilder sb = new StringBuilder();
        // Loop 10 times and append a random character from chars
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        // Return the string
        return sb.toString() + getCurrentDateTime();
    }

    // A method to get the current date and time in the format ddMMyyyyHHmmss.SSS
    public static String getCurrentDateTime() {
        // Create a simple date format object
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
        // Get the current date and time
        Date date = new Date();
        // Format the date and time and return the string
        return sdf.format(date);
    }
}

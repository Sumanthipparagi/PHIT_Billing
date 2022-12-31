package phitb_ui

import org.apache.commons.codec.binary.Base64

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import java.security.InvalidKeyException
import java.security.Key
import java.security.KeyPair
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.Certificate
import java.security.cert.CertificateException

class Cryptography {


    private Cipher cipher
    def publicKey
    def privateKey
    KeyStore keyStore
    String password = "pMif10!TSb"

    Cryptography() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA")
        File keyStoreFile = new File(this.class.classLoader.getResource("KeyStore/KeyStore-prod.jks").toURI())
        keyStore = loadKeyStore(keyStoreFile, password)
        KeyPair keyPair = getKeyPair(keyStore, "PHARMIT", password)
        publicKey = keyPair.public
        privateKey = keyPair.private
    }

    public String encryptText(String msg)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
                    UnsupportedEncodingException, IllegalBlockSizeException,
                    BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
    }

    public String decryptText(String msg)
            throws InvalidKeyException, UnsupportedEncodingException,
                    IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
    }


    /**
     * Reads a Java keystore from a file.
     *
     * @param keystoreFile
     *          keystore file to read
     * @param password
     *          password for the keystore file
     * @param keyStoreType
     *          type of keystore, e.g., JKS or PKCS12
     * @return the keystore object
     * @throws KeyStoreException
     *           if the type of KeyStore could not be created
     * @throws IOException
     *           if the keystore could not be loaded
     * @throws NoSuchAlgorithmException
     *           if the algorithm used to check the integrity of the keystore
     *           cannot be found
     * @throws CertificateException
     *           if any of the certificates in the keystore could not be loaded
     */
    public static KeyStore loadKeyStore(final File keystoreFile, final String password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException,
                    CertificateException {
        if (null == keystoreFile) {
            throw new IllegalArgumentException("Keystore url may not be null");
        }
        final URI keystoreUri = keystoreFile.toURI();
        final URL keystoreUrl = keystoreUri.toURL();
        final KeyStore keystore = KeyStore.getInstance("jks");
        InputStream is = null;
        try {
            is = keystoreUrl.openStream();
            keystore.load(is, null == password ? null : password.toCharArray());
        } finally {
            if (null != is) {
                is.close();
            }
        }
        return keystore;
    }

    static KeyPair getKeyPair(final KeyStore keystore,
                                     final String alias, final String password) {
        final Key key = (PrivateKey) keystore.getKey(alias, password.toCharArray());
        final Certificate cert = keystore.getCertificate(alias);
        final PublicKey publicKey = cert.getPublicKey();
        KeyPair keyPair = new KeyPair(publicKey, (PrivateKey) key)
        return keyPair
    }
}

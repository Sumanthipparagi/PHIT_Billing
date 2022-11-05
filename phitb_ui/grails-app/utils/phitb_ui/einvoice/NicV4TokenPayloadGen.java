package phitb_ui.einvoice;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;

public class NicV4TokenPayloadGen {
    Cipher cipher;
    PublicKey publicKey;
    byte[] b;

    public static void main(String[] args) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, IOException
    {
        //To encrypt auth-token payload payload
      /*  String randomAppKey = "8rZqQ01ZEqeoRLqoLgu2vLsT0BMtS7ex";

        String base64EncodedAppKey = Base64.getEncoder().encodeToString(randomAppKey.getBytes());
        System.out.println("base64EncodedAppKey : " + base64EncodedAppKey);
        String authPayload = "{\"UserName\":\"nsdlTest\", \"Password\":\"Test@123\", \"AppKey\":\""+ base64EncodedAppKey +"\", \"ForceRefreshAccessToken\":true}";

        System.out.println("authPayload JSON : " + authPayload);

        String base64EncodedPayload = Base64.getEncoder().encodeToString(authPayload.getBytes());

        System.out.println("base64EncodedPayload : " + base64EncodedPayload);

        //Public Key Path
        byte[] b = readFile("C:\\Users\\arjun\\Desktop\\publicKey.pem.pem");
        NicV4TokenPayloadGen gen = new NicV4TokenPayloadGen(b);

        gen.encryptPayload(base64EncodedPayload);*/


        //Decode Base64
        //System.out.println(new String(Base64.getDecoder().decode("MRRDuEKnjurTKDusjR+R6ajEL6CSM0WAe/7Klfgb5Jw=")));
    }

    public String encryptPayload(String data) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException
    {
        System.out.println("\n@@@inside encryptPayload  " +data);
        String cipherResult = null;
        cipherResult = enryptData(data);
        System.out.println("Encrypted Token Payload : " + cipherResult);
        return cipherResult;
    }

    public String enryptData(String data)
    {
        //System.out.println("@@ Inside enryptData");
        byte[] cipherData = null;
        String cipherResult = null;
        try
        {
            cipherData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            cipherResult = java.util.Base64.getEncoder().encodeToString(cipherData);
        }
        catch (IllegalBlockSizeException | BadPaddingException e)
        {
            e.printStackTrace();
        }
        return cipherResult;
    }

    public NicV4TokenPayloadGen(byte[] b)
    {
        this.b = b;
        init(b);
    }
    void init(byte[] key)
    {
        //System.out.println("@@ inside init...");
        try
        {
            byte[] keyBytes = java.util.Base64.getMimeDecoder().decode(key);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            AsymmetricKeyParameter asymmetricKeyParameter = PublicKeyFactory.createKey(keyBytes);
            RSAKeyParameters rsaKeyParameters = (RSAKeyParameters) asymmetricKeyParameter;
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(rsaKeyParameters.getModulus(), rsaKeyParameters.getExponent());
            publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
            cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static byte[] readFile(String fileName)
    {
        FileInputStream fin = null;
        ByteArrayOutputStream os = null;
        byte[] data = null;
        try
        {
            fin = new FileInputStream(fileName);
            os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int b = -1;
            while ( ( b = fin.read(buffer)) != -1)
            {
                os.write(buffer,0,b);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fin != null) fin.close();
                if(os != null)
                {
                    data = os.toByteArray();
                    os.flush();
                    os.close();
                    //log.debug(new String(data));
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return data;
    }
}

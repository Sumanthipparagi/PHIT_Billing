package phitb_ui.einvoice;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class PayloadEncrypt {
    public static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    public static final String AES_ALGORITHM = "AES";
    public static final int ENC_BITS = 256;
    public static final String CHARACTER_ENCODING = "UTF-8";
    private static Cipher ENCRYPT_CIPHER;
    private static Cipher DECRYPT_CIPHER;
    private static KeyGenerator KEYGEN;
    static byte[] b=null;
    static String appKey="ACEFGHJKLMNPQRUVWXYabcdefijkpro";
    static String sek= "R4AG1PI1dwQNxekm7VCbwrd7LVlzmYNMXaZbq6DZpv4FuEjcixS4IogSbFtU1Bnb";

    static
    {
        try
        {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            ENCRYPT_CIPHER = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            DECRYPT_CIPHER = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            KEYGEN = KeyGenerator.getInstance(AES_ALGORITHM);
            KEYGEN.init(ENC_BITS);

            //sek,app_key
            b = decrypt(sek,appKey.getBytes());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    /*piyushm*/
    //Encrypt Payload
    public static void EncryptPayload() throws Exception
    {
        System.out.println("*************Encrypt Payload*************");

        byte[] payload= readFile("F:\\GST Related Documents\\For e-Way Bill\\cancelEwb.txt");

        String a=new String(java.util.Base64.getEncoder().encode(payload));

        String s = new String(payload);

        byte[] ebill = encryptEK(s, b);
        System.out.println("encrypted_payload:" + new String(org.bouncycastle.util.encoders.Base64.encode(ebill)));
    }

    //Decrypt Payload
	public static void DecryptPayload() throws Exception
	{
		System.out.println("*************Decrypt Payload*************");
		String enc_sample_data = "dwqukd+VBqAAbF/NOSXT2SLHM5XQmWLvwCI9s8VlD2jGOeBL4+TDN3LJVHx5AgQ19CTbrn8CW/tZdm9wI2uP35DJ7dJrcK1eALD7fM8HvYM=";
		byte[] d = decrypt( enc_sample_data, b);
		System.out.println(new String(d));
	}

    //Decode Response
    public static void DecodeError()
    {
        System.out.println("*************Decode Response*************");
        System.out.println(new String(java.util.Base64.getDecoder().decode("eyJlcnJvckNvZGVzIjoiMTA4In0=")));
    }

    //Decrypt Response
    public static void DecryptResponse() throws Exception
    {
        System.out.println("*************Decrypt Response*************");
        String response = "z+mqjh1PmHH30m4oWGbpF4s5FYrG2WAZRbTgdcCgFruJabuBf0+0r4vwG8bPYe9M10WdED7RoOissPra2vOUExYGc/S5UNlltDekNVlDMo0=";
        byte[] result = decrypt( response, b);
        System.out.println(new String(result));
    }

    //Decrypt Get Ewaybill
    public static void DecGetEway() throws Exception
    {
        System.out.println("*************Decrypt Get Ewaybill*************");
        String get_data="TjjKZaf9ZIhKzu4wzEm8bN2FyyIuHqxxXc7mz2ylg8Ura+jfMtbNBDG+V1RJ7UAYKi/OdvX6G/oq2vT+AqLqCk+1ob0D+q7MWhahhKaW0hPelci3/pNdG0BdZkW0QMfGr1lCpcyeNO3ulSsI8LYIdJoekntFfBNEYgWwCQRN5mA=";
        String rek="hQIICcbCnC/gOMd8zNUU1u/SEAMDfzLWWkrIVfL3bc/BWBFTnAvz4O+eaautlI/F";
        byte[] d = decrypt(rek, b);
        String a= (new String (java.util.Base64.getEncoder().encode(d)));
        byte[]  s = decodeBase64StringTOByte(a);
        byte[] fi = decrypt(get_data, d);
        System.out.println("Decrypted Data : "+ new String(fi));
    }

    /**
     * This method is used to encode bytes[] to base64 string.
     *
     * @param bytes
     *            : Bytes to encode
     * @return : Encoded Base64 String
     */
    private static String encodeBase64String(byte[] bytes)
    {
        return new String(java.util.Base64.getEncoder().encode(bytes));
    }
    /**
     * This method is used to decode the base64 encoded string to byte[]
     *
     * @param stringData
     *            : String to decode
     * @return : decoded String
     * @throws UnsupportedEncodingException
     */
    public static byte[] decodeBase64StringTOByte(String stringData) throws Exception
    {
        return java.util.Base64.getDecoder().decode(stringData.getBytes(CHARACTER_ENCODING));
    }

    /**
     * This method is used to encrypt the string which is passed to it as byte[] and return base64 encoded
     * encrypted String
     * @param plainText
     *            : byte[]
     * @param secret
     *            : Key using for encrypt
     * @return : base64 encoded of encrypted string.
     *
     */
    public static byte[] encryptEK(String plainText, byte[] secret)
    {
        try
        {
            SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
            ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, sk);
            byte[] s = ENCRYPT_CIPHER.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * This method is used to decrypt base64 encoded string using an AES 256 bit key.
     *
     * @param plainText
     *            : plain text to decrypt
     * @param secret
     *            : key to decrypt
     * @return : Decrypted String
     * @throws IOException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(String plainText, byte[] secret)throws Exception
    {
        SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
        DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, sk);
        return DECRYPT_CIPHER.doFinal(java.util.Base64.getDecoder().decode(plainText));
    }

    /**
     * This method is used to generate the base64 encoded secure AES 256 key     *
     * @return : base64 encoded secure Key
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private static String generateSecureKey() throws Exception
    {
        SecretKey secretKey = KEYGEN.generateKey();
        return encodeBase64String(secretKey.getEncoded());
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
    public static String encrypt(String strToEncrypt, byte[] secret)
    {
        try
        {
            SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
}

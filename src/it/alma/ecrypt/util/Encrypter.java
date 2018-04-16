package it.alma.ecrypt.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
	
	private static String PASSWORD = "Gr0upam4";
	private static byte [] SALT = null;
	private static String IV = "testaaa012356789";
	private static int iterationCount = 2048;
    private static int keyStrength = 128;
    
	public static String encrypt(String message){

		 String encryptedString = null;
		try {
			SALT = new String("12345678").getBytes("UTF-8");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
			KeySpec keyspec = new PBEKeySpec(PASSWORD.toCharArray(),SALT, iterationCount, keyStrength);
	        SecretKey tmp = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(keyspec);
	        SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");
	        
	        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        c.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

	        byte[] encrypted = c.doFinal(message.getBytes());
	        encryptedString = new String(Base64.getEncoder().encode(encrypted));
	        
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return encryptedString;

    }

}

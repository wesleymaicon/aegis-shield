/*
 * May 27, 2009
 *
 * This source code is provided under the GPL v3 Open Source license agreement.
 */
package com.googlecode.aegisshield.security.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.googlecode.aegisshield.app.utils.Base64Coder;

import android.util.Log;

/**
 * 	Utility class providing encryption and digest functionality for working with sensitive data, such as the
 * account passwords. Service class is final and with static methods.
 * 
 * @author Mihai Campean
 */
public final class CryptoService {
	/**
	 * PBE encryption algorithm.
	 */
	private static final String PBE_ENC_ALGORITHM = "PBEWithMD5AndDES";

	/**
	 * 	8 byte salt used for encryption/decryption.
	 */
	private static final byte[] salt = {
		(byte) 0xc1, (byte) 0xe2, (byte) 0xae, (byte) 0x91,
		(byte) 0xdf, (byte) 0x11, (byte) 0x1f, (byte) 0xaa
	};
	
	/**
	 * 	PBE iteration count.
	 */
	private static final int count = 21;
	
	/**
	 *  Class cannot be instantiated.
	 */
	private CryptoService() {
	}
	
	/**
	 * 	Init the cipher for password based encryption or decryption, based on the mode given as
	 * argument.
	 * 
	 * @param mode
	 * @param password
	 * @return
	 */
	private static Cipher initCipher(int mode, String password) {
		Cipher pbeCipher = null;
		try {
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, count);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ENC_ALGORITHM);
			SecretKey key = keyFactory.generateSecret(keySpec);
			pbeCipher = Cipher.getInstance(PBE_ENC_ALGORITHM);
			pbeCipher.init(mode, key, paramSpec);
		} catch (NoSuchAlgorithmException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (InvalidKeySpecException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (InvalidKeyException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (InvalidAlgorithmParameterException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (NoSuchPaddingException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		return pbeCipher;
	}
	
	/**
	 * 	Uses an MD5 algorithm in order to hash the string given as argument, returning the resulted
	 * hashed string.
	 *  
	 * @param msg
	 * @return the hashed message
	 */
	public static String buildMessageDigest(String msg) {
		String hashCodedMsg = null;
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(msg.getBytes());
			hashCodedMsg = new String(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		
		return hashCodedMsg;
	}
	
	/**
	 *  Encrypts a given string.
	 * 
	 * @param data
	 * @param password
	 */
	public static String encrypt(String data, String password) {
		String encData = null;
		Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, password);
		
		try {
			
			encData = Base64Coder.encodeString(new String(cipher.doFinal(password.getBytes())));
		} catch (IllegalBlockSizeException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (BadPaddingException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		
		return encData;
	}
	
	/**
	 *  Decrypts a given string.
	 * 
	 * @param encData
	 * @param password
	 * @return
	 */
	public static String decrypt(String encData, String password) {
		String data = null;
		Cipher cipher = initCipher(Cipher.DECRYPT_MODE, password);
		
		try {
			data = new String(cipher.doFinal(Base64Coder.decodeString(encData).getBytes()));
		} catch (IllegalBlockSizeException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (BadPaddingException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		
		return data;
	}
}

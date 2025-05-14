package com.cafeapp.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Pranav_Sharma LMU ID: 23048577 Utility class for AES encryption and
 *         decryption of passwords using AES-GCM with key derivation via PBKDF2
 *         and HMAC-SHA256.
 */
public class PasswordUtil {
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

	private static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
	private static final int IV_LENGTH_BYTE = 12;
	private static final int SALT_LENGTH_BYTE = 16;
	private static final Charset UTF_8 = StandardCharsets.UTF_8;

	/**
	 * Generates a random byte array of the specified size using a secure random
	 * generator.
	 *
	 * @param numBytes Number of bytes to generate.
	 * @return Byte array of random values.
	 */
	public static byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	/**
	 * Generates a new AES secret key of the specified size.
	 *
	 * @param keysize Size in bits (e.g., 128, 192, 256).
	 * @return AES SecretKey object.
	 * @throws NoSuchAlgorithmException if AES algorithm is not available.
	 */
	public static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keysize, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}

	/**
	 * Derives an AES key from a given password and salt using PBKDF2WithHmacSHA256.
	 *
	 * @param password The password.
	 * @param salt     The salt.
	 * @return Derived AES SecretKey or null if an error occurs.
	 */
	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			// iterationCount = 65536
			// keyLength = 256
			KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
			SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return secret;
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidKeySpecException ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * Encrypts a password using AES-GCM and encodes the result as a Base64 string.
	 *
	 * @param username The username to use as the encryption key base.
	 * @param password The password to encrypt.
	 * @return Encrypted password as Base64 string or null if encryption fails.
	 */
	public static String encrypt(String username, String password) {
		try {
			// 16 bytes salt
			byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);

			// GCM recommended 12 bytes iv?
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);

			// secret key from password
			SecretKey aesKeyFromPassword = getAESKeyFromPassword(username.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			// ASE-GCM needs GCMParameterSpec
			cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] cipherText = cipher.doFinal(password.getBytes());

			// prefix IV and Salt to cipher text
			byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv)
					.put(salt).put(cipherText).array();

			// string representation, base64, send this string to other for decryption.
			return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * Decrypts an AES-GCM encrypted Base64 password string using the given
	 * username.
	 *
	 * @param encryptedPassword The Base64 encoded encrypted password.
	 * @param username          The username used during encryption.
	 * @return Decrypted password as plain text or null if decryption fails.
	 */
	public static String decrypt(String encryptedPassword, String username) {
		try {
			byte[] decode = Base64.getDecoder().decode(encryptedPassword.getBytes(UTF_8));

			// get back the iv and salt from the cipher text
			ByteBuffer bb = ByteBuffer.wrap(decode);

			byte[] iv = new byte[IV_LENGTH_BYTE];
			bb.get(iv);

			byte[] salt = new byte[SALT_LENGTH_BYTE];
			bb.get(salt);

			byte[] cipherText = new byte[bb.remaining()];
			bb.get(cipherText);

			// get back the aes key from the same password and salt
			SecretKey aesKeyFromPassword = PasswordUtil.getAESKeyFromPassword(username.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] plainText = cipher.doFinal(cipherText);

			return new String(plainText, UTF_8);
		} catch (Exception ex) {
			return null;
		}

	}

}
package net.Meta;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MetaEncryptor {
	public static KeyPair KeyGenerator() {
		KeyPairGenerator keygen = null;
		
		try {
			keygen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		assert keygen != null;
		keygen.initialize(1024);
		MetaConsole.out("Encryptionkeys generated.", 0, true);
		return keygen.generateKeyPair();
	}
	
	public static PublicKey parsePublicKey(String PublicKey) {
		X509EncodedKeySpec spec = new X509EncodedKeySpec(PublicKey.getBytes());
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePublic(spec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static PrivateKey parsePrivateKey(String PrivateKey) {
		X509EncodedKeySpec spec = new X509EncodedKeySpec(PrivateKey.getBytes());
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(spec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static byte[] Encrypt(String Content, PublicKey PublicKey) {
		byte[] Chiffrat = null;
		Cipher cipher = null;
		
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, PublicKey);
			Chiffrat = cipher.doFinal(Content.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return Chiffrat;
	}
	
	public static String Decrypt(byte[] Content, PrivateKey PrivateKey) {
		byte[] dec = null;
		Cipher cipher = null;
		
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, PrivateKey);
			dec = cipher.doFinal(Content);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e1) {
			e1.printStackTrace();
		}

		assert dec != null;
		return new String(dec);
	}
}
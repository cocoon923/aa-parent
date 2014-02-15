package cn.iaa.core.code;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

import cn.iaa.core.log.LogUtil;

public class DesCode {

	public static String encrypt(String data, String key) {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = Base64.encodeBase64String(bt);
		return strs;
	}

	public static byte[] encrypt(byte[] datasource, byte[] password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			LogUtil.error(DesCode.class, "Des crypto Error", e);
		}
		return null;
	}

	public static String decrypt(String data, String key) {
		if (data == null)
			return null;
		byte[] buf = Base64.decodeBase64(data);
		byte[] bt = decrypt(buf, key.getBytes());
		return new String(bt);
	}

	public static byte[] decrypt(byte[] src, byte[] password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			return cipher.doFinal(src);
		} catch (Exception e) {
			LogUtil.error(DesCode.class, "Des decrypto Error", e);
		}
		return null;
	}

}

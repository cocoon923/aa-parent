package cn.iaa.core.code;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import cn.iaa.core.log.LogUtil;

public class DesCode {

	public static byte[] crypto(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
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

	public static String crypto(String datasource, String password) {
		return new String(crypto(datasource.getBytes(), password));
	}

	public static byte[] decrypt(byte[] src, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
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

	public static String decrypt(String src, String password) {
		return new String(decrypt(src.getBytes(), password));
	}

}

package cn.iaa.util.zxing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.iaa.core.log.LogUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ZxingUtil {

	/**
	 * Defalut don't use UTF-8 to encode.
	 * 
	 * @param path
	 * @param name
	 * @param content
	 * @param width
	 * @param height
	 * @param urlPrefix
	 * @return
	 */
	@Deprecated
	public static String generateCode(String path, String name, String content, int width, int height, String urlPrefix) {
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

			File file1 = new File(path + name + ".jpg");
			LogUtil.debug(ZxingUtil.class, "Image Location:" + name + ".jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
			LogUtil.debug(ZxingUtil.class, "Generate Code " + content + " Success!");
			return urlPrefix + name;
		} catch (Exception e) {
			LogUtil.error(ZxingUtil.class, "Generate Code Fail!", e);
			return "";
		}
	}

	public static String generateCode(String path, String name, String content, int width, int height,
			String urlPrefix, boolean isUTF8) {
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			if (isUTF8) {
				hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			}
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			File file1 = new File(path + name + ".jpg");
			LogUtil.debug(ZxingUtil.class, "Image Location:" + name + ".jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
			LogUtil.debug(ZxingUtil.class, "Generate Code " + content + " Success!");
			return urlPrefix + name;
		} catch (Exception e) {
			LogUtil.error(ZxingUtil.class, "Generate Code Fail!", e);
			return "";
		}
	}

	public static void deleteCode(String path, String name) {
		File file1 = new File(path + name + ".jpg");
		if (file1.exists()) {
			file1.delete();
		}
	}

}

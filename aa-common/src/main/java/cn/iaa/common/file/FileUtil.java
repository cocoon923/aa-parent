package cn.iaa.common.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.iaa.common.logging.LogUtil;

public class FileUtil {

	public static Properties loadFile(String file) throws IOException {
		LogUtil.debug(FileUtil.class, "Load File <" + file + ">");
		try {
			FileInputStream fin = new FileInputStream(file);
			Properties p = new Properties();
			p.load(fin);
			return p;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static Properties loadPropertiesFile(String file) throws IOException {
		LogUtil.debug(FileUtil.class, "Load Properties File <" + file + ">");
		InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(file);
		Properties p = new Properties();
		p.load(in);
		return p;
	}

}

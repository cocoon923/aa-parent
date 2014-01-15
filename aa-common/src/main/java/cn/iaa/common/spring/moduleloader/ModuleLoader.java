package cn.iaa.common.spring.moduleloader;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.iaa.common.file.FileUtil;
import cn.iaa.common.logging.LogUtil;
import cn.iaa.common.string.StringUtil;

public abstract class ModuleLoader {

	/**
	 * Application Context of Module.
	 */
	protected ApplicationContext atx;

	/**
	 * Properties of Module.
	 */
	protected Properties properties;

	public ModuleLoader(String configLocation) {
		ApplicationContext atx = new ClassPathXmlApplicationContext(configLocation);
		this.atx = atx;
	}

	public Properties loadContants(String moduleName) throws Exception {
		Properties properties = null;
		try {
			String path = getPropertiesPath();
			if (StringUtil.assertNotNull(path)) {
				properties = FileUtil.loadPropertiesFile(path);
			}
		} catch (Exception e) {
			LogUtil.error(getClass(), "Load Properties File Error", e);
			throw new Exception(e);
		}
		return properties;
	}

	/**
	 * Implement this method to locate the path of properties file. <br>
	 * 
	 * Don't load properties for module if return <code>null</code>.
	 * 
	 * @return The path of properties file
	 */
	public abstract String getPropertiesPath();

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public ApplicationContext getAtx() {
		return atx;
	}

	public void setAtx(ApplicationContext atx) {
		this.atx = atx;
	}

}

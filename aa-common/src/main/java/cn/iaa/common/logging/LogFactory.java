package cn.iaa.common.logging;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import cn.iaa.common.string.StringUtil;

/**
 * The <code>LogFactory</code> is a utility class load log xml file. Support
 * Logback.
 * 
 * @author chenmm
 * 
 */
public class LogFactory {

	private String property;
	private String location;

	private static LogFactory instance;

	private LogFactory() {
	}

	/**
	 * Load log xml file and initial Logback.
	 * 
	 * @param property
	 * @param location
	 * @throws Exception
	 */
	public static void init(String property, String location) throws Exception {
		LogFactory initial = (instance == null ? new LogFactory() : instance);
		initial.property = property;
		initial.location = location;
		initial.initLogback();
	}

	private void initLogback() throws Exception {
		try {
			LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();

			String configFilePath = System.getenv(property);
			if (StringUtil.assertNotNull(configFilePath)) {
				configurator.doConfigure(configFilePath);
				return;
			}

			configFilePath = System.getProperty(property);
			if (StringUtil.assertNotNull(configFilePath)) {
				configurator.doConfigure(configFilePath);
				return;
			}

			configurator.doConfigure(location);
		} catch (Exception e) {
			throw new Exception("Initial Logback Fail", e);
		}
	}

}

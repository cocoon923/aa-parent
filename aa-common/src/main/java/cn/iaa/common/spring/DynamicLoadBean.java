package cn.iaa.common.spring;

import java.io.ByteArrayInputStream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import cn.iaa.common.logging.LogUtil;
import cn.iaa.common.string.StringUtil;

public class DynamicLoadBean implements ApplicationContextAware {
	
	private ConfigurableApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}

	public ConfigurableApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void loadBeanFromXML(StringBuffer beanXML) {
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
				(BeanDefinitionRegistry) getApplicationContext().getBeanFactory());
		beanDefinitionReader.setValidationMode(0);
		beanDefinitionReader.setResourceLoader(getApplicationContext());
		beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(getApplicationContext()));
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(beanXML.toString().getBytes(
					StringUtil.getDefaultCharset()));
			Resource inputResource = new InputStreamResource(is);
			LogUtil.debug(getClass(), "Load bean number:" + beanDefinitionReader.loadBeanDefinitions(inputResource));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

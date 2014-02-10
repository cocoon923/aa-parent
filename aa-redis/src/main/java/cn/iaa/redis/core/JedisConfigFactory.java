package cn.iaa.redis.core;

import org.springframework.beans.factory.FactoryBean;

public class JedisConfigFactory implements FactoryBean<JedisConfig> {

	@Override
	public JedisConfig getObject() {
		return JedisBuilder.getJedisConfig();
	}

	@Override
	public Class<?> getObjectType() {
		return JedisConfig.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

package cn.iaa.redis.core;

import org.springframework.beans.factory.FactoryBean;

public class JedisConnectionConfigFactory implements FactoryBean<JedisConnectionConfig> {

	@Override
	public JedisConnectionConfig getObject() {
		return JedisBuilder.getJedisConnectionConfig();
	}

	@Override
	public Class<?> getObjectType() {
		return JedisConnectionConfig.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

package cn.iaa.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;
import cn.iaa.redis.core.JedisConfig;
import cn.iaa.redis.core.JedisConnectionConfig;

public class ConfigBuilder {

	private ApplicationContext applicationContext;

	public ConfigBuilder() {
		this.applicationContext = new AnnotationConfigApplicationContext(Config.class);
	}

	public ConfigBuilder(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public JedisConfig getJedisConfig() {
		return applicationContext.getBean(JedisConfig.class);
	}

	public JedisConnectionConfig getJedisConnectionConfig() {
		return applicationContext.getBean(JedisConnectionConfig.class);
	}

	public JedisPoolConfig getJedisPoolConfig() {
		return applicationContext.getBean(JedisPoolConfig.class);
	}

	public JedisConnectionFactory getJedisConnectionFactory() {
		return applicationContext.getBean(JedisConnectionFactory.class);
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return applicationContext.getBean(StringRedisTemplate.class);
	}
}

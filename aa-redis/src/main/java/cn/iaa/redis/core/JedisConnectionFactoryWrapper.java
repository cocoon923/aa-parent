package cn.iaa.redis.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactoryWrapper implements FactoryBean<JedisConnectionFactory> {

	private JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	private JedisConnectionConfig jedisConnectionConfig = new JedisConnectionConfig();

	public JedisConnectionFactoryWrapper(JedisConnectionConfig jedisConnectionConfig, JedisPoolConfig jedisPoolConfig) {
		if (jedisConnectionConfig != null) {
			this.jedisConnectionConfig = jedisConnectionConfig;
		}
		if (jedisPoolConfig != null) {
			this.jedisPoolConfig = jedisPoolConfig;
		} else {
			this.jedisPoolConfig.setMaxActive(300);
			this.jedisPoolConfig.setMaxIdle(100);
			this.jedisPoolConfig.setMaxWait(1000);
			this.jedisPoolConfig.setTestOnBorrow(true);
		}
	}

	@Override
	public JedisConnectionFactory getObject() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		// Set Property JedisPoolConfig.
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(jedisConnectionConfig.getHostname());
		jedisConnectionFactory.setPort(jedisConnectionConfig.getPort());
		jedisConnectionFactory.setTimeout(jedisConnectionConfig.getTimeout());
		if (jedisConnectionConfig.hasPassword()) {
			jedisConnectionFactory.setPassword(jedisConnectionConfig.getPassword());
		}
		return jedisConnectionFactory;
	}

	@Override
	public Class<?> getObjectType() {
		return JedisConnectionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

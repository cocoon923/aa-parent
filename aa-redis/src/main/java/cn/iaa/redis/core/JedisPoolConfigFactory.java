package cn.iaa.redis.core;

import java.util.Map.Entry;

import org.springframework.beans.factory.FactoryBean;

import redis.clients.jedis.JedisPoolConfig;
import cn.iaa.redis.core.JedisConfig.Field;

public final class JedisPoolConfigFactory implements FactoryBean<JedisPoolConfig> {

	private JedisConfig jedisConfig;

	public JedisPoolConfigFactory(JedisConfig jedisConfig) {
		this.jedisConfig = jedisConfig;
	}

	@Override
	public JedisPoolConfig getObject() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		for (Entry<String, String> entry : jedisConfig.entrySet()) {
			Field field = Field.valueOf(entry.getKey());
			if (field != null) {
				switch (field) {
				case maxIdle:
					jedisPoolConfig.setMaxIdle(Integer.parseInt(entry.getValue()));
					break;
				case minIdle:
					jedisPoolConfig.setMinIdle(Integer.parseInt(entry.getValue()));
					break;
				case maxActive:
					jedisPoolConfig.setMaxActive(Integer.parseInt(entry.getValue()));
					break;
				case maxWait:
					jedisPoolConfig.setMaxWait(Long.parseLong(entry.getValue()));
					break;
				case whenExhaustedAction:
					jedisPoolConfig.setWhenExhaustedAction(Byte.parseByte(entry.getValue()));
					break;
				case testOnBorrow:
					jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(entry.getValue()));
					break;
				case testOnReturn:
					jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(entry.getValue()));
					break;
				case testWhileIdle:
					jedisPoolConfig.setTestWhileIdle(Boolean.parseBoolean(entry.getValue()));
					break;
				case timeBetweenEvictionRunsMillis:
					jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Long.parseLong(entry.getValue()));
					break;
				case numTestsPerEvictionRun:
					jedisPoolConfig.setNumTestsPerEvictionRun(Integer.parseInt(entry.getValue()));
					break;
				case minEvictableIdleTimeMillis:
					jedisPoolConfig.setMinEvictableIdleTimeMillis(Long.parseLong(entry.getValue()));
					break;
				case softMinEvictableIdleTimeMillis:
					jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(Long.parseLong(entry.getValue()));
					break;
				default:
					break;
				}
			}
		}
		return jedisPoolConfig;
	}

	@Override
	public Class<?> getObjectType() {
		return JedisPoolConfig.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

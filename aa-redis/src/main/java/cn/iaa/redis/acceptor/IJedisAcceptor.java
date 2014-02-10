package cn.iaa.redis.acceptor;

import org.springframework.data.redis.core.StringRedisTemplate;

import cn.iaa.redis.core.JedisConfig;
import cn.iaa.redis.core.JedisConnectionConfig;

/**
 * Accept {@link JedisConfig} to initial redis connection.
 * 
 * @author chenmm
 * 
 */
public interface IJedisAcceptor {

	public StringRedisTemplate accept(JedisConnectionConfig jedisConnectionConfig);

	public StringRedisTemplate accept(JedisConnectionConfig jedisConnectionConfig, JedisConfig jedisConfig);

}

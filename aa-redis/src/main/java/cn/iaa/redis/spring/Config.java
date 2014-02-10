package cn.iaa.redis.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;
import cn.iaa.redis.core.JedisConfig;
import cn.iaa.redis.core.JedisConfigFactory;
import cn.iaa.redis.core.JedisConnectionConfig;
import cn.iaa.redis.core.JedisConnectionConfigFactory;
import cn.iaa.redis.core.JedisConnectionFactoryWrapper;
import cn.iaa.redis.core.JedisPoolConfigFactory;

@Configuration
public class Config {

	@Bean
	public JedisConfig jedisConfig() {
		return (new JedisConfigFactory()).getObject();
	}

	@Bean
	public JedisConnectionConfig jedisConnectionConfig() {
		return (new JedisConnectionConfigFactory()).getObject();
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		return (new JedisPoolConfigFactory(jedisConfig())).getObject();
	}

	@Bean(destroyMethod = "destroy")
	public JedisConnectionFactory jedisConnectionFactory() {
		return (new JedisConnectionFactoryWrapper(jedisConnectionConfig(), jedisPoolConfig())).getObject();
	}

	@Bean
	public StringRedisTemplate getRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
		return stringRedisTemplate;
	}

}

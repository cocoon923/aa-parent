package cn.iaa.redis.acceptor;

import org.springframework.data.redis.core.StringRedisTemplate;

import cn.iaa.redis.core.JedisConfig;
import cn.iaa.redis.core.JedisBuilder;
import cn.iaa.redis.core.JedisConnectionConfig;
import cn.iaa.redis.spring.ConfigBuilder;

public class JedisAcceptor implements IJedisAcceptor {

	private JedisAcceptor() {
	}

	private final static JedisAcceptor ACCEPTOR = new JedisAcceptor();

	public static JedisAcceptor getAcceptor() {
		return ACCEPTOR;
	}

	@Override
	public StringRedisTemplate accept(JedisConnectionConfig jedisConnectionConfig) {
		return accept(jedisConnectionConfig, null);
	}

	@Override
	public StringRedisTemplate accept(JedisConnectionConfig jedisConnectionConfig, JedisConfig jedisConfig) {
		JedisBuilder.build(jedisConnectionConfig, jedisConfig);
		return (new ConfigBuilder()).getStringRedisTemplate();
	}

}

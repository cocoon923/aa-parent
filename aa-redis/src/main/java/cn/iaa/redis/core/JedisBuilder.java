package cn.iaa.redis.core;

public final class JedisBuilder {

	private static JedisConfig jedisConfig = new JedisConfig();
	private static JedisConnectionConfig jedisConnectionConfig = new JedisConnectionConfig();

	public static JedisConnectionConfig getJedisConnectionConfig() {
		return jedisConnectionConfig;
	}

	public static JedisConfig getJedisConfig() {
		return jedisConfig;
	}

	public static void build(JedisConnectionConfig jedisConnectionConfig, JedisConfig jedisConfig) {
		if (jedisConnectionConfig != null) {
			JedisBuilder.jedisConnectionConfig = jedisConnectionConfig;
		}
		if (jedisConfig != null) {
			JedisBuilder.jedisConfig = jedisConfig;
		}
	}

}

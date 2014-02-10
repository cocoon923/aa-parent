package cn.iaa.redis.core;

import cn.iaa.core.json.JSONBean;

/**
 * Contains parameters of redis configuration.
 * 
 * @author chenmm
 * 
 */
public final class JedisConfig extends JSONBean {

	private static final long serialVersionUID = 448701507709492621L;

	public String put(Field field, String value) {
		return super.put(field.toString(), value);
	}

	public String get(Field field) {
		return this.get(field.toString());
	}

	@Override
	public String put(String key, String value) {
		Field field = Field.valueOf(key);
		if (field != null) {
			return this.put(field, value);
		}
		return null;
	}

	/**
	 * Define parameters' enumeration of redis configuration.
	 * 
	 * @author chenmm
	 * 
	 */
	enum Field {
		maxIdle, minIdle, maxActive, maxWait, whenExhaustedAction, testOnBorrow, testOnReturn, testWhileIdle, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, softMinEvictableIdleTimeMillis
	}

}

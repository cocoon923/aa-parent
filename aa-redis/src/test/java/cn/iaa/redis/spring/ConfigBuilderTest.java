package cn.iaa.redis.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

public class ConfigBuilderTest extends UnitilsJUnit4 {

	@SpringApplicationContext("cn/iaa/redis/spring/applicationContext.xml")
	private ApplicationContext atx;

	@SpringBeanByType
	private ConfigBuilder configBuilder;

	@Test
	public void testConfigBuilder() {
		Assert.assertNotNull(configBuilder);
		Assert.assertNotNull(configBuilder.getJedisConfig());
		Assert.assertNotNull(configBuilder.getJedisPoolConfig());
		Assert.assertNotNull(configBuilder.getJedisConnectionConfig());
		Assert.assertNotNull(configBuilder.getJedisConnectionFactory());
		Assert.assertNotNull(configBuilder.getStringRedisTemplate());
	}

}

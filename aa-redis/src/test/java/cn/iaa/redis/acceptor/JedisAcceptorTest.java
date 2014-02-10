package cn.iaa.redis.acceptor;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.unitils.UnitilsJUnit4;

public class JedisAcceptorTest extends UnitilsJUnit4 {

	@Test
	public void testJedisAcceptor() {
		StringRedisTemplate template = JedisAcceptor.getAcceptor().accept(null);
		Assert.assertNotNull(template);
	}

}

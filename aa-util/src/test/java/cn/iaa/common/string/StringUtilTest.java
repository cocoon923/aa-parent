package cn.iaa.common.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.iaa.core.StringUtil;

public class StringUtilTest {

	@Test
	public void testAssertNotNull() {
		assertTrue(StringUtil.assertNotNull("123"));
		assertFalse(StringUtil.assertNotNull(""));
		assertTrue(StringUtil.assertNotNull("123", "123"));
		assertFalse(StringUtil.assertNotNull("", ""));
		assertFalse(StringUtil.assertNotNull("", "123"));
	}

	@Test
	public void testIsDigit() {
		assertTrue(StringUtil.isDigit("123424"));
		assertFalse(StringUtil.isDigit("asdfasd23324"));
		assertFalse(StringUtil.isDigit(""));
	}

}

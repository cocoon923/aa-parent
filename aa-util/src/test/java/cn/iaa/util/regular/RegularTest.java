package cn.iaa.util.regular;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.iaa.core.Regular;

public class RegularTest {

	@Test
	public void test() {
		assertTrue(Regular.emailReg("asdflas@wqe.coma"));
		assertTrue(Regular.phoneReg("15088603496"));
		assertTrue(Regular.phoneReg("0571-31801133"));
		assertTrue(Regular.phoneReg("0571-31801133-123"));
		assertTrue(Regular.phoneReg("571-3180133"));
	}

}

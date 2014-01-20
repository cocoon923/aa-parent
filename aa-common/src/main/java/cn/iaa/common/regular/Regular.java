package cn.iaa.common.regular;

import static cn.iaa.common.consts.Expression.EMAIL;
import static cn.iaa.common.consts.Expression.PHONE;

import java.util.regex.Pattern;

public class Regular {

	/**
	 * Regulation Email Address.
	 * 
	 * @param email
	 * @return regular or not
	 */
	public static boolean emailReg(String email) {
		Pattern pattern = Pattern.compile(EMAIL);
		return pattern.matcher(email).matches();
	}

	/**
	 * Regulation Phone Number. (Support 11 Telephone Number, 3-4 Area Code, 7-8
	 * Live Number, 1-4 Extension Number)
	 * 
	 * @param phone
	 * @return regular or not
	 */
	public static boolean phoneReg(String phone) {
		Pattern pattern = Pattern.compile(PHONE);
		return pattern.matcher(phone).matches();
	}

}

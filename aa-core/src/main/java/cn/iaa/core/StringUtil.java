package cn.iaa.core;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {

	private static ConcurrentHashMap<String, Charset> charsetHashMap = new ConcurrentHashMap<String, Charset>();
	private static String systemDefaultCharsetEncode = System.getProperty("file.encoding");

	private static Charset getCharset(String en) {
		if (charsetHashMap.get(en) == null) {
			charsetHashMap.put(en, Charset.forName(en));
		}
		return charsetHashMap.get(en);
	}

	/**
	 * Get Default Charset
	 * 
	 * @return {@link Charset}
	 */
	public static Charset getDefaultCharset() {
		return getCharset(systemDefaultCharsetEncode);
	}

	/**
	 * Get Now Date on Format "yyyyMMddHHmmss"
	 * 
	 * @return {@link String}
	 */
	public static String getNowDate() {
		return (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
	}

	public static String getBirthdayStr(Date birthday) {
		return (new SimpleDateFormat("yyyy-MM-dd").format(birthday));
	}

	public static Date getBirthday(String birthday) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * Change the Object to String.
	 * 
	 * @param obj
	 * @return {@link String}
	 */
	public static String ToBeString(Object obj) {
		if (obj != null)
			return obj.toString();
		else {
			return "";
		}
	}

	/**
	 * Determine whether the string is empty.
	 * 
	 * @param str
	 * @return {@link Boolean}
	 */
	public static boolean assertNotNull(String str) {
		boolean notnull = false;
		if (str != null && !str.equals("") && !str.equals("null"))
			return true;
		return notnull;
	}

	/**
	 * If any one of the string is empty, return false.
	 * 
	 * @param strs
	 * @return {@link Boolean}
	 */
	public static boolean assertNotNull(String... strs) {
		for (String s : strs) {
			if (!assertNotNull(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * If any one of the string is empty, return false.
	 * 
	 * @param strs
	 * @return {@link Boolean}
	 */
	public static boolean assertNotNull(Collection<String> strs) {
		for (String s : strs) {
			if (!assertNotNull(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determine whether a string is a number.
	 * 
	 * @param str
	 * @return {@link Boolean}
	 */
	public static boolean isDigit(String str) {
		if (!assertNotNull(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static String getSerialNo() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	public static String getThisDayStr() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		return sdf.format(cal.getTime());
	}

	public static String getThisDayStr(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

}

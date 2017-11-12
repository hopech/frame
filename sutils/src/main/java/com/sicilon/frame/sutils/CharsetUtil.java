package com.sicilon.frame.sutils;

import java.io.UnsupportedEncodingException;

/**
 * 
 * description：字符编码转换工具类
 * ClassName: CharsetUtil <br/> 
 * date: 2017年4月10日 下午5:14:58 <br/> 
 * @author chen
 */
public class CharsetUtil {

	public static final String US_ASCII = "US-ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	public static final String UTF_16BE = "UTF-16BE";
	public static final String UTF_16LE = "UTF-16LE";
	public static final String UTF_16 = "UTF-16";
	public static final String GBK = "GBK";

	public CharsetUtil() {
	}

	/**
	 * 将平台默认编码字符串,转换为ASCII编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toASCII(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "US-ASCII");
	}

	/**
	 * 将平台默认编码字符串,转换为ISO-8859-1编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "ISO-8859-1");
	}

	/**
	 * 将平台默认编码字符串,转换为UTF-8编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toUTF_8(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "UTF-8");
	}

	/**
	 * 将平台默认编码字符串,转换为UTF-16BE编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toUTF_16BE(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "UTF-16BE");
	}

	/**
	 * 将平台默认编码字符串,转换为"UTF-16LE编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toUTF_16LE(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "UTF-16LE");
	}

	/**
	 * 将平台默认编码字符串,转换为"UTF-16编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toUTF_16(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "UTF-16");
	}

	/**
	 * 将平台默认编码字符串,转换为GBK编码
	 * @param str 目标字符串
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String toGBK(String str) throws UnsupportedEncodingException {
		return changeCharset(str, "GBK");
	}

	/**
	 * 将字符串默认的平台编码 转换为新的编码
	 * @param str 目标字符串
	 * @param newCharset 新编码
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			byte bs[] = str.getBytes();
			return new String(bs, newCharset);
		} else {
			return null;
		}
	}

	/**
	 * 将已知字符串编码转换为新的字符编码
	 * @param str 目标字符串
	 * @param oldCharset 旧编码
	 * @param newCharset 新编码
	 * @return 转换编码后的字符串
	 * @throws UnsupportedEncodingException 不支持的编码异常
	 */
	public static String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			byte bs[] = str.getBytes(oldCharset);
			return new String(bs, newCharset);
		} else {
			return null;
		}
	}

}

package com.sicilon.frame.sutils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * 
 * description：字符串工具类
 * ClassName: StrUtil <br/> 
 * date: 2017年3月31日 下午5:16:03 <br/> 
 * @author chen
 */
public class SecurityUtil {

	public static String SQL_VALID_REG = "[\\s\\S]*(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)[\\s\\S]*" + "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	
	public static String SPCIAL_CHAR = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	

	/**
	 * @category 校验是否有sql注入
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isValidSql(String str) {
		String[] matchs = RegExUtil.matchAll(str, SQL_VALID_REG, 0);
		if (matchs.length > 0) {
			System.err.println("发现sql注入攻击：" + str + ">>" + Arrays.asList(matchs).toString());
			return false;
		}
		return true;
	}

	/**
	 * 过滤特殊字符
	 * @param str 要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String stringFlilter(String str) {
		Pattern p = Pattern.compile(SPCIAL_CHAR);
		Matcher m = p.matcher(str);
		return m.replaceAll("").replace(" ", "");
	}

	
	
	/**
	 * 检查是否存在特殊字符
	 * @param str 要检查的字符串
	 * @return 真假值 true存在 false不存在
	 */
	public static boolean existSpeicialChar(String str) {
		String[] matchs = RegExUtil.matchAll(str, SPCIAL_CHAR, 0);
		if (matchs.length > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 使用对称密钥进行加密 返回加密后的密文
	 * @param aesKey 密钥
	 * @param mingwen 明文
	 * @return 密文
	 * @throws Exception 异常信息
	 */
	public static String getA231(String aesKey, String mingwen)
			throws Exception {
		byte[] keyb = StringUtil.hex2bytes(aesKey);
		SecretKeySpec sKeySpec = new SecretKeySpec(keyb, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
		byte[] miwen = cipher.doFinal(mingwen.getBytes());
		return StringUtil.bytes2Hex(miwen);
	}

	/**
	 * 使用对称密钥进行解密 返回解密后的明文
	 * @param aesKey 密钥
	 * @param miwen 密文
	 * @return 明文
	 * @throws Exception 异常信息
	 */
	public static String getA232(String aesKey, String miwen) throws Exception {
		byte[] keyb = StringUtil.hex2bytes(aesKey);
		byte[] miwenBytes = StringUtil.hex2bytes(miwen);
		SecretKeySpec sKeySpec = new SecretKeySpec(keyb, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
		byte[] bjiemihou = cipher.doFinal(miwenBytes);
		return new String(bjiemihou);
	}
	
}

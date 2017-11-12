package com.sicilon.frame.sutils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * 
 * description：字符串工具类
 * ClassName: StringUtil <br/> 
 * date: 2017年4月10日 上午10:44:45 <br/> 
 * @author chen
 */
public class StringUtil {
	
	public static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 判断字符串是否不为空
	 * @param parm 输入的字符串
	 * @return 判断结果 true false
	 */
	public static boolean isNotBlank(Object parm) {
		if (!isBlank(parm)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param parm 输入的字符串
	 * @return 判断结果 true false
	 */
	public static boolean isBlank(Object parm) {
		if (parm == null) {
			return true;
		}
		return parm.toString().trim().length() <= 0;
	}
	
	/**
	 * 判断传入字符串是否包含空
	 * @param parm 字符串
	 * @return 真假值
	 */
	public static boolean hasBlank(String ...parm) {
		for (int i = 0; i < parm.length; i++) {
			if(parm[i] == null || parm[i].toString().trim().length() <= 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断传入字符串是否不包含空
	 * @param parm 字符串
	 * @return 真假值
	 */
	public static boolean hasNoBlank(String ...parm){
		return !hasBlank(parm);
	}
	
	/**
	 * 将字符串的首字母大写
	 * @param str 要处理的字符串
	 * @return 转换后的字符串
	 */
	public static String firstToUppercase(String str) {
		
		if (isBlank(str))
			return str;
		char[] chars = str.toCharArray();
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] = (char) (chars[0] - 32);
		}
		return new String(chars);
	}
	
	/**
	 * @category 首字母小写
	 * @param str 要处理的字符串
	 * @return 转换后的字符串
	 */
	public static String firstToLowerCase(String str) {
		if (isBlank(str))
			return str;
		char[] chars = str.toCharArray();
		if (chars[0] >= 'A' && chars[0] <= 'Z') {
			chars[0] = (char) (chars[0] + 32);
		}
		return new String(chars);
	}
	
	/**
	 * 判断字符串数组是否包含某元素
	 * @param substring 包含的元素
	 * @param source 字符串数组 
	 * @return 判断结果 true false
	 */
	public static boolean isIn(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}
		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取数UUID
	 * @return UUID字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 判断两个字符串是否相等
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 判断结果 true false
	 */
	public static boolean equals(String str1,String str2){
		if(str1!= null && str2 != null){
			return str1.equals(str2);
		}
		return false;
	}
	
	/**
	 * 判断两个字符串是否相等,忽略大小写
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 判断结果 true false
	 */
	public static boolean equalsIgnoreCase(String str1,String str2){
		if(str1!= null && str2 != null){
			return str1.equalsIgnoreCase(str2);
		}
		return false;
	}
	
	/**
	 * 判断字符串是否 全部为汉字
	 * @param str 要判断的字符串
	 * @return 判断结果 true false
	 */
	public static boolean isNum(String str){
		str = str.trim().replaceAll("[0-9]+", "");
		if (str.length() == 0)
			return true;

		return false;
	}
	
	/**
	 * 对sql语句，传入参数中的特殊字符做转义
	 * @param str sql语句字符串
	 * @return 转义后的字符串
	 */
	public static String escape(String str) {
		String s = str;
		if (str.indexOf("'") > -1) {
			s = str.replace("'", "''");
		}
		if (str.indexOf("%") > -1) {
			s = s.replace("%", "\\%");
		}
		if (str.indexOf("_") > -1) {
			s = s.replace("_", "\\_");
		}
		if (str.indexOf("[") > -1) {
			s = s.replace("[", "\\[");
		}
		if (str.indexOf("]") > -1) {
			s = s.replace("]", "\\]");
		}
		if (str.indexOf("^") > -1) {
			s = s.replace("^", "\\^");
		}
		return s;
	}
	
	/**
	 * 将字节数组转化为十六进制字符串
	 * @param bytes 字节数组
	 * @return 16进制字符串
	 */
	public static final String bytes2Hex(byte[] bytes) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buf.append(bcdLookup[(bytes[i] >>> 4) & 0x0f]);
			buf.append(bcdLookup[bytes[i] & 0x0f]);
		}
		return buf.toString();
	}

	/**
	 * 将十六进制字符串转换为字节数组
	 * @param s 16进制字符串
	 * @return 字节数组
	 */
	public static final byte[] hex2bytes(String s) {
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}
	
	
	/**
	 * 替换所有空格
	 * @param str 要替换空格的字符串
	 * @return 替换后的字符串
	 */
	public static String removeBlank(String str) {
		if (str != null) {
			return str.replaceAll("\\s*", "");  
		}

		return null;
	}
	
	
	/**
	 * 将用某个符号拼接而成的字符串(比如 "AAA,BBB,CCC"),转换成list 
	 * @param str 字符串
	 * @param splitStr 字符串分隔符
	 * @return List<String> 集合
	 */
	public static List<String> changeStringToList(String str,String splitStr) {
		
		List<String> list = new ArrayList<String>();
		
		if (isNotBlank(str)) {
			String[] selectIdsArr = str.split(splitStr);
			for (String strSelect : selectIdsArr) {
				list.add(strSelect);
			}
		}
		return list;
	}
	
	/**
	 * 将用某个符号拼接而成的字符串(比如 "AAA,BBB,CCC"),转换成特定类型的list 
	 * @param str 字符串
	 * @param splitStr 分隔符
	 * @param clazz 类型
	 * @return 集合信息
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> changeStringToList(String str,String splitStr,Class<T> clazz){
		
		List<Object> list = new ArrayList<Object>();
		
		if (isNotBlank(str)) {
			String[] selectIdsArr = str.split(splitStr);
			for (String strSelect : selectIdsArr) {
				
				if(clazz == String.class){ //String 类型转换
					list.add(strSelect);
				}else if(clazz == Long.class){
					list.add(Long.valueOf(strSelect)); // Long 类型转换
				}else if(clazz == Integer.class){
					list.add(Integer.valueOf(strSelect)); // Integer 类型转换
				}else if(clazz == Double.class){
					list.add(Double.valueOf(strSelect)); // Double 类型转换
				}else{
					// 其他类型不做类型转换处理
					throw new ClassCastException();
				}
				
			}
		}
		return (List<T>) list;
	}
	
	
	
	/**
	 * 将list中的String内容以一定形式拼接成String
	 * @param strs 字符串集合信息 List
	 * @param splitStr 分隔符
	 * @return 用分隔符进行分隔的字符串
	 */
	public static String changeListToString(List<String> strs,String splitStr) {
		StringBuffer sbr = new StringBuffer("");
		if (strs != null && strs.size() > 0) {
			for (String st : strs) {
				sbr.append(st + splitStr);
			}
		}
		if (sbr.length() > 0) {
			return sbr.toString().substring(0, sbr.length() - 1);
		}
		return "";
	}
	
	/**
	 * 删除子字符串
	 * @param allStr 原字符串
	 * @param del 要删除的字符串
	 * @param splitStr 分隔符
	 * @return
	 */
	public static String delChildStr(String allStr, String del,String splitStr) {
		if (allStr != null && !allStr.trim().equals("")) {
			if (allStr.contains(splitStr)) {
				/*
				 * 将字符串内容除了要删除的元素 循环遍历加到Stringbuffer中
				 */
				String[] strs = allStr.split(splitStr);
				StringBuffer strb = new StringBuffer("");
				for (String st : strs) {
					if (!st.equals(del)) {
						strb.append(st + splitStr);
					}
				}
				
				/*
				 * 截取掉最后一个分隔符
				 */
				if (strb.length() > 0) {
					allStr = strb.toString().substring(0, strb.length() - 1);
				}
				return allStr;
			} else {
				if (allStr.equals(del)) {
					return "";
				}
			}
		}
		return "";
	}
}

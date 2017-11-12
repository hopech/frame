package com.sicilon.frame.sutils;

import java.util.ArrayList;
import java.util.List;

/**
 * description：数组操作Util
 * ClassName: ArrayUtil <br/> 
 * date: 2017年4月10日 上午9:37:34 <br/> 
 * @author chenwei
 */
public class ArrayUtil {

	
	/**
	 * 判断数组是否为空
	 * @param array 对象数组
	 * @return 真假值
	 */
	public static boolean isBlank(Object[] array){
		return (array == null || array.length == 0);
	}
	
	/**
	 * 判断数组是否不为空
	 * @param array 对象数组
	 * @return 真假值
	 */
	public static boolean isNotBlank(Object[] array){
		if(!isBlank(array)){
			return true;
		}
		return false;
	}
	
	/**
	 * 字符数组转化为整型数组
	 * @param stringArr 字符串数组
	 * @return Integer数组
	 */
	public static Integer[] stringArrToIntegerArr(String[] stringArr) {
		Integer[] intArr = new Integer[]{};
		if(isBlank(stringArr)){
			return intArr;
		}
		
		intArr = new Integer[stringArr.length];
		for (int i = 0; i < stringArr.length; i++) {
			intArr[i] = Integer.parseInt(stringArr[i]);
		}
		return intArr;
	}
	
	/**
	 * int数组转换成String数组
	 * @param integerArr integer数组
	 * @return String数组
	 */
	public static String[] integerArrToStringArr(Integer[] integerArr) {
		String[] strArray = new String[]{};
		if(isBlank(integerArr)){
			return strArray;
		}
		
		strArray = new String[integerArr.length];
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = String.valueOf(integerArr[i]);
		}
		return strArray;
	}
	
	/**
	 * 字符串数组转换list
	 * @param strArray 字符串数组
	 * @return 字符串List
	 */
	public static List<String> strArrayToList(String[] strArray){
		
		List<String> strList = new ArrayList<String>();
		if(strArray == null || strArray.length == 0){
			return strList;
		}
		
		for (String str : strArray) {
			strList.add(str);
		}
		return strList;
	}
	
	/**
	 * Integer数组转换为List
	 * @param integerArray integer数组
	 * @return integer List集合
	 */
	public static List<Integer> integerArrayToList(Integer[] integerArray){
		List<Integer> integerList = new ArrayList<Integer>();
		if(integerArray == null || integerArray.length == 0){
			return integerList;
		}
		
		for (Integer i : integerArray) {
			integerList.add(i);
		}
		return integerList;
	}
}

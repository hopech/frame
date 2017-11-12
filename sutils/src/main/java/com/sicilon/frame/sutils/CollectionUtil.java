package com.sicilon.frame.sutils;

import java.util.Collection;

/**
 * Description: 集合工具类
 * Author: chenweijia <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年5月19日 上午10:39:54.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class CollectionUtil {

	/**
	 * 判断集合是否不为空或者不为null
	 * @param collection 集合
	 * @return 真假值
	 */
	public static boolean isNotBlank(Collection<?> collection){ 
		return !isBlank(collection);
	}
	
	/**
	 * 判断集合是否为空或者null
	 * @param collection 集合
	 * @return 真假值
	 */
	public static boolean isBlank(Collection<?> collection){
		if(collection == null || collection.isEmpty()){
			return true;
		}
		return false;
	}
}

package com.sicilon.frame.sweb.util;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


/**
 * description：JSON工具类
 * ClassName: JsonUtil <br/> 
 * date: 2017年3月31日 下午5:31:25 <br/> 
 * @author chen
 */
public class JsonUtil {

	/**
	 * 将对象转换成json串
	 * @param obj 任意类型对象
	 * @return json形式字符串
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj, false);
	}

	/**
	 * 将json串转换为对象
	 * @param json json形式字符串
	 * @param clazz 要转换的类型
	 * @return 传入类型的对象
	 */
	public static <T> T jsonToObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * 将json串转换成map
	 * @param json json形式字符串
	 * @return json对应的map值
	 */
	public static Map<String, Object> jsonToMap(String json){
		return JSON.parseObject(json,new TypeReference<Map<String, Object>>(){});
	}
	
	/**
	 * 将json串转换成list
	 * @param json json形式字符串
	 * @return json对应的List集合值
	 */
	public static <T> List<T> jsonToList(String json,Class<T> clazz){
		return JSON.parseArray(json, clazz);
	}
}

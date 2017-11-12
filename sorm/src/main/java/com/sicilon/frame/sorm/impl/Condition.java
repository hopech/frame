package com.sicilon.frame.sorm.impl;

import java.lang.reflect.Field;

import com.sicilon.frame.sorm.annotntion.PK;
import com.sicilon.frame.sorm.utils.StringUtil;


/**
 * sql 条件类
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:51:20
 * @version 1.0 
 */
public class Condition {
	private static final String WHERE = " WHERE ";							//where 条件语句
	private static final String BeEqualTo = " = ";							//=   
	
	
	/**
	 * 返回where 条件  
	 * @param id 表主键为Integer类型
	 * @return
	 */
	public static String selectWhereById(Class<?> cs, Long id){
		String sql = WHERE+loadEntityByPk(cs)+BeEqualTo+id;
		return sql;
	}
	
	/**
	 * 返回where 条件  
	 * @param id 表主键为Integer类型
	 * @return
	 */
	public static String selectWhereById(Class<?> cs, String id){
		String sql = WHERE+loadEntityByPk(cs)+BeEqualTo+id;
		return sql;
	}
	
	/**
	 * 加载entity类 获取主键
	 * @param cs
	 * @return 默认返回id
	 */
	private static String loadEntityByPk(Class<?> cs){
		Field[] declaredFields = cs.getDeclaredFields();
		for (Field field : declaredFields) {
			if(field.isAnnotationPresent(PK.class)){
				String value = field.getAnnotation(PK.class).value();
				//注释存在不存在
                if(StringUtil.isBlank(value)){//没有设置数据库的字段名，则取pojo的字段名
                    return field.getName();
                }else{
                	return value;
                }
			}
		}
		return "nid";
	}
}

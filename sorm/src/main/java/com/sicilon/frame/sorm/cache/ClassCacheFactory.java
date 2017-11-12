package com.sicilon.frame.sorm.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.sicilon.frame.sorm.annotntion.Column;
import com.sicilon.frame.sorm.annotntion.NoInsert;
import com.sicilon.frame.sorm.annotntion.NoUpdate;
import com.sicilon.frame.sorm.annotntion.PK;
import com.sicilon.frame.sorm.impl.FieldInfo;
import com.sicilon.frame.sorm.utils.StringUtil;

/**
 * 
 * description：Class缓存工厂
 * ClassName: ClassCacheFactory <br/> 
 * date: 2017年5月4日 下午3:33:33 <br/> 
 * @author chen
 */
public class ClassCacheFactory {
	
	/**
	 * Class缓存map
	 */
	public static Map<Class<?>, ClassCache> classChache = new HashMap<Class<?>, ClassCache>();
	
	/**
	 * 私有构造方法
	 */
	private ClassCacheFactory(){
	}
	
	/**
	 * 获取classCache实体
	 * @param clazz Class
	 * @return classCache实体
	 */
	public static ClassCache getClassCache(Class<?> clazz){
		/*
		 * 如果缓存中有classChache返回,没有则构建
		 */
		ClassCache classCache = classChache.get(clazz);
		if(null != classCache ){
			return classCache;
		}
		return buildClaasChache(clazz);
	}

	/**
	 * 构建class缓存实体
	 * @param clazz Class
	 * @return class缓存实体
	 */
	private static ClassCache buildClaasChache(Class<?> clazz) {
		ClassCache cache = new ClassCache();
		
		Map<String, Integer> methodIndex = new HashMap<String, Integer>(); //方法索引
		Map<String, String> getMethod = new HashMap<String, String>(); //get方法
		Map<String, String> setMethod = new HashMap<String, String>(); //set方法
		
		
		StringBuffer fieldNames = new StringBuffer();// 属性名
		StringBuffer fieldNameAgens = new StringBuffer();// 属性名占位符形式
		List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();//属性信息集合
		
		/*
		 * 1.设置methodAccess
		 */
		MethodAccess methodAccess = MethodAccess.get(clazz);
		cache.setMethodAccess(methodAccess);

		// 遍历属性数组
		List<Field> fields = getDeclaredField(clazz);
		for (Field field : fields) {
			
			//忽略序列化字段
			if(field.getName().equals("serialVersionUID")){
                continue;
            }
			
			String fieldName = field.getName();
			
			fieldNames.append(fieldName).append(","); //属性名拼接
			
			fieldNameAgens.append(":").append(fieldName).append(",");//属性名占位符拼接
			 
			getMethod.put(fieldName, cache.getFieldMethodName(fieldName));// 设置字段名 对应的get方法
			
			setMethod.put(fieldName, cache.setFieldMethodName(fieldName));// 设置字段名 对应的set方法
			//方法的索引
			methodIndex.put(cache.getFieldMethodName(fieldName), methodAccess.getIndex(cache.getFieldMethodName(fieldName)));
			methodIndex.put(cache.setFieldMethodName(fieldName), methodAccess.getIndex(cache.setFieldMethodName(fieldName)));
			
			
			//为fieldInfo加入元素
			setFieldInfo(fieldInfos, field);
		}

		/*
		 * 2.设置属性名
		 */
		cache.setFildNames(fieldNames.deleteCharAt(fieldNames.length() - 1).toString());//删除最后一个逗号
		/*
		 * 3.设置属性名占位符
		 */
		cache.setFieldValuesAgen(fieldNameAgens.deleteCharAt(fieldNameAgens.length() - 1).toString());//删除最后一个逗号
		/*
		 * 4.设置bean属性对应的设置数据库列名称
		 */
		cache.setFieldColumn(setFieldColumn(fields));
		/*
		 * 5.设置方法索引
		 */
		cache.setMethodIndex(methodIndex);
		/*
		 * 6.设置getSet方法
		 */
		cache.setGetMethod(getMethod);
		cache.setSetMethod(setMethod);
		
		/*
		 * 7.设置fieldInfos
		 */
		cache.setFieldInfos(fieldInfos);
		
		/*
		 * 8.设置数据库字段对应类型属性映射
		 */

		classChache.put(clazz, cache);
		return cache;
	}
	
	/**
	 * 设置数据库列名称
	 * @param fields
	 * @return
	 */
	private static String setFieldColumn(List<Field> fields){
		StringBuffer columns = new StringBuffer();
		for (Field field : fields) {
			
			//忽略序列化字段
			if(field.getName().equals("serialVersionUID")){
                continue;
            }
			
			/*
			 * 判断有无注解,有主键且有value值 设置column值为value值
			 * 如果没有注解,设置column值为字段名称
			 */
			if(field.isAnnotationPresent(Column.class)
					&& StringUtil.isNotBlank(field.getAnnotation(Column.class).value())){
				columns.append(field.getAnnotation(Column.class).value()).append(",");
			}else if(field.isAnnotationPresent(Column.class)
					&& StringUtil.isBlank(field.getAnnotation(Column.class).value())){
				columns.append(field.getName()).append(",");
			}else if(field.isAnnotationPresent(PK.class)
					&& StringUtil.isNotBlank(field.getAnnotation(PK.class).value())){
				columns.append(field.getAnnotation(PK.class).value()).append(",");
			}else if(field.isAnnotationPresent(PK.class)
					&& StringUtil.isBlank(field.getAnnotation(PK.class).value())){
				columns.append(field.getName()).append(",");
			}else{
				columns.append(field.getName()).append(",");
			}
			
		}
		return columns.deleteCharAt(columns.length()-1).toString();
	}
	
	/**
	 * 设置字段属性信息
	 * @param fieldInfo
	 * @param field
	 */
	private static void setFieldInfo(List<FieldInfo> fieldInfo,Field field){
		
		FieldInfo info = new FieldInfo();
		//获取属性名称
		String fieldName = field.getName();
		
		//设置属性名称
		info.setPojoFieldName(fieldName);
		
		//设置数据库字段名称
		if(field.isAnnotationPresent(Column.class) 
				&& StringUtil.isNotBlank(field.getAnnotation(Column.class).value())){ //有column主键有值
			info.setDbFieldName(field.getAnnotation(Column.class).value());
		}else if(field.isAnnotationPresent(Column.class) 
				&& StringUtil.isBlank(field.getAnnotation(Column.class).value())){ //有column主键无值
			info.setDbFieldName(fieldName);
		}
		
		//设置字段类型
		info.setFieldType(field.getType());
		
		//设置主键信息
		if(field.isAnnotationPresent(PK.class) && StringUtil.isNotBlank(field.getAnnotation(PK.class).value())){
			info.setPk(true);
			info.setDbFieldName(field.getAnnotation(PK.class).value());
		}else if(field.isAnnotationPresent(PK.class) && StringUtil.isBlank(field.getAnnotation(PK.class).value())){
			info.setPk(true);
			info.setDbFieldName(fieldName);
		}
		
		//设置是否是更新字段
		if(field.isAnnotationPresent(NoUpdate.class)){
			info.setUpdate(false);
		}
		//设置是否是插入字段
		if(field.isAnnotationPresent(NoInsert.class)){
			info.setInsert(false);
		}
		
		//加入元素
		fieldInfo.add(info);
	}
	
	public static List<Field> getDeclaredField(Class<?> clazz) {
	    
	    List<Field>filesList = new ArrayList<Field>();
	    
	    for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
	      try {
	        Field[] fields = clazz.getDeclaredFields();
	        for (Field field : fields) {
	          filesList.add(field);
	        }
	      } catch (Exception e) {
	        // 这里甚么都不能抛出去。
	        // 如果这里的异常打印或者往外抛，则就不会进入
	      }
	    }
	    return filesList;
	  }   
}

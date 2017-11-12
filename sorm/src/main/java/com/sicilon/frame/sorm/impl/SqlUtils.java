package com.sicilon.frame.sorm.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.sicilon.frame.sorm.SqlErrorCode;
import com.sicilon.frame.sorm.annotntion.Table;
import com.sicilon.frame.sorm.cache.ClassCache;
import com.sicilon.frame.sorm.cache.ClassCacheFactory;
import com.sicilon.frame.sorm.utils.DateTimeUtil;
import com.sicilon.frame.sorm.utils.StringUtil;

/**
 * 映射sql类
 * 
 * @author SmallFour
 * @date 创建时间：2017年5月5日 下午3:44:22
 * @version 1.0
 */
public class SqlUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(SqlUtils.class);
	
	//private static Map<String, Object> cacheMap = new HashMap<String, Object>();
	private static Map<String, Object> insertSqlCache = new HashMap<String, Object>();
	private static Map<String, Object> updateSqlCache = new HashMap<String, Object>();
	private static Map<String, Object> deleteSqlCache = new HashMap<String, Object>();
	private static Map<String, Object> selectSqlCache = new HashMap<String, Object>();

	/**
	 * 批量添加 根据list生成sql
	 * 
	 * @param list
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static String buildInsertBathSql(List<?> list)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> class1 = list.get(0).getClass(); // 获取对象
		List<FieldInfo> loadPojoSqlInfo = loadPojoSqlInfo(class1); // 加载entity注解信息
		return buildInsertBathSql(class1, loadPojoSqlInfo, list);
	}

	/**
	 * 根据pojo类的class来构建select * from 的SQL语句
	 * 
	 * @param pojoClass
	 * @return
	 */
	public static String buildSelectSql(Class<?> pojoClass) {
		List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass); // 加载model类获取注解上的值，没有就取字段
		String sql = buildSelectSql(pojoClass, fieldInfoList); // 拼接sql
		return sql;
	}

	/**
	 * 根据pojo类的class来构建insert的SQL语句
	 * 
	 * @param pojoClass
	 * @return
	 */
	public static String buildInsertSql(Class<?> pojoClass) {
		List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
		String sql = buildInsertSql(pojoClass, fieldInfoList);
		return sql;
	}

	/**
	 * 根据pojo类的class构建根据pk来update的SQL语句
	 * 
	 * @param pojoObject
	 * @return
	 */
	public static String buildUpdateSql(Class<?> pojoClass) {
		List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
		String sql = buildUpdateSqlByPK(pojoClass, fieldInfoList);
		return sql;
	}

	/**
	 * 根据pojo类的Class生成根据pk来delete的SQL语句
	 * 
	 * @param pojoClass
	 * @return
	 */
	public static String buildDeleteSql(Class<?> pojoClass) {
		// 获取字段注解 没有就字段本身
		List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
		String sql = buildDeleteSqlByPK(pojoClass, fieldInfoList);
		return sql;
	}

	/**
	 * 将SQL查询出来的map对象转成实体对象
	 * 
	 * @param map
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	public static Object coverMapToBean(Map<String, Object> map, Class<?> pojoClass) throws Exception {
		if(map ==  null){
			return null;
		}
		Object result = pojoClass.newInstance();
		ClassCache classCache = ClassCacheFactory.getClassCache(pojoClass); // 反射使用
		MethodAccess methodAccess = classCache.getMethodAccess();
		List<FieldInfo> loadPojoSqlInfo = loadPojoSqlInfo(pojoClass); // 获取缓存
		for (FieldInfo field : loadPojoSqlInfo) { // 循环取出字段数组
			methodAccess.invoke(result,
					classCache.getMethodIndex().get(classCache.getSetMethod().get(field.getPojoFieldName())),
					map.get(field.getDbFieldName())); // 映射set方法赋值
		}
		return result;
	}

	/**
	 * 将map转换为普通model类
	 * 
	 * @param map
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	public static <T>T coverMapToModel(Map<String, Object> map, Class<T> modelClass) throws Exception {
		T result = modelClass.newInstance(); // 实例化对象
		ClassCache classCache = ClassCacheFactory.getClassCache(modelClass); // 获取缓存
		MethodAccess methodAccess = classCache.getMethodAccess();
		ergodicMap(classCache, methodAccess, map, result);
		return result;
	}

	/**
	 * 将SQL查询出来的List<map>对象转成实体对象
	 * 
	 * @param map
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	public static <T>List<T> coverListToBean(List<Map<String, Object>> list, Class<T> pojoClass) throws Exception {
		ClassCache classCache = ClassCacheFactory.getClassCache(pojoClass); // 获取缓存
		MethodAccess methodAccess = classCache.getMethodAccess();
		List<T> resultList = new ArrayList<T>();
		for (Map<String, Object> map : list) { // 遍历list
			T result = pojoClass.newInstance(); // 实例化对象
			ergodicMap(classCache, methodAccess, map, result);
			resultList.add(result); // list数组添加
		}
		return resultList;
	}

	/**
	 * 遍历map key
	 * @param classCache
	 * @param methodAccess
	 * @param map
	 * @param result
	 */
	private static <T> void ergodicMap(ClassCache classCache, MethodAccess methodAccess, Map<String, Object> map, T result) {
		for (String key : map.keySet()) {			//循环map中key值
			if(classCache.getSetMethod().get(key)!=null){
				methodAccess.invoke(result, classCache.getMethodIndex().get(classCache.getSetMethod().get(key)),
						map.get(key)); // set赋值
			}else{
				throw new RuntimeException(SqlErrorCode.RESULT_SET+key+"映射失败");
			}
		}
	}
	
	/**
	 * 加载读取pojo的注解信息
	 * 
	 * @param pojoClass
	 * @return
	 */
	private static List<FieldInfo> loadPojoSqlInfo(Class<?> pojoClass) {
		ClassCache classCache = ClassCacheFactory.getClassCache(pojoClass); // 获取缓存
		List<FieldInfo> fieldInfos = classCache.getFieldInfos(); // 获取字段详解集合
		if(!fieldInfos.isEmpty()&&fieldInfos!=null){
			return fieldInfos;
		}else{
			throw new RuntimeException(SqlErrorCode.NOGET_CACHE);
		}
	}

	/**
	 * 拼接select语句
	 * 
	 * @param pojoClass
	 * @param fieldInfoList
	 * @return
	 */
	private static String buildSelectSql(Class<?> pojoClass, List<FieldInfo> fieldInfoList) {
		if (StringUtil.isNotBlank(selectSqlCache.get(pojoClass.getName()))) { // 缓存中查询已有sql
			return (String) selectSqlCache.get(pojoClass.getName());
		}
		String sql = "SELECT ";
		for (FieldInfo fieldInfo : fieldInfoList) {
			sql += fieldInfo.getDbFieldName() + ", ";
		} // 拼接将要查询的字段
		String substring = sql.substring(0, sql.length() - 2); // 拼接完成，去掉最后的空格
																// 和逗号
		return substring + " FROM " + loadTableName(pojoClass);
	}

	/**
	 * 拼接insertBath的SQL
	 * 
	 * @param pojoClass
	 * @param fieldInfoList
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private static String buildInsertBathSql(Class<?> pojoClass, List<FieldInfo> fieldInfoList, List<?> list){
		String tableName = loadTableName(pojoClass); // 获取表名
		StringBuffer mappingFieldValue = new StringBuffer(); // 将要添加的字段值 values
		StringBuffer mappingFieldDB = new StringBuffer(); // 将要添加的字段 db
		
		/*
		 * 拼接属性字段
		 */
		for (FieldInfo field : fieldInfoList) {
			if(!field.isPk()){
				mappingFieldDB.append(field.getDbFieldName()+", ");
			}
		}
		ClassCache classCache = ClassCacheFactory.getClassCache(pojoClass); // 获取缓存
		MethodAccess methodAccess = classCache.getMethodAccess();
		
		/*
		 * 拼接values
		 */
		for (Object object : list) { // 循环list
			mappingFieldValue.append("(");
			for (FieldInfo fieldName : fieldInfoList) { // 循环表字段
				Object value = methodAccess.invoke(object, // 获取get方法 值
						classCache.getMethodIndex().get(classCache.getGetMethod().get(fieldName.getPojoFieldName())));
				if (StringUtil.isNotBlank(value)) {
					if(value.getClass() == Date.class){
						mappingFieldValue.append("'").append(new DateTimeUtil((Date)value).toDateTimeString()).append("'").append(",");// 拼接映射字段值
					}else{
						mappingFieldValue.append("'").append(value).append("'").append(",");// 拼接映射字段值
					}
				}
			}
			mappingFieldValue.deleteCharAt(mappingFieldValue.length() - 1);
			mappingFieldValue.append("),");
		}
		mappingFieldValue.deleteCharAt(mappingFieldValue.length() - 1);
		StringBuffer resultSql = new StringBuffer();
		// 这里做主键自增判断
		resultSql.append("insert into ").append(tableName).append("(") // sql拼接
				.append(mappingFieldDB.deleteCharAt(mappingFieldDB.length() - 2)).append(") values ") // 拼接映射字段
				.append(mappingFieldValue); // 拼接映射值
		logger.debug(resultSql.toString());
		return resultSql.toString();
	}

	/**
	 * 拼接insert的SQL
	 * 
	 * @param pojoClass
	 * @param fieldInfoList
	 * @return
	 */
	private static String buildInsertSql(Class<?> pojoClass, List<FieldInfo> fieldInfoList) {
		String result = null;
		if (StringUtil.isNotBlank(insertSqlCache.get(pojoClass.getName()))) { // 查询缓存
			result = (String) insertSqlCache.get(pojoClass.getName());
			return result;
		}
		ClassCache classCache = ClassCacheFactory.getClassCache(pojoClass); // 获取缓存
		String fildNames = classCache.getFieldColumn(); // 获取字段名
		String fieldValuesAgen = classCache.getFieldValuesAgen(); // 获取values
		String tableName = loadTableName(pojoClass); // 获取表名
		StringBuffer resultSql = new StringBuffer();
		resultSql.append("insert into ").append(tableName).append("(") // 拼接
																		// insert
																		// sql
				.append(fildNames).append(") values (").append(fieldValuesAgen).append(")");
		result = resultSql.toString();
		insertSqlCache.put(pojoClass.getName(), result); // 添加缓存
		logger.debug(result);
		return result;
	}

	/**
	 * 生成根据主键生成删除的SQL
	 * 
	 * @param pojoClass
	 * @param fieldInfoList
	 * @return
	 */
	private static String buildDeleteSqlByPK(Class<?> pojoClass, List<FieldInfo> fieldInfoList) {
		String result = null;
		if (StringUtil.isNotBlank(deleteSqlCache.get(pojoClass.getName() + "_pk"))) {
			result = (String) deleteSqlCache.get(pojoClass.getName() + "_pk"); // 从删除缓存中获取sql
			return result;
		}

		StringBuffer resultSql = new StringBuffer();
		resultSql.append(appendBaseDeleteSQL(pojoClass)); // 获取删除where之前部分
		mappingPKDBField(fieldInfoList, resultSql);
		result = resultSql.toString();
		deleteSqlCache.put(pojoClass.getName() + "_pk", result); // 添加缓存
		logger.debug(result);
		return result;
	}

	/**
	 * 拼接根据主键来update的SQL
	 * 
	 * @param pojoClass
	 * @param fieldInfoList
	 * @return
	 */
	private static String buildUpdateSqlByPK(Class<?> pojoClass, List<FieldInfo> fieldInfoList) {
		String result = null;
		if (StringUtil.isNotBlank(updateSqlCache.get(pojoClass.getName() + "_pk"))) {
			result = (String) updateSqlCache.get(pojoClass.getName() + "_pk"); // 从修改缓存中获取sql
			return result;
		}
		StringBuffer resultSql = new StringBuffer();
		resultSql.append(appendBaseUpdateSQL(pojoClass, fieldInfoList));
		mappingPKDBField(fieldInfoList, resultSql);					//映射值字段
		result = resultSql.toString();
		updateSqlCache.put(pojoClass.getName() + "_pk", result); // 添加缓存
		logger.debug(result);
		return result;
	}

	/**
	 * Description: 获取pk主键
	 * @param 
	 * @return 
	 * @throws Exception  
	 * Note: Nothing much.
	 */
	private static void mappingPKDBField(List<FieldInfo> fieldInfoList, StringBuffer resultSql) {
		for (FieldInfo fieldInfo : fieldInfoList) {
			if (fieldInfo.isPk()) {
				resultSql.append(fieldInfo.getDbFieldName()); // 拼接主键
				resultSql.append("=:").append(fieldInfo.getPojoFieldName());
			}
		}
	}
	/**
	 * Description: 获取映射字段
	 * @param 
	 */
	private static void mappingValueDBField(List<FieldInfo> fieldInfoList, StringBuffer resultSql) {
		for (FieldInfo fieldInfo : fieldInfoList) {
			if (fieldInfo.isUpdate() && !fieldInfo.isPk()) {
				resultSql.append(fieldInfo.getDbFieldName()); // 拼接主键
				resultSql.append("=:").append(fieldInfo.getPojoFieldName()).append(", ");
			}
		}
	}

	/**
	 * 拼接update语句的where之前的sql
	 * @param pojoClass
	 * @param fieldInfoList
	 * @param resultSql
	 */
	private static String appendBaseUpdateSQL(Class<?> pojoClass, List<FieldInfo> fieldInfoList) {
		String result = null;
		if (StringUtil.isNotBlank(updateSqlCache.get(pojoClass.getName() + "_columns"))) {
			result = (String) updateSqlCache.get(pojoClass.getName() + "_columns");// 从修改缓存中获取sql
		} else {
			StringBuffer resultSql = new StringBuffer();
			String tableName = loadTableName(pojoClass); // 获取表名
			resultSql.append("update ").append(tableName).append(" set "); // 开头部分
			mappingValueDBField(fieldInfoList, resultSql);
			resultSql.delete(resultSql.length() - 2, resultSql.length());
			resultSql.append(" where ");

			result = resultSql.toString();
			updateSqlCache.put(pojoClass.getName() + "_columns", result); // 添加缓存
		}
		logger.debug(result);
		return result;
	}

	/**
	 * 拼接delete语句的where之前的sql
	 * 
	 * @param pojoClass
	 * @param fieldInfoList
	 * @param resultSql
	 */
	private static String appendBaseDeleteSQL(Class<?> pojoClass) {
		String result = "";
		if (StringUtil.isNotBlank(deleteSqlCache.get(pojoClass.getName() + "_columns"))) {
			result = (String) deleteSqlCache.get(pojoClass.getName() + "_columns");
			logger.debug(result);
			return result; // 删除缓存中获取sql
		} else {
			result = "DELETE FROM " + loadTableName(pojoClass) + " WHERE ";
			deleteSqlCache.put(pojoClass.getName() + "_columns", result); // 没有就先拼接
																			// 在缓存
			logger.debug(result);
			return result;
		}
	}

	/**
	 * 通过类获取表名
	 * 
	 * @param pojoClass
	 * @return
	 */
	private static String loadTableName(Class<?> pojoClass) {
		if (pojoClass.isAnnotationPresent(Table.class)) {
			Table table = (Table) pojoClass.getAnnotation(Table.class); // 反射获取表名注解
			logger.debug("表名>>>>>>>>"+table.value());
			return table.value();
		} else {
			String string = lowerStrToUnderline(pojoClass.getSimpleName());
			logger.debug("表名>>>>>>>>"+string);
			return string; // 没有注解就获取类名
		}
	}

	/**
	 * 将大写字母转换成下划线加小写字母 例:userName--> user_name
	 * 
	 * @param str
	 * @return
	 */
	private static String lowerStrToUnderline(String str) {
		if (StringUtil.isBlank(str)) {
			return "";
		}
		StringBuilder sb = new StringBuilder(str);
		char c;
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				sb.replace(i + count, i + count + 1, (c + "").toLowerCase());
				// sb.insert(i+count, "_");
				count++;
			}
		}
		return sb.toString();
	}

	private static String buildDeleteLoginSqlByPK(Class<?> cs, List<FieldInfo> fieldInfoList, int isDel)
			throws InstantiationException, IllegalAccessException {
		String resultSql = "";
		if (StringUtil.isNotBlank(deleteSqlCache.get(cs.getName() + "_deleteLogin"))) {
			return (String) deleteSqlCache.get(cs.getName() + "_deleteLogin"); // 删除缓存中获取sql
		} else {
			for (FieldInfo fieldInfo : fieldInfoList) {
				if (fieldInfo.isPk()) {
					resultSql = "UPDATE " + loadTableName(cs) + " SET isDel=" + isDel + " WHERE "
							+ fieldInfo.getDbFieldName() + "=:" + fieldInfo.getDbFieldName(); // 根据主键进行拼接sql
					deleteSqlCache.put(cs.getName() + "_deleteLogin", resultSql); // 没有就先拼接
																					// 在缓存
				}
			}
			logger.debug(resultSql);
			return resultSql;
		}
	}

	public static String buildDeleteLoginSql(Class<?> cs, int isDel)
			throws InstantiationException, IllegalAccessException {
		loadPojoSqlInfo(cs);
		System.out.println(buildDeleteLoginSqlByPK(cs, loadPojoSqlInfo(cs), isDel));
		return buildDeleteLoginSqlByPK(cs, loadPojoSqlInfo(cs), isDel);
	}

	/**
	 * 计算分页 limit
	 * @param page 将要访问的页数
	 * @param pageSize 每页大小
	 * @return
	 */
	public static String getLimitSql(Integer page, Integer pageSize) {
		return " LIMIT "+(page-1)*pageSize+","+pageSize;
	}
}

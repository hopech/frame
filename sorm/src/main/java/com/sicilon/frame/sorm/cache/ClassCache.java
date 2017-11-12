package com.sicilon.frame.sorm.cache;

import java.util.List;
import java.util.Map;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.sicilon.frame.sorm.impl.FieldInfo;

/**
 * 
 * description：Class缓存
 * ClassName: ClassCache <br/> 
 * date: 2017年5月4日 下午1:38:58 <br/> 
 * @author chen
 */
public class ClassCache {

	private MethodAccess methodAccess; //反射方法工具
	
	private String fildNames; //类属性名称拼接, 形式为:field1,field2,field3  insert into table (id,name) values(:id,:name)
	
	private String fieldValuesAgen; //insert语句中value的属性代理 形式为: :field1,:field2,:field3
	
	private String fieldColumn; //类属性与数据库名称对应的列名
	
	private Map<String, String> getMethod;// key:字段名称 value:get方法
	
	private Map<String, String> setMethod;// key:字段名称 value:set方法
	
	private Map<String, Integer> methodIndex; // key:方法名称 value:索引
	
	private Map<String, String> columnField; // key:数据库字段 value:类属性
	
	private List<FieldInfo> fieldInfos; //属性信息
	
	protected ClassCache(){
	}
	
	/**
	 * 获取get方法名称
	 * @param fieldName
	 * @return
	 */
	public String getFieldMethodName(String fieldName){
		StringBuffer result = new StringBuffer();
		result.append("get").append(fieldName.substring(0,1).toUpperCase()).append(fieldName.substring(1));
		return result.toString();
	}
	
	/**
	 * 获取set方法名称
	 * @param fieldName
	 * @return
	 */
	public String setFieldMethodName(String fieldName){
		StringBuffer result = new StringBuffer();
		result.append("set").append(fieldName.substring(0,1).toUpperCase()).append(fieldName.substring(1));
		return result.toString();
	}
	
	
	public MethodAccess getMethodAccess() {
		return methodAccess;
	}
	public void setMethodAccess(MethodAccess methodAccess) {
		this.methodAccess = methodAccess;
	}
	public String getFildNames() {
		return fildNames;
	}
	public void setFildNames(String fildNames) {
		this.fildNames = fildNames;
	}
	
	public String getFieldValuesAgen() {
		return fieldValuesAgen;
	}
	public void setFieldValuesAgen(String fieldValuesAgen) {
		this.fieldValuesAgen = fieldValuesAgen;
	}

	public Map<String, String> getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(Map<String, String> getMethod) {
		this.getMethod = getMethod;
	}

	public Map<String, String> getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(Map<String, String> setMethod) {
		this.setMethod = setMethod;
	}

	public Map<String, Integer> getMethodIndex() {
		return methodIndex;
	}

	public void setMethodIndex(Map<String, Integer> methodIndex) {
		this.methodIndex = methodIndex;
	}

	public String getFieldColumn() {
		return fieldColumn;
	}

	public void setFieldColumn(String fieldColumn) {
		this.fieldColumn = fieldColumn;
	}

	public List<FieldInfo> getFieldInfos() {
		return fieldInfos;
	}

	public void setFieldInfos(List<FieldInfo> fieldInfos) {
		this.fieldInfos = fieldInfos;
	}

	public Map<String, String> getColumnField() {
		return columnField;
	}

	public void setColumnField(Map<String, String> columnField) {
		this.columnField = columnField;
	}
	
	
}

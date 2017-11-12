package com.sicilon.frame.sorm.impl;
/**
 * 
* @ClassName: FieldInfo 
* @Description: 字段信息包装类
* @author chen
* @date 2017年5月5日 下午3:01:40 
*
 */
public class FieldInfo {
	
    private String pojoFieldName; //java字段名
  
    private String dbFieldName;  //数据库字段名
    //isDelete时是否需要插入
    private boolean isDelete = false;
    
    private boolean isPk = false;//是否是主键
   
    private boolean isUpdate = true;//update时是否需要更新
   
    private boolean isInsert = true; //insert时是否需要插入
    
    private Class<?> fieldType;//字段类型
	public String getPojoFieldName() {
		return pojoFieldName;
	}
	public void setPojoFieldName(String pojoFieldName) {
		this.pojoFieldName = pojoFieldName;
	}
	public String getDbFieldName() {
		return dbFieldName;
	}
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
	public boolean isPk() {
		return isPk;
	}
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
	public boolean isUpdate() {
		return isUpdate;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public boolean isInsert() {
		return isInsert;
	}
	public void setInsert(boolean isInsert) {
		this.isInsert = isInsert;
	}
	public Class<?> getFieldType() {
		return fieldType;
	}
	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}

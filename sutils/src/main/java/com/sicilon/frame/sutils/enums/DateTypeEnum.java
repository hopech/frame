package com.sicilon.frame.sutils.enums;


/**
 * Description: 日期类型枚举
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月1日 下午3:05:03.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public enum DateTypeEnum {

	LOCAL(0,"本地时间"),
	UTC(1,"UTC时间"); 
	
	private Integer value;
	private String description;
	
	
	private DateTypeEnum(Integer value, String description) {
		this.value = value;
		this.description = description;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

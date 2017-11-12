package com.sicilon.frame.sweb.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 字符串输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月4日 下午8:38:00.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class StringInput extends BaseInput{

	@ApiModelProperty("字符型输入参数")
	private String strValue = "";
	
	public StringInput() {
	}
	
	public StringInput(String strValue) {
		this.strValue = strValue;
	}
	
	public String getStrValue() {
		return strValue;
	}
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	
	
}

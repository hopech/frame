package com.sicilon.frame.sweb.bean;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 字符串集合输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月4日 下午8:40:30.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class StringListInput extends BaseInput{

	@ApiModelProperty("字符集合输入参数")
	private List<String> strListValue;
	
	public StringListInput() {
	}

	public StringListInput(List<String> strListValue) {
		this.strListValue = strListValue;
	}

	public List<String> getStrListValue() {
		return strListValue;
	}

	public void setStrListValue(List<String> strListValue) {
		this.strListValue = strListValue;
	}
}

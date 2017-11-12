package com.sicilon.frame.sweb.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 整形输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月4日 下午8:37:35.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class IntInput extends BaseInput{

	@ApiModelProperty("整型输入参数")
	private Integer intValue;
	
	public IntInput() {
	}

	public IntInput(Integer intValue) {
		this.intValue = intValue;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	
	
}

package com.sicilon.frame.sweb.bean;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 整形集合输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月4日 下午8:39:12.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class IntListInput extends BaseInput{

	@ApiModelProperty("整型集合输入参数")
	private List<Integer> intListValue;

	
	public IntListInput() {
	}

	public IntListInput(List<Integer> intListValue) {
		this.intListValue = intListValue;
	}

	public List<Integer> getIntListValue() {
		return intListValue;
	}

	public void setIntListValue(List<Integer> intListValue) {
		this.intListValue = intListValue;
	}
	
}

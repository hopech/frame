package com.sicilon.frame.sweb.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 长整形输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月4日 下午8:42:05.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class LongInput extends BaseInput{

	@ApiModelProperty("长整型输入参数")
	private Long longValue;
	
	
	/**
	 * 默认构造器
	 */
	public LongInput() {
	}

	/**
	 * @param longValue
	 */
	public LongInput(Long longValue) {
		this.longValue = longValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}
	
}

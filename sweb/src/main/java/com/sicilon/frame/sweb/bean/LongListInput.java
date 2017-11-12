package com.sicilon.frame.sweb.bean;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 长整形集合输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月4日 下午8:42:12.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class LongListInput extends BaseInput{

	@ApiModelProperty("长整型输入参数")
	private List<Long> longListValue;
	
	public LongListInput() {
	}
	
	public LongListInput(List<Long> longListValue) {
		this.longListValue = longListValue;
	}

	public List<Long> getLongListValue() {
		return longListValue;
	}

	public void setLongListValue(List<Long> longListValue) {
		this.longListValue = longListValue;
	}

	
}

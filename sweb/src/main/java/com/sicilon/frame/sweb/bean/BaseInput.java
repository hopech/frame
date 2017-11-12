package com.sicilon.frame.sweb.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 公共输入实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月7日 下午10:59:04.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class BaseInput {

	@ApiModelProperty(value="公共参数",required = true)
	private BaseParam baseParam = new BaseParam(); //公共参数

	public BaseParam getBaseParam() {
		return baseParam;
	}
	public void setBaseParam(BaseParam baseParam) {
		this.baseParam = baseParam;
	}

	

	
	
	
}

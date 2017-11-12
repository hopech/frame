package com.sicilon.frame.sweb.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 输出类<br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年8月30日 下午5:09:33 <br/>
  <br/>UpdateTime：
  <br/>UpdateUser：
  <br/>UpdateNote：
  <br/>------------------------------
 */

public class BaseOutput {
	
	@ApiModelProperty(value = "响应编码")
	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}

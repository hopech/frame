package com.sicilon.frame.sweb.bean;

import com.sicilon.frame.sweb.config.ResponseCode;
import com.sicilon.frame.sweb.util.JsonUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 错误输出类
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月27日 上午8:53:14.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class ErrorOutput extends BaseOutput{

	@ApiModelProperty(value = "编码信息")
	private String codeValue; // 编码信息
	
	@ApiModelProperty(value = "提示信息")
	private String msg; // 提示信息
	
	
	public ErrorOutput() {
	}
	
	

	public ErrorOutput(Integer code, String msg) {
		super.setCode(code);
		this.msg = msg;
	}



	public String getCodeValue() {
		this.codeValue =  ResponseCode.codes.get(super.getCode());
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

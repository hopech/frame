package com.sicilon.frame.sweb.http.invoke;
/**
 * Description: http请求接收bean
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月5日 下午7:08:47.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class AcceptBean {

	private Integer code; //响应编码
	private String codeValue; //编码内容
	private Object resultData; //数据
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	
	public Object getResultData() {
		return resultData;
	}
	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	
	
}

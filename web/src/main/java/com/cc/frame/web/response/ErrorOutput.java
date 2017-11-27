package com.cc.frame.web.response;

/**
 * @ClassName: ErrorOutput
 * @Description: 请求失败 响应信息
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class ErrorOutput extends BaseOutput{

	private String codeValue; // 编码信息
	
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

package com.sicilon.frame.sweb.exception;
/**
 * Description: 提示信息异常输出类
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月7日 下午1:51:12.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class MessageException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6330817473144669561L;
	
	/**
	 * 响应编码
	 */
	private Integer code;

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public MessageException(String msg){
		super(msg);
	}

	public MessageException(Integer code,String msg) {
		super(msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}

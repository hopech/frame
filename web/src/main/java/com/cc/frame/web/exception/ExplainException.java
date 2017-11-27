package com.cc.frame.web.exception;


import com.cc.frame.web.response.ResponseCode;

/**
 * @ClassName: ExplainException
 * @Description: 解释性异常
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class ExplainException extends Exception{

	private static final long serialVersionUID = -6330817473144669561L;
	
	/**
	 * 响应编码
	 */
	private Integer code;

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public ExplainException(String msg){
		super(msg);
		this.code = ResponseCode.BUSINESS_EXCEPTION;
	}

	public ExplainException(Integer code,String msg) {
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

package com.cc.frame.web.response;


/**
 * @ClassName: BaseOutput
 * @Description: 公共响应参数
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class BaseOutput {
	
	/** 响应编码 **/
	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	protected BaseOutput() {
		super();
	}
	
	
	
}

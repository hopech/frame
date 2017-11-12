package com.sicilon.frame.sweb.enums;


/**
 * Description: 项目环境枚举<br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年8月30日 下午12:05:35 <br/>
  <br/>UpdateTime：
  <br/>UpdateUser：
  <br/>UpdateNote：
  <br/>------------------------------
 */
public enum EnvironmentCode {

	DEBUG("debug"),
	
	/** 开发环境* */
	DEV("dev"),
	
	/** 真实环境* */
	REAL("real");
	
	private String envirCode;
	
	private EnvironmentCode(String envirCode) {
		this.envirCode = envirCode;
	}

	public String getEnvirCode() {
		return envirCode;
	}

	public void setEnvirCode(String envirCode) {
		this.envirCode = envirCode;
	}

	
}

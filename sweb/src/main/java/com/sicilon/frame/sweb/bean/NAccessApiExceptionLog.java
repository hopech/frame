package com.sicilon.frame.sweb.bean;

import java.util.Date;

/**
 * Description: 访问接口异常信息包装类<br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年9月15日 下午3:59:08 <br/>
  <br/>UpdateTime：
  <br/>UpdateUser：
  <br/>UpdateNote：
  <br/>------------------------------
 */
public class NAccessApiExceptionLog extends BaseInput{

	/** 源IP **/
	private String originIp;
	
	/**客户端ip**/
	private String clientIp;
	
	/**访问接口URL**/
	private String accessApiUrl;
	
	/**输入参数json**/
	private String inputJsonStr;
	
	/**访问时间**/
	private Date accessTime;
	
	/**异常类**/
	private String exceptionType;
	
	/**异常详情**/
	private String exceptionDetail;
	
	/**访问用户id**/
	private Long accessUserId;
	
	
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getAccessApiUrl() {
		return accessApiUrl;
	}
	public void setAccessApiUrl(String accessApiUrl) {
		this.accessApiUrl = accessApiUrl;
	}
	public String getInputJsonStr() {
		return inputJsonStr;
	}
	public void setInputJsonStr(String inputJsonStr) {
		this.inputJsonStr = inputJsonStr;
	}
	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public Long getAccessUserId() {
		return accessUserId;
	}
	public void setAccessUserId(Long accessUserId) {
		this.accessUserId = accessUserId;
	}
	public String getExceptionDetail() {
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}
	public String getOriginIp() {
		return originIp;
	}
	public void setOriginIp(String originIp) {
		this.originIp = originIp;
	}
	
	
}

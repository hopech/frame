package com.sicilon.frame.sweb.bean;
/**
 * Description: 接口访问监控输入信息<br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年9月14日 上午9:03:24 <br/>
  <br/>UpdateTime：
  <br/>UpdateUser：
  <br/>UpdateNote：
  <br/>------------------------------
 */
public class NAccessApiLogInfo extends BaseInput{

	/** 源IP **/
	private String originIp;
	
	/**客户端ip**/
	private String clientIp;
	
	/**访问url**/
	private String apiUrl;
	
	/**访问用户id**/
	private Long accessUserId;
	
	/**访问时间 (服务器时间)**/
	private String accessTime;
	
	/**输入参数json**/
	private String inputJsonStr;
	
	/**响应参数json**/
	private String responseJsonStr;
	
	/**花费时间**/
	private Long spendTime;

	public String getApiUrl() {
		return apiUrl;
	}

	public NAccessApiLogInfo setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
		return this;
	}

	public Long getAccessUserId() {
		return accessUserId;
	}

	public NAccessApiLogInfo setAccessUserId(Long accessUserId) {
		this.accessUserId = accessUserId;
		return this;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public NAccessApiLogInfo setAccessTime(String accessTime) {
		this.accessTime = accessTime;
		return this;
	}

	public String getInputJsonStr() {
		return inputJsonStr;
	}

	public NAccessApiLogInfo setInputJsonStr(String inputJsonStr) {
		this.inputJsonStr = inputJsonStr;
		return this;
	}

	public String getResponseJsonStr() {
		return responseJsonStr;
	}

	public NAccessApiLogInfo setResponseJsonStr(String responseJsonStr) {
		this.responseJsonStr = responseJsonStr;
		return this;
	}

	public Long getSpendTime() {
		return spendTime;
	}

	public NAccessApiLogInfo setSpendTime(Long spendTime) {
		this.spendTime = spendTime;
		return this;
	}

	public String getClientIp() {
		return clientIp;
	}

	public NAccessApiLogInfo setClientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}

	public String getOriginIp() {
		return originIp;
	}

	public NAccessApiLogInfo setOriginIp(String originIp) {
		this.originIp = originIp;
		return this;
	}



	


	
	
	
}

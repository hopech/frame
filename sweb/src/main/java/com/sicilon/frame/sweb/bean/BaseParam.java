package com.sicilon.frame.sweb.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月13日 上午8:59:37.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class BaseParam {

	@ApiModelProperty(value = "应用key" , required = true)
	private String appKey;//应用key
	
	
	//accesskey------secretkey
	
	@ApiModelProperty(value = "服务key" , required = true)
	private String accesskey; //服务key
	
	@ApiModelProperty(value = "方法名" , required = true)
	private String method; //方法名
	
	@ApiModelProperty(value = "版本" , required = true)
	private String version; //版本
	
	@ApiModelProperty(value = "盐" , required = true)
	private String nonce; //盐
	
	@ApiModelProperty(value = "时间戳" , required = true)
	private long timestamp; //时间戳
	
	@ApiModelProperty(value = "签名" , required = true)
	private String sign; //签名
	
	@ApiModelProperty(required = true)
	private String tokenId;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	
}

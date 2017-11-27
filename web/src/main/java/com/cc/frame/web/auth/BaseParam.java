package com.cc.frame.web.auth;


/**
 * @ClassName: BaseParam
 * @Description: 公共参数
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class BaseParam {

	private String appKey;//应用key
	
	private String accesskey; //服务key
	
	private String method; //方法名
	
	private String version; //版本
	
	private String nonce; //盐
	
	private long timestamp; //时间戳
	
	private String sign; //签名
	
	private String tokenId;//登录票据

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

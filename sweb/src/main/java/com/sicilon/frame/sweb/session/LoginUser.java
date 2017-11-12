package com.sicilon.frame.sweb.session;

import java.util.Date;

/**
 * Description: 登录用户信息封装<br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年9月14日 下午4:55:37 <br/>
 * <br/>
 * UpdateTime： <br/>
 * UpdateUser： <br/>
 * UpdateNote： <br/>
 * ------------------------------
 */
public class LoginUser {

	/** 用户id **/
	private Long userId;

	/** 用户账号 **/
	private String userAccount;
	
	/** 用户姓名 **/
	private String userRealName;
	
	/** 角色编码 **/
	private String roleCode;
	
	/** 角色名称 **/
	private String roleName;
	
	/** 组织id **/
	private Long orgId;

	/** tokenId **/
	private Long tokenId;

	/** 用户最后一次访问时间 **/
	private Date lastAccessTime;

	/** token过期时长 **/
	private Long tokenTimeout;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Long getTokenTimeout() {
		return tokenTimeout;
	}

	public void setTokenTimeout(Long tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}

}

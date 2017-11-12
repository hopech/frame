package com.sicilon.frame.sweb.session.comuser;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 登录用户信息
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月6日 下午2:35:59.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class LoginUserInfo {
	
	private Long userId = 1L; //用户主键
	private String userName = "";//用户姓名
	private String realName = "";//真实姓名
	
	private List<RoleOutput> roleNames = new ArrayList<RoleOutput>();//角色名称
	private List<GroupOutput> groupNames = new ArrayList<GroupOutput>();//分组名称
	
	private Long orgId; //组织id
	private String orgName = "";//组织名称
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<RoleOutput> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<RoleOutput> roleNames) {
		this.roleNames = roleNames;
	}

	public List<GroupOutput> getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(List<GroupOutput> groupNames) {
		this.groupNames = groupNames;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}





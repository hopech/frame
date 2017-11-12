package com.sicilon.frame.sweb.session.comuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Description: 角色输出实体
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月13日 下午5:51:40.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel(value="角色输出类")
public class RoleOutput {
	@ApiModelProperty(value = "主键")
	private Long roleId; //主键
	
	@ApiModelProperty(value = "备注")
	private String remark; //备注
	
	@ApiModelProperty(value = "角色编码")
	private String roleCode; //角色编码
	
	@ApiModelProperty(value = "角色名称")
	private String roleName; //角色名称
	
	@ApiModelProperty(value = "角色拥有用户")
	private String userName;


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}

package com.sicilon.frame.sweb.session.comuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Description: 分组output
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月13日 下午6:16:36.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ApiModel
public class GroupOutput {

	@ApiModelProperty("分组id")
	private Long groupId;
	
	@ApiModelProperty("分组名")
	private String groupName = "";
	
	@ApiModelProperty("分组编码")
	private String groupCode = "";
	
	@ApiModelProperty("备注")
	private String remark = "";
	
	@ApiModelProperty("用户名")
	private String userName = "";
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
}

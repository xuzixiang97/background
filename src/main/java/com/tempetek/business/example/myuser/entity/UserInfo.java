package com.tempetek.business.example.myuser.entity;

import com.tempetek.core.base.LongId;

import java.util.Date;

public class UserInfo extends LongId {

	private static final long serialVersionUID = 1L;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 人员id
	private Long personId;
	// 人员名称
	private String personStr;
	// 是否上锁
	private Integer isLocked;
	// 是否
	private String isLockedStr;
	// 创造者
	private String creator;
	// 创造时间
	private Date createTime;
	// 修改者
	private String modifier;
	// 修改时间
	private Date modifiedTime;
	// 描述
	private String description;
	//角色id
	private  Long roleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonStr() {
		return personStr;
	}

	public void setPersonStr(String personStr) {
		this.personStr = personStr;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public String getIsLockedStr() {
		return isLockedStr;
	}

	public void setIsLockedStr(String isLockedStr) {
		this.isLockedStr = isLockedStr;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
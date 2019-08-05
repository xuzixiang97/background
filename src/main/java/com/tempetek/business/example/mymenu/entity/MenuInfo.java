package com.tempetek.business.example.mymenu.entity;

import com.tempetek.core.base.LongId;

import java.util.Date;

public class MenuInfo extends LongId {

	private static final long serialVersionUID = 1L;
	// 编码
	private String code;
	// text
	private String text;
	// 类型
	private Long type;
	// 路径
	private String linkUrl;
	// 父节点id
	private Long pid;
	// 排序值
	private String sortValue;
	// 图标
	private String iconcls;
	// 样式
	private String arecls;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getArecls() {
		return arecls;
	}

	public void setArecls(String arecls) {
		this.arecls = arecls;
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
}
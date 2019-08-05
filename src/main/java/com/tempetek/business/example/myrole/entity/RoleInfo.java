package com.tempetek.business.example.myrole.entity;

import com.tempetek.core.base.LongId;

import java.util.Date;

/**
 * Created by xuzx on 2019/7/3.
 */
public class RoleInfo extends LongId {

    private static final long serialVersionUID = 1L;
    // 编号
    private String code;
    // 名称
    private String name;
    // 父节点id
    private Long pid;
    // 是否是管理员
    private Integer isAdmin;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer is_admin) {
        this.isAdmin = is_admin;
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

    public void setCreateTime(Date create_time) {
        this.createTime = create_time;
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

    public void setModifiedTime(Date modified_time) {
        this.modifiedTime = modified_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package com.tempetek.business.example.people.entity;

import com.tempetek.core.base.LongId;

import java.util.Date;

/**
 * Created by xuzx on 2019/7/1.
 */
public class PeopleInfo extends LongId {

    private static final long serialVersionUID = 1L;
    // 编号
    private String code;
    // 名称
    private String name;
    // 性别
    private Integer sex;
    // 性别(名称)
    private String sexStr;
    // 电话号码
    private String telephone;
    // 手机号码
    private String cellphone;
    // 邮箱
    private String email;
    // 职务
    private Integer duty;
    // 状态
    private Integer state;
    // 职务（名称）
    private String dutyStr;
    // 状态（名称）
    private String stateStr;
    // 身份证号
    private String idCard;
    // 编码
    private String portraut;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDuty() {
        return duty;
    }

    public void setDuty(Integer duty) {
        this.duty = duty;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String id_card) {
        this.idCard = id_card;
    }

    public String getPortraut() {
        return portraut;
    }

    public void setPortraut(String portraut) {
        this.portraut = portraut;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setCreate_time(Date create_time) {
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

    public String getDutyStr() {
        return dutyStr;
    }

    public void setDutyStr(String dutyStr) {
        this.dutyStr = dutyStr;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

}

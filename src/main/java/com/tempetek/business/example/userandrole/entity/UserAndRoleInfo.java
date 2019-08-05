package com.tempetek.business.example.userandrole.entity;

import com.tempetek.core.base.LongId;

/**
 * Created by xuzx on 2019/7/3.
 */
public class UserAndRoleInfo extends LongId {

    private static final long serialVersionUID = 1L;
    //用户id
    private Long userId;
    //角色id
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}


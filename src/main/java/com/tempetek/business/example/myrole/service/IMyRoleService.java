package com.tempetek.business.example.myrole.service;

import com.tempetek.business.example.myrole.entity.RoleInfo;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

/**
 * Created by xuzx on 2019/7/1.
 */
public interface IMyRoleService extends AbstractBasicService<RoleInfo, Long> {

    public List<RoleInfo> findByPid(Long id);

    public List<RoleInfo> findByCondition(RoleInfo condition);

    public RoleInfo findByCode(String code);

}
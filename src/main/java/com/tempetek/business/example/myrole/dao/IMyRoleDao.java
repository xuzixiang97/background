package com.tempetek.business.example.myrole.dao;

import com.tempetek.business.example.myrole.entity.RoleInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IMyRoleDao extends AbstractBasicDao<RoleInfo, Long> {

    public List<RoleInfo> findByPid(Long id);

    public List<RoleInfo> findAll();

    public List<RoleInfo> findByCondition(RoleInfo condition);

    public RoleInfo findByCode(String code);

    public Pagination<RoleInfo> findByPager(Pagination<RoleInfo> pager, RoleInfo condition);

    public void deleteByIds(Long[] ids);

}
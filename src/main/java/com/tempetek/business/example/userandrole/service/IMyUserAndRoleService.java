package com.tempetek.business.example.userandrole.service;

import com.tempetek.business.example.userandrole.entity.UserAndRoleInfo;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IMyUserAndRoleService extends AbstractBasicDao<UserAndRoleInfo, Long> {

    public Long[] findUserByRoleId(Long id);

    public Long[] findByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo);

    public void deleteByIds(Long[] ids);

    public void deleteByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo);

    public void deleteByUserIdAndRoleids(List<UserAndRoleInfo> list);

    public void insertList(List<UserAndRoleInfo> userAndRoleInfoList);

}
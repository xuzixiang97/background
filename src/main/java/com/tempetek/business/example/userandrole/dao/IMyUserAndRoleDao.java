package com.tempetek.business.example.userandrole.dao;

import com.tempetek.business.example.userandrole.entity.UserAndRoleInfo;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

public interface IMyUserAndRoleDao extends AbstractBasicService<UserAndRoleInfo, Long> {

    public List<UserAndRoleInfo> findUserByRoleId(Long id);

    public List<UserAndRoleInfo> findByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo);

    public void deleteByIds(Long[] ids);

    public void deleteByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo);

}
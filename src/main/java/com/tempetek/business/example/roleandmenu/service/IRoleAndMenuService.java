package com.tempetek.business.example.roleandmenu.service;

import com.tempetek.business.example.roleandmenu.entity.RoleAndMenuInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

public interface IRoleAndMenuService extends AbstractBasicService<RoleAndMenuInfo, Long> {

    public List<RoleAndMenuInfo> findByCondition(RoleAndMenuInfo condition);

    public Pagination<RoleAndMenuInfo> findByPager(Pagination<RoleAndMenuInfo> pager, RoleAndMenuInfo condition);

    public void deleteByIds(Long[] ids);

    public List<RoleAndMenuInfo> findByRoleid (Long roleid);

    public void saveMenuTree(Long[] menuIds, Long roleId);

}

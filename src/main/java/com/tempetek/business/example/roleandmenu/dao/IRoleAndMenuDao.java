package com.tempetek.business.example.roleandmenu.dao;

import com.tempetek.business.example.roleandmenu.entity.RoleAndMenuInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IRoleAndMenuDao extends AbstractBasicDao<RoleAndMenuInfo, Long> {
	
    public List<RoleAndMenuInfo> findByCondition(RoleAndMenuInfo condition);
    
    public Pagination<RoleAndMenuInfo> findByPager(Pagination<RoleAndMenuInfo> pager, RoleAndMenuInfo condition);
    
    public void deleteByIds(Long[] ids);

    public List<RoleAndMenuInfo> findByRoleid (Long roleid);

    public List<RoleAndMenuInfo> findByRoleidAndMenuid (RoleAndMenuInfo roleAndMenuInfo);
	
}
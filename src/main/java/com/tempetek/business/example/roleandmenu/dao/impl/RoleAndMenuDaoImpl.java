package com.tempetek.business.example.roleandmenu.dao.impl;
import com.tempetek.business.example.roleandmenu.dao.IRoleAndMenuDao;
import com.tempetek.business.example.roleandmenu.entity.RoleAndMenuInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleAndMenuDaoImpl extends AbstractBasicDaoImpl<RoleAndMenuInfo, Long> implements IRoleAndMenuDao {
	
	@Override
	public List<RoleAndMenuInfo> findByCondition(RoleAndMenuInfo condition) {
		return this.selectList("findByCondition", condition);
	}

	@Override
	public Pagination<RoleAndMenuInfo> findByPager(Pagination<RoleAndMenuInfo> pager, RoleAndMenuInfo condition) {
		return this.findByPager(pager, "selectPager", "countPager", condition);
	}
	
	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}

	@Override
	public List<RoleAndMenuInfo> findByRoleid(Long id) {
		return this.selectList("findByRoleid", id);
	}

	@Override
	public List<RoleAndMenuInfo> findByRoleidAndMenuid(RoleAndMenuInfo roleAndMenuInfo) {
		return this.selectList("findByRoleidAndMenuid", roleAndMenuInfo);
	}

	@Override
	protected String getNamespace() {
		return RoleAndMenuInfo.class.getName();
	}

}
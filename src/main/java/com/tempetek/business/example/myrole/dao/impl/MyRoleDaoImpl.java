package com.tempetek.business.example.myrole.dao.impl;


import com.tempetek.business.example.myrole.dao.IMyRoleDao;
import com.tempetek.business.example.myrole.entity.RoleInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyRoleDaoImpl extends AbstractBasicDaoImpl<RoleInfo,Long> implements IMyRoleDao {

	@Override
	protected String getNamespace() {
		return RoleInfo.class.getName();
	}

	@Override
	public List<RoleInfo> findByPid(Long id) {
		return this.selectList("findByPid", id);
	}

	@Override
	public List<RoleInfo> findAll() {
		return this.selectList("findAll");
	}

	@Override
	public List<RoleInfo> findByCondition(RoleInfo condition) {
		return this.selectList("findByCondition", condition);
	}

	@Override
	public Pagination<RoleInfo> findByPager(Pagination<RoleInfo> pager, RoleInfo condition) {
		return this.findByPager(pager, "selectPager", "countPager", condition);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}

	@Override
	public RoleInfo findByCode(String code) {
		return this.selectOne("findByCode",code);
	}

}
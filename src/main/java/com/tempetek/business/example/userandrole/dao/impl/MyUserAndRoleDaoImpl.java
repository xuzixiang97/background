package com.tempetek.business.example.userandrole.dao.impl;


import com.tempetek.business.example.userandrole.dao.IMyUserAndRoleDao;
import com.tempetek.business.example.userandrole.entity.UserAndRoleInfo;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyUserAndRoleDaoImpl extends AbstractBasicDaoImpl<UserAndRoleInfo,Long> implements IMyUserAndRoleDao {

	@Override
	protected String getNamespace() {
		return UserAndRoleInfo.class.getName();
	}

	@Override
	public List<UserAndRoleInfo> findUserByRoleId(Long id) {
		return this.selectList("findUserByRoleId", id);
	}

	@Override
	public List<UserAndRoleInfo> findByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo) {
		return this.selectList("findByUserIdAndRoleid", userAndRoleInfo);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}

	@Override
	public void deleteByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo) {
		this.delete("deleteByUserIdAndRoleid", userAndRoleInfo);
	}

	@Override
	public void save(UserAndRoleInfo userAndRoleInfo) {
	}
}
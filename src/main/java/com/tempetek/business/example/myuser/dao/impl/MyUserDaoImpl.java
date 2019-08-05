package com.tempetek.business.example.myuser.dao.impl;

import com.tempetek.business.example.myuser.dao.IMyUserDao;
import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyUserDaoImpl extends AbstractBasicDaoImpl<UserInfo, Long> implements IMyUserDao {

	@Override
	protected String getNamespace() {
		return UserInfo.class.getName();
	}

	@Override
	public Pagination<UserInfo> findByPager(Pagination<UserInfo> pager, UserInfo condition) {
		return this.findByPager(pager, "selectPager", "countPager", condition);
	}

	@Override
	public Pagination<UserInfo> findByPagerRole(Pagination<UserInfo> pager, UserInfo condition) {
		return this.findByPager(pager, "selectPagerRole", "countPagerRole", condition);
	}

	@Override
	public Pagination<UserInfo> findByPagerNotRole(Pagination<UserInfo> pager, UserInfo condition) {
		return this.findByPager(pager, "selectPagerNotRole", "countPagerNotRole", condition);
	}

	@Override
	public List<UserInfo> findByIds(Long[] ids) {
		return this.selectList("findByIds", ids);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}

	@Override
	public void deleteByPersonIds(Long[] ids) {
		this.delete("deleteByPersonIds", ids);
	}

	@Override
	public List<UserInfo> findByPersonId(Long personId) {
		return this.selectList("findByPersonId", personId);
	}

	@Override
	public UserInfo findByUserName(String username) {
		return this.selectOne("findByUserName",username);
	}

}
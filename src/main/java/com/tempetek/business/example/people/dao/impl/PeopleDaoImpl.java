package com.tempetek.business.example.people.dao.impl;

import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.business.example.people.dao.IPeopleDao;
import com.tempetek.business.example.people.entity.PeopleInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleDaoImpl extends AbstractBasicDaoImpl<PeopleInfo, Long> implements IPeopleDao{

	@Override
	public List<AccountInfo> findByCondition(AccountInfo condition) {
		return this.selectList("findByCondition", condition);
	}

	@Override
	protected String getNamespace() {
		return PeopleInfo.class.getName();
	}

	@Override
	public Pagination<PeopleInfo> findByPager(Pagination<PeopleInfo> pager, PeopleInfo condition) {
		return this.findByPager(pager, "selectPager", "countPager", condition);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}

	@Override
	public PeopleInfo findByCode(String code) {
		return this.selectOne("findByCode",code);
	}

}
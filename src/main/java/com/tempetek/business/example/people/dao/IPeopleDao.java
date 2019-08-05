package com.tempetek.business.example.people.dao;

import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.business.example.people.entity.PeopleInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IPeopleDao extends AbstractBasicDao<PeopleInfo, Long> {

    public List<AccountInfo> findByCondition(AccountInfo condition);

    public Pagination<PeopleInfo> findByPager(Pagination<PeopleInfo> pager, PeopleInfo condition);

    public void deleteByIds(Long[] ids);

    public PeopleInfo findByCode(String code);
	
}
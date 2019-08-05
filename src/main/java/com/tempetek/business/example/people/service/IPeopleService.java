package com.tempetek.business.example.people.service;

import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.business.example.people.entity.PeopleInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

/**
 * Created by xuzx on 2019/7/1.
 */
public interface IPeopleService extends AbstractBasicService<PeopleInfo, Long> {

    public List<AccountInfo> findByCondition(AccountInfo condition);

    public Pagination<PeopleInfo> findByPager(Pagination<PeopleInfo> pager, PeopleInfo condition);

    public void deleteByIds(Long[] ids);

    public PeopleInfo findByCode(String code);

}
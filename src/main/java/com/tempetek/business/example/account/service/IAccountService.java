package com.tempetek.business.example.account.service;

import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

public interface IAccountService extends AbstractBasicService<AccountInfo, Long> {


    public List<AccountInfo> findByCondition(AccountInfo condition);

    public Pagination<AccountInfo> findByPager(Pagination<AccountInfo> pager, AccountInfo condition);

    public void deleteByIds(Long[] ids);

}

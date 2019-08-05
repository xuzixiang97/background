package com.tempetek.business.example.account.dao;

import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IAccountDao extends AbstractBasicDao<AccountInfo, Long> {
	
    public List<AccountInfo> findByCondition(AccountInfo condition);
    
    public Pagination<AccountInfo> findByPager(Pagination<AccountInfo> pager, AccountInfo condition);
    
    public void deleteByIds(Long[] ids);
	
}
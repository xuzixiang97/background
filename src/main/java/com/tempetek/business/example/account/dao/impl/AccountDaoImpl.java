package com.tempetek.business.example.account.dao.impl;

import com.tempetek.business.example.account.dao.IAccountDao;
import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl extends AbstractBasicDaoImpl<AccountInfo, Long> implements IAccountDao {
	
	@Override
	public List<AccountInfo> findByCondition(AccountInfo condition) {
		return this.selectList("findByCondition", condition);
	}

	@Override
	public Pagination<AccountInfo> findByPager(Pagination<AccountInfo> pager, AccountInfo condition) {
		return this.findByPager(pager, "selectPager", "countPager", condition);
	}
	
	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}
	
	@Override
	protected String getNamespace() {
		return AccountInfo.class.getName();
	}

}
package com.tempetek.business.example.account.service.impl;

import com.tempetek.business.example.account.dao.IAccountDao;
import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.business.example.account.service.IAccountService;
import com.tempetek.business.systemparam.enumerate.DataType;
import com.tempetek.business.systemparam.enumerate.FieldType;
import com.tempetek.cache.manager.CacheManager;
import com.tempetek.core.Pagination;
import com.tempetek.core.constants.CommConstants;
import com.tempetek.orm.AbstractBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl extends AbstractBasicServiceImpl<AccountInfo, Long> implements IAccountService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IAccountDao iAccountDao;
    
    @Override
	public AccountInfo findById(Long id) {
    	AccountInfo accountInfo = cacheManager.get(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), id);
    	
    	if(accountInfo==null){
    		accountInfo = iAccountDao.findById(id);
    		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), accountInfo);
    	}
		return accountInfo;
	}

	@Override
	public void deleteById(Long id) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), id);
		iAccountDao.deleteById(id);
}

	@Override
	public void insert(AccountInfo condition) {
		iAccountDao.insert(condition);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), condition);
	}

	@Override
	public void update(AccountInfo condition) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), condition.getId());
		iAccountDao.update(condition);
	}
	
	@Override
	public List<AccountInfo> findByCondition(AccountInfo condition) {
		return iAccountDao.findByCondition(condition);
	}

	@Override
	public Pagination<AccountInfo> findByPager(Pagination<AccountInfo> pager, AccountInfo condition) {
		return iAccountDao.findByPager(pager, condition);
	}
	
	@Override
	public void deleteByIds(Long[] ids) {
		for(Long id : ids){
			cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), id);
		}
    	iAccountDao.deleteByIds(ids);
	}

}
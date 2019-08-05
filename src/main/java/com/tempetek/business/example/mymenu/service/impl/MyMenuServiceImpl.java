package com.tempetek.business.example.mymenu.service.impl;


import com.tempetek.business.example.mymenu.dao.IMyMenuDao;
import com.tempetek.business.example.mymenu.entity.MenuInfo;
import com.tempetek.business.example.mymenu.service.IMyMenuService;
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
public class MyMenuServiceImpl extends AbstractBasicServiceImpl<MenuInfo, Long> implements IMyMenuService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IMyMenuDao iMyMenuDao;
    
    @Override
	public MenuInfo findById(Long id) {
		MenuInfo menuInfo = cacheManager.get(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), id);
    	
    	if(menuInfo==null){
			menuInfo = iMyMenuDao.findById(id);
    		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), menuInfo);
    	}
		return menuInfo;
	}

	@Override
	public void deleteById(Long id) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), id);
		iMyMenuDao.deleteById(id);
}

	@Override
	public void insert(MenuInfo condition) {
		iMyMenuDao.insert(condition);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), condition);
	}

	@Override
	public void update(MenuInfo condition) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), condition.getId());
		iMyMenuDao.update(condition);
	}

	@Override
	public List<MenuInfo> findByCondition(MenuInfo condition) {
		return iMyMenuDao.findByCondition(condition);
	}

	@Override
	public Pagination<MenuInfo> findByPager(Pagination<MenuInfo> pager, MenuInfo condition) {
		System.out.println("service");
		return iMyMenuDao.findByPager(pager, condition);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for(Long id : ids){
			cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.TPMENU.getName(), FieldType.ID.getName(), id);
		}
    	iMyMenuDao.deleteByIds(ids);
	}

}
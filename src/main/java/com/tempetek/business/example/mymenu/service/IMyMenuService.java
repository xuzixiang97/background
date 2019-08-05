package com.tempetek.business.example.mymenu.service;

import com.tempetek.business.example.mymenu.entity.MenuInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

public interface IMyMenuService extends AbstractBasicService<MenuInfo, Long> {
	
    public List<MenuInfo> findByCondition(MenuInfo condition);
    
    public Pagination<MenuInfo> findByPager(Pagination<MenuInfo> pager, MenuInfo condition);
    
    public void deleteByIds(Long[] ids);

}

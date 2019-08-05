package com.tempetek.business.example.mymenu.dao;

import com.tempetek.business.example.mymenu.entity.MenuInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IMyMenuDao extends AbstractBasicDao<MenuInfo, Long> {
	
    public List<MenuInfo> findByCondition(MenuInfo condition);
    
    public Pagination<MenuInfo> findByPager(Pagination<MenuInfo> pager, MenuInfo condition);
    
    public void deleteByIds(Long[] ids);

    public List<MenuInfo> findAll();

}
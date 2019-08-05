package com.tempetek.business.example.mymenu.dao.impl;


import com.tempetek.business.example.mymenu.dao.IMyMenuDao;
import com.tempetek.business.example.mymenu.entity.MenuInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyMenuDaoImpl extends AbstractBasicDaoImpl<MenuInfo, Long> implements IMyMenuDao {
	
	@Override
	public List<MenuInfo> findByCondition(MenuInfo condition) {
		return this.selectList("findByCondition", condition);
	}

	@Override
	public Pagination<MenuInfo> findByPager(Pagination<MenuInfo> pager, MenuInfo condition) {
		return this.findByPager(pager, "selectPager", "countPager", condition);
	}
	
	@Override
	public void deleteByIds(Long[] ids) {
		this.delete("deleteByIds", ids);
	}
	
	@Override
	protected String getNamespace() {
		return MenuInfo.class.getName();
	}

	@Override
	public List<MenuInfo> findAll() {
		return this.selectList("findAll");
	}

}
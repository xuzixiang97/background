package com.tempetek.business.example.roleandmenu.service.impl;

import com.tempetek.business.example.roleandmenu.dao.IRoleAndMenuDao;
import com.tempetek.business.example.roleandmenu.entity.RoleAndMenuInfo;
import com.tempetek.business.example.roleandmenu.service.IRoleAndMenuService;
import com.tempetek.business.systemparam.enumerate.DataType;
import com.tempetek.business.systemparam.enumerate.FieldType;
import com.tempetek.cache.manager.CacheManager;
import com.tempetek.core.Pagination;
import com.tempetek.core.constants.CommConstants;
import com.tempetek.orm.AbstractBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleAndMenuServiceImpl extends AbstractBasicServiceImpl<RoleAndMenuInfo, Long> implements IRoleAndMenuService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IRoleAndMenuDao iRoleAndMenuDao;
    
    @Override
	public RoleAndMenuInfo findById(Long id) {
		RoleAndMenuInfo roleAndMenuInfo = cacheManager.get(CommConstants.PLATFORM_RECEPTION, DataType.ROLEANDMENU.getName(), FieldType.ID.getName(), id);
    	
    	if(roleAndMenuInfo==null){
			roleAndMenuInfo = iRoleAndMenuDao.findById(id);
    		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.ROLEANDMENU.getName(), roleAndMenuInfo);
    	}
		return roleAndMenuInfo;
	}

	@Override
	public void deleteById(Long id) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.ROLEANDMENU.getName(), FieldType.ID.getName(), id);
		iRoleAndMenuDao.deleteById(id);
	}

	@Override
	public void insert(RoleAndMenuInfo condition) {
		iRoleAndMenuDao.insert(condition);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.ROLEANDMENU.getName(), condition);
	}

	@Override
	public void update(RoleAndMenuInfo condition) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.ROLEANDMENU.getName(), FieldType.ID.getName(), condition.getId());
		iRoleAndMenuDao.update(condition);
	}
	
	@Override
	public List<RoleAndMenuInfo> findByCondition(RoleAndMenuInfo condition) {
		return iRoleAndMenuDao.findByCondition(condition);
	}

	@Override
	public Pagination<RoleAndMenuInfo> findByPager(Pagination<RoleAndMenuInfo> pager, RoleAndMenuInfo condition) {
		return iRoleAndMenuDao.findByPager(pager, condition);
	}
	
	@Override
	public void deleteByIds(Long[] ids) {
		for(Long id : ids){
			cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.ROLEANDMENU.getName(), FieldType.ID.getName(), id);
		}
		iRoleAndMenuDao.deleteByIds(ids);
	}

	@Override
	public List<RoleAndMenuInfo> findByRoleid(Long roleid) {
		return iRoleAndMenuDao.findByRoleid(roleid);
	}

	@Override
	@Transactional
	public void saveMenuTree(Long[] menuIds, Long roleId) {
		RoleAndMenuInfo roleAndMenuInfo = new RoleAndMenuInfo();
		roleAndMenuInfo.setRoleId(roleId);
		List<RoleAndMenuInfo> deleteList =  iRoleAndMenuDao.findByCondition(roleAndMenuInfo);

		for(RoleAndMenuInfo RoleAndMenuInfo : deleteList){
			iRoleAndMenuDao.deleteById(RoleAndMenuInfo.getId());
		}

		for(Long menuId : menuIds){
			RoleAndMenuInfo item = new RoleAndMenuInfo();
			item.setRoleId(roleId);
			item.setMenuId(menuId);
			iRoleAndMenuDao.insert(item);
		}
	}
}
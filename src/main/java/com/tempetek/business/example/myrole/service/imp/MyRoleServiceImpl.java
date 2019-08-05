package com.tempetek.business.example.myrole.service.imp;

import com.tempetek.business.example.myrole.dao.IMyRoleDao;
import com.tempetek.business.example.myrole.entity.RoleInfo;
import com.tempetek.business.example.myrole.service.IMyRoleService;
import com.tempetek.business.systemparam.enumerate.DataType;
import com.tempetek.business.systemparam.enumerate.FieldType;
import com.tempetek.cache.manager.CacheManager;
import com.tempetek.core.constants.CommConstants;
import com.tempetek.orm.AbstractBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRoleServiceImpl extends AbstractBasicServiceImpl<RoleInfo, Long> implements IMyRoleService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IMyRoleDao iMyRoleDao;

	@Override
	public RoleInfo findById(Long aLong) {
		return iMyRoleDao.findById(aLong);
	}

	@Override
	public void deleteById(Long id) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.ROLE.getName(), FieldType.ID.getName(), id);
		iMyRoleDao.deleteById(id);

	}

	@Override
	public void insert(RoleInfo condition) {
		iMyRoleDao.insert(condition);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.ROLE.getName(), condition);
	}

	@Override
	public void update(RoleInfo condition) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.ROLE.getName(), FieldType.ID.getName(), condition.getId());
		iMyRoleDao.update(condition);
	}

	@Override
	public List<RoleInfo> findByPid(Long id) {
		return iMyRoleDao.findByPid(id);
	}

	@Override
	public List<RoleInfo> findByCondition(RoleInfo condition) {
		return iMyRoleDao.findByCondition(condition);
	}

	@Override
	public RoleInfo findByCode(String code) {
		return iMyRoleDao.findByCode(code);
	}

}
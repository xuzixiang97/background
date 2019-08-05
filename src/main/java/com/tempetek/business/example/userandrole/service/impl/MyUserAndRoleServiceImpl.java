package com.tempetek.business.example.userandrole.service.impl;

import com.tempetek.business.example.userandrole.dao.IMyUserAndRoleDao;
import com.tempetek.business.example.userandrole.entity.UserAndRoleInfo;
import com.tempetek.business.example.userandrole.service.IMyUserAndRoleService;
import com.tempetek.business.systemparam.enumerate.DataType;
import com.tempetek.business.systemparam.enumerate.FieldType;
import com.tempetek.cache.manager.CacheManager;
import com.tempetek.core.Pagination;
import com.tempetek.core.constants.CommConstants;
import com.tempetek.orm.AbstractBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserAndRoleServiceImpl extends AbstractBasicServiceImpl<UserAndRoleInfo, Long> implements IMyUserAndRoleService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IMyUserAndRoleDao iMyUserAndRoleDao;

	@Override
	public UserAndRoleInfo findById(Long aLong) {
		return null;
	}

	@Override
	public void deleteById(Long aLong) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.USERANDROLE.getName(), FieldType.ID.getName(), aLong);
		iMyUserAndRoleDao.deleteById(aLong);

	}

	@Override
	public void insert(UserAndRoleInfo userAndRoleInfo) {
		iMyUserAndRoleDao.insert(userAndRoleInfo);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.USERANDROLE.getName(), userAndRoleInfo);

	}

	@Override
	public void update(UserAndRoleInfo userAndRoleInfo) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.USERANDROLE.getName(), FieldType.ID.getName(), userAndRoleInfo.getId());
		iMyUserAndRoleDao.update(userAndRoleInfo);
	}

	@Override
	public Pagination<UserAndRoleInfo> findByPager(Pagination<UserAndRoleInfo> pagination, String s, String s1, UserAndRoleInfo userAndRoleInfo) {
		return null;
	}

	@Override
	@Transactional
	public void deleteByIds(Long[] ids) {
		for(Long id : ids){
			cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.USERANDROLE.getName(), FieldType.ID.getName(), id);
		}
		iMyUserAndRoleDao.deleteByIds(ids);
	}

	@Override
	public void deleteByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo) {
		iMyUserAndRoleDao.deleteByUserIdAndRoleid(userAndRoleInfo);
	}

	@Override
	@Transactional
	public void deleteByUserIdAndRoleids(List<UserAndRoleInfo> list) {
		for (UserAndRoleInfo userAndRoleInfo : list){
			this.deleteByUserIdAndRoleid(userAndRoleInfo);
		}
	}

	@Override
	@Transactional
	public void insertList(List<UserAndRoleInfo> userAndRoleInfoList) {
		for (UserAndRoleInfo userAndRoleInfo : userAndRoleInfoList){
			this.insert(userAndRoleInfo);
		}
	}

	@Override
	public Long[] findUserByRoleId(Long id) {
		List<Long> ll = new ArrayList<Long>();
		List<UserAndRoleInfo> list=iMyUserAndRoleDao.findUserByRoleId(id);

		for(int i = 0 ; i < list.size() ; i++) {
			ll.add(list.get(i).getUserId());
			System.out.println(list.get(i).getUserId());
		}
		Long[] longs = ll.toArray(new Long[ll.size()]);
		return longs;
	}

	@Override
	public Long[] findByUserIdAndRoleid(UserAndRoleInfo userAndRoleInfo) {
		List<UserAndRoleInfo> byUserIdAndRoleid = iMyUserAndRoleDao.findByUserIdAndRoleid(userAndRoleInfo);
		return null;
	}

}
package com.tempetek.business.example.myuser.service.imp;

import com.tempetek.business.example.myuser.dao.IMyUserDao;
import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.business.example.myuser.service.IMyUserService;
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
public class MyUserServiceImpl extends AbstractBasicServiceImpl<UserInfo, Long> implements IMyUserService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IMyUserDao iMyUserDao;

	@Override
	public Pagination<UserInfo> findByPager(Pagination<UserInfo> pager, UserInfo condition) {
		return iMyUserDao.findByPager(pager, condition);
	}

	@Override
	public Pagination<UserInfo> findByPagerRole(Pagination<UserInfo> pager, UserInfo condition) {
		return iMyUserDao.findByPagerRole(pager, condition);
	}

	@Override
	public Pagination<UserInfo> findByPagerNotRole(Pagination<UserInfo> pager, UserInfo condition) {
		return iMyUserDao.findByPagerNotRole(pager, condition);
	}

	@Override
	public List<UserInfo> findByIds(Long[] ids) {
		return iMyUserDao.findByIds(ids);
	}


	@Override
	public void deleteByIds(Long[] ids) {
		for(Long id : ids){
			cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.USER.getName(), FieldType.ID.getName(), id);
		}
		iMyUserDao.deleteByIds(ids);
	}

	@Override
	public UserInfo findByUserName(String username) {
		return iMyUserDao.findByUserName(username);
	}

	@Override
	public List<UserInfo> findByPersonId(Long id) {
		return iMyUserDao.findByPersonId(id);
	}


	@Override
	public UserInfo findById(Long id) {
		UserInfo userInfo = cacheManager.get(CommConstants.PLATFORM_RECEPTION, DataType.USER.getName(), FieldType.ID.getName(), id);

		if(userInfo==null){
			userInfo = iMyUserDao.findById(id);
			cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.USER.getName(), userInfo);
		}
		return userInfo;
	}

	@Override
	public void deleteById(Long id) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.USER.getName(), FieldType.ID.getName(), id);
		iMyUserDao.deleteById(id);

	}

	@Override
	public void insert(UserInfo userInfo) {
		iMyUserDao.insert(userInfo);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.USER.getName(), userInfo);

	}

	@Override
	public void update(UserInfo userInfo) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.USER.getName(), FieldType.ID.getName(), userInfo.getId());
		iMyUserDao.update(userInfo);

	}

}
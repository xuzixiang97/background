package com.tempetek.business.example.people.service.imp;

import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.business.example.myuser.dao.IMyUserDao;
import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.business.example.people.dao.IPeopleDao;
import com.tempetek.business.example.people.entity.PeopleInfo;
import com.tempetek.business.example.people.service.IPeopleService;
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
public class PeopleServiceImpl extends AbstractBasicServiceImpl<PeopleInfo, Long> implements IPeopleService {
	
	@Autowired
	private CacheManager cacheManager;
	
    @Autowired
    private IPeopleDao iPeopleDao;

	@Autowired
    private IMyUserDao iMyUserDao;

	@Override
	public Pagination<PeopleInfo> findByPager(Pagination<PeopleInfo> pager, PeopleInfo condition) {
		return iPeopleDao.findByPager(pager, condition);
	}

	@Override
	@Transactional
	public void deleteByIds(Long[] ids) {
		for(Long id : ids){
			cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.PEOPLE.getName(), FieldType.ID.getName(), id);
		}

		iPeopleDao.deleteByIds(ids);

		for(Long id : ids){
			List<UserInfo> list = iMyUserDao.findByPersonId(id);

			for(UserInfo userInfo : list){
				iMyUserDao.deleteById(userInfo.getId());
			}
		}
	}

	@Override
	public PeopleInfo findByCode(String code) {
		return iPeopleDao.findByCode(code);
	}

	@Override
	public PeopleInfo findById(Long id) {
		PeopleInfo peopleInfo = cacheManager.get(CommConstants.PLATFORM_RECEPTION, DataType.PEOPLE.getName(), FieldType.ID.getName(), id);

		if(peopleInfo==null){
			peopleInfo = iPeopleDao.findById(id);
			cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.PEOPLE.getName(), peopleInfo);
		}
		return peopleInfo;
	}

	@Override
	public void deleteById(Long id) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.PEOPLE.getName(), FieldType.ID.getName(), id);
		iPeopleDao.deleteById(id);
	}
	@Override
	public List<AccountInfo> findByCondition(AccountInfo condition) {
		return iPeopleDao.findByCondition(condition);
	}


	@Override
	public void insert(PeopleInfo peopleInfo) {
		iPeopleDao.insert(peopleInfo);
		cacheManager.add(CommConstants.PLATFORM_RECEPTION, DataType.PEOPLE.getName(), peopleInfo);

	}

	@Override
	public void update(PeopleInfo peopleInfo) {
		cacheManager.del(CommConstants.PLATFORM_RECEPTION, DataType.PEOPLE.getName(), FieldType.ID.getName(), peopleInfo.getId());
		iPeopleDao.update(peopleInfo);

	}
}
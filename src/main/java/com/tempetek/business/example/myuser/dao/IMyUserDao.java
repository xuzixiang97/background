package com.tempetek.business.example.myuser.dao;

import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicDao;

import java.util.List;

public interface IMyUserDao extends AbstractBasicDao<UserInfo, Long> {

    public Pagination<UserInfo> findByPager(Pagination<UserInfo> pager, UserInfo condition);

    public Pagination<UserInfo> findByPagerRole(Pagination<UserInfo> pager, UserInfo condition);

    public Pagination<UserInfo> findByPagerNotRole(Pagination<UserInfo> pager, UserInfo condition);

    public List<UserInfo> findByIds(Long[] ids);

    public void deleteByIds(Long[] ids);

    public void deleteByPersonIds(Long[] ids);

    public List<UserInfo> findByPersonId(Long personId);

    public UserInfo findByUserName(String username);

}
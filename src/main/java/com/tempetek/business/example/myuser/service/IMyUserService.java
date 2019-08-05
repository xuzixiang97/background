package com.tempetek.business.example.myuser.service;

import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.core.Pagination;
import com.tempetek.orm.AbstractBasicService;

import java.util.List;

/**
 * Created by xuzx on 2019/7/1.
 */
public interface IMyUserService extends AbstractBasicService<UserInfo, Long> {

    public Pagination<UserInfo> findByPager(Pagination<UserInfo> pager, UserInfo condition);

    public Pagination<UserInfo> findByPagerRole(Pagination<UserInfo> pager, UserInfo condition);

    public Pagination<UserInfo> findByPagerNotRole(Pagination<UserInfo> pager, UserInfo condition);

    public List<UserInfo> findByIds(Long[] ids);

    public void deleteByIds(Long[] ids);

    public UserInfo findByUserName(String username);

    public List<UserInfo> findByPersonId(Long id);

}
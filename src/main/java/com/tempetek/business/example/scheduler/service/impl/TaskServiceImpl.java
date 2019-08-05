package com.tempetek.business.example.scheduler.service.impl;

import com.tempetek.business.example.scheduler.service.ITaskService;
import com.tempetek.orm.spring.SpringContextHandler;
import com.tempetek.safety.manager.AuthenManager;
import com.tempetek.safety.manager.impl.AuthenWebManager;
import com.tempetek.scheduler.Job;

public class TaskServiceImpl implements ITaskService {
	
	private AuthenManager authenManager = SpringContextHandler.getBean(AuthenWebManager.class);

	@Override
	public void execute(Job job) {
		System.out.println("-------执行测试任务-------" + authenManager.getCurrentUser().getUserName());
	}
	
}

package com.sdudoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdudoc.bean.SysLog;
import com.sdudoc.dao.LogDao;
import com.sdudoc.service.LogService;
import com.sdudoc.utils.Pager;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Resource(name = "logDao")
	public LogDao logDao;

	@Override
	public void addLog(SysLog sysLog) {
		logDao.addLog(sysLog);
	}
	
	@Override
	public void deleteLogDaysAgo(int days) {
		logDao.deleteLogDaysAgo(days);
	}

	@Override
	public List<SysLog> listLogByUserId(int userId) {
		return logDao.listLogByUserId(userId);
	}

	@Override
	public Pager<SysLog> listLogByUserPage(int userId, int pageNo, int pageSize) {
		return logDao.listLogByUserPage(userId, pageNo, pageSize);
	}

	@Override
	public Pager<SysLog> listLogAllByPage(int pageNo, int pageSize) {
		return logDao.listLogAllByPage(pageNo, pageSize);
	}

	

}

package com.sdudoc.dao;

import java.util.List;

import com.sdudoc.bean.SysLog;
import com.sdudoc.utils.Pager;

public interface LogDao {

	public void addLog(SysLog sysLog);

	public void deleteLog(SysLog sysLog);
	
	public void deleteLogDaysAgo(int days);
	
	public List<SysLog> listLogByUserId(int userId);

	public Pager<SysLog> listLogByUserPage(int userId, int pageNo, int pageSize);
	
	public Pager<SysLog> listLogAllByPage(int pageNo, int pageSize);
}

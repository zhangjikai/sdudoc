package com.sdudoc.task;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdudoc.service.LogService;

/**
 * 定时任务
 * @author zhangjk
 *
 */
@Component
public class TaskManager {

	Logger log = LogManager.getLogger(TaskManager.class);

	@Resource(name = "logService")
	private LogService logService;

	/**
	 * 每天0点的时候，执行一遍日志删除工作，将30天之前的日志删除
	 */
	@Scheduled(cron = "0 0 0 * * ? ")
	public void deleteLogs() {
		if (log.isInfoEnabled()) {
			log.info("执行删除日志任务");
		}
		logService.deleteLogDaysAgo(30);
	}
}

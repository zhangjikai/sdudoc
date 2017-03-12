package com.sdudoc.task;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 使用Quartz执行定时任务，这里只是一个简单的示例
 * @author zhangjk
 *
 */
public class QuartzTask {

	Logger log = LogManager.getLogger(TaskManager.class);
	
	public void run() {
		log.info("QuartzTask开始");
	}
}

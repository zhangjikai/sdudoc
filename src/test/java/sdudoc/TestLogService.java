package sdudoc;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sdudoc.bean.SysLog;
import com.sdudoc.bean.User;
import com.sdudoc.service.LogService;

@SuppressWarnings("resource")
public class TestLogService extends TestCase{

	public void testGetLogByUserId(){
		
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		LogService logService = (LogService) ac.getBean("logService");
		List<SysLog> sysLogs = logService.listLogByUserId(0);
		for(SysLog sysLog : sysLogs) {
			System.out.println(sysLog);
		}
	}
	
	public void testInsertLog() throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		LogService logService = (LogService) ac.getBean("logService");
		User user = new User();
		user.setId(1);
		user.setUsername("zhangjk");
		user.setPassword("4QrcOUm6Wau+VuBX8g+IPg==");
		user.setEmail("995641419@qq.com");
		for (int i = 0; i < 50; i++) {
			SysLog sysLog = new SysLog();
			/*sysLog.setUserId(4);
			sysLog.setUserName("123456");*/
			sysLog.setUser(user);
			sysLog.setMethod("登录系统");
			sysLog.setTime(new Date());
			TimeUnit.MILLISECONDS.sleep(50);
			logService.addLog(sysLog);
		}
	}
	
	public void testDeleteLog(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		LogService logService = (LogService) ac.getBean("logService");
		logService.deleteLogDaysAgo(8);
	}
}

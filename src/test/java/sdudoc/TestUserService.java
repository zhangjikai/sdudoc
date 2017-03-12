package sdudoc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sdudoc.bean.User;
import com.sdudoc.service.UserService;
import com.sdudoc.utils.MailUtil;

@SuppressWarnings("resource")
public class TestUserService extends TestCase{
	
	
	public void testGetUserByEmail() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		UserService userDao = (UserService) ac.getBean("userService");
		System.out.println(userDao.getUserByEmail("995641419@qq.com"));
	}
	
	public void testAddUser() throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		for(int i = 0; i < 50; i++) {
			User user= new User();
			user.setUsername("test" + i);
			user.setPassword("123456");
			user.setEmail("me@zhangjikai.com");
			user.setCheckCode("g3zOn94YybMQh4oNdH6ptA==");
			user.setRegisterDate(new Date());
			user.setState(1);
			user.setGroup(1);
			TimeUnit.SECONDS.sleep(1);
			userService.addUser(user);
		}
	}
	
	public void testGenCheckCode() {
		String check = MailUtil.genCheckCode("aa");
		System.out.println(check);
	}
}

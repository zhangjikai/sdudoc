package sdudoc;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sdudoc.service.RecommendService;


@SuppressWarnings("resource")
public class TestRecommendService extends TestCase{
	
	public void testGetRecommend(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		RecommendService recommendService = (RecommendService) ac.getBean("recommendService");
		List<String> list = recommendService.getCollect(1);
		for(String s:list) {
			System.out.println(s);
		}
	}

}

package com.sdudoc.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.bean.Book;
import com.sdudoc.bean.Click;
import com.sdudoc.bean.SysLog;
import com.sdudoc.bean.User;
import com.sdudoc.service.ClickService;
import com.sdudoc.service.LogService;
import com.sdudoc.utils.Constants;

@Component
public class LogInterceptor implements Interceptor {

	private static final long serialVersionUID = 8837579269961323490L;

	Logger log = LogManager.getLogger(LogInterceptor.class);

	@Resource(name = "logService")
	private LogService logService;
	
	@Resource(name="clickService")
	private ClickService clickService;
	
	@Override
	public void init() {
		log.info("LogIntercepetor init");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log.info("LogIntercepetor intercept");
		String result = invocation.invoke();

		String methodName = invocation.getProxy().getMethod();
		if (StringUtils.isEmpty(methodName)) {
			return result;
		}
		// 排除set和get方法
		if (methodName.startsWith("set") || methodName.startsWith("get")) {
			return result;
		}

		Class<?> targetClass = invocation.getAction().getClass();
		Method method = targetClass.getMethod(methodName);
		if (method == null || !method.isAnnotationPresent(MethodDesc.class))
			return result;
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get(Constants.USER);
		if (user == null)
			return result;

		MethodDesc methodDesc = method.getAnnotation(MethodDesc.class);
		String desc = methodDesc.description();
		int opType = methodDesc.opType();
		if(opType == Constants.OP_CLICK) {
			Click click = new Click();
			Book book = (Book) session.get("book");
			if(book != null) {
				click.setUser(user);
				click.setBook(book);
				clickService.addClick(click);
				session.remove("book");
			}
		}

		/*Field[] fields = targetClass.getSuperclass().getDeclaredFields();
		for(Field field : fields) {
			System.out.println(field.getName());
		}*/
		Field field = targetClass.getSuperclass().getDeclaredField("logMessage");
		field.setAccessible(true);
		String logMessage = (String) field.get(invocation.getAction());
		System.out.println("logMessage:" + logMessage);

		/*Field field = targetClass.getDeclaredField("logMessage");
		field.setAccessible(true);
		String logMessage = (String) field.get(invocation.getAction());
		System.out.println("logMessage:" + logMessage);*/
		try {
			SysLog sysLog = new SysLog();
			/*sysLog.setUserId(user.getId());
			sysLog.setUserName(user.getUsername());*/
			sysLog.setUser(user);
			if (logMessage != null && !logMessage.equals(""))
				sysLog.setMethod(desc + ": " + logMessage);
			else
				sysLog.setMethod(desc);
			sysLog.setTime(new Date());
			logService.addLog(sysLog);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public void destroy() {
		log.info("LogIntercepetor init");
	}

}

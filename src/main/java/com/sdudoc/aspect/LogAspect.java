package com.sdudoc.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.bean.SysLog;
import com.sdudoc.bean.User;
import com.sdudoc.service.LogService;
import com.sdudoc.utils.Constants;

/**
 * 使用Spring AOP 拦截Action，记录用户操作日志
 * 但是由于其和Struts2的interceptor会发生冲突,这里就使用interceptor来记录日志
 * @author zhangjk
 *
 */
@Component("logAspect")
public class LogAspect {

	@Resource(name = "logService")
	private LogService logService;

	Logger log = LogManager.getLogger(LogAspect.class);

	public void doSysLogAfter(JoinPoint point) throws Throwable {
		log.info("doSysLogAfter");
		String methodName = point.getSignature().getName();

		// 目标方法不为空
		if (StringUtils.isEmpty(methodName)) {
			return;
		}
		// 排除set和get方法
		if (methodName.startsWith("set") || methodName.startsWith("get")) {
			return;
		}
		/*Object[] args = point.getArgs();
		Class<?>[] classes = new Class<?>[args.length];
		for(int i = 0; i < args.length; i++) {
			classes[i] = args[i].getClass();
		}
		Class<?> targetClass = point.getTarget().getClass();
		Method method = targetClass.getMethod(methodName, classes);*/
		Class<?> targetClass = point.getTarget().getClass();
		Method method = targetClass.getMethod(methodName);
		
		if (method == null) {
			return;
		}
		// 方法中是否含有MethodDesc注解，如果有则记录该操作
		boolean hasAnnotation = method.isAnnotationPresent(MethodDesc.class);
		
		if (hasAnnotation) {
			MethodDesc annotation = method.getAnnotation(MethodDesc.class);
			String methodDescp = annotation.description();
			
			if (log.isDebugEnabled()) {
				log.debug("Action method:" + method.getName() + " Description:" + methodDescp);
			}
			Map<String, Object> session = ActionContext.getContext().getSession();
			if (session == null) {
				return;
			}
			User user = (User) session.get(Constants.USER);
			if (user != null) {
				//获得类中的字段
				/*Field field = targetClass.getDeclaredField("user");
				field.setAccessible(true);
				User user_temp = (User) field.get(point.getTarget());
				System.out.println(user_temp);
				System.out.println(field);*/
				try {
					SysLog sysLog = new SysLog();
					/*sysLog.setUserId(user.getId());
					sysLog.setUserName(user.getUsername());*/
					sysLog.setUser(user);
					sysLog.setMethod(methodDescp);
					sysLog.setTime(new Date());
					logService.addLog(sysLog);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		return;
	}

	public Object doSysLogAround(ProceedingJoinPoint point) throws Throwable {
		log.info("doSysLogAround");
		String methodName = point.getSignature().getName();

		// 目标方法不为空
		if (StringUtils.isEmpty(methodName)) {
			return point.proceed();
		}

		// 排除set和get方法
		if (methodName.startsWith("set") || methodName.startsWith("get")) {
			return point.proceed();
		}
		Class<?> targetClass = point.getTarget().getClass();
		Method method = targetClass.getMethod(methodName);
		if (method == null) {
			return point.proceed();
		}
		// 方法中是否含有MethodDesc注解，如果有则记录该操作
		boolean hasAnnotation = method.isAnnotationPresent(MethodDesc.class);
		if (hasAnnotation) {
			MethodDesc annotation = method.getAnnotation(MethodDesc.class);
			String methodDescp = annotation.description();
			if (log.isDebugEnabled()) {
				log.debug("Action method:" + method.getName() + " Description:" + methodDescp);
			}
			Map<String, Object> session = ActionContext.getContext().getSession();
			if (session == null) {
				return point.proceed();
			}
			User user = (User) session.get(Constants.USER);
			if (user != null) {
				try {
					SysLog sysLog = new SysLog();
					/*sysLog.setUserId(user.getId());
					sysLog.setUserName(user.getUsername());*/
					sysLog.setUser(user);
					sysLog.setMethod(methodDescp);
					sysLog.setTime(new Date());
					logService.addLog(sysLog);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}

		}

		return point.proceed();
	}

}

package com.sdudoc.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sdudoc.action.BaseAction;
import com.sdudoc.annotation.Permission;
import com.sdudoc.bean.User;
import com.sdudoc.utils.Constants;

/**
 * 权限控制
 * @author zhangjk
 *
 */
public class AccessInterceptor implements Interceptor {

	private static final long serialVersionUID = 8571752631133473299L;

	Logger log = LogManager.getLogger(AccessInterceptor.class);

	@Override
	public void init() {
		log.info("AccessInterceptor init");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log.info("AccessInterceptor intercept");
		String methodName = invocation.getProxy().getMethod();
		Method method = invocation.getAction().getClass().getMethod(methodName);
		/*System.out.println("methodName: " + methodName);
		System.out.println("method: " + method);
		System.out.println(method.isAnnotationPresent(Permission.class));
		System.out.println(method.getAnnotations().length);
		for(Annotation annotation : method.getAnnotations()) {
			System.out.println(annotation.toString());
		}*/

		if (method == null || !method.isAnnotationPresent(Permission.class))
			return invocation.invoke();
		Permission permission = method.getAnnotation(Permission.class);
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get(Constants.USER);
		if (user == null)
			return BaseAction.LOGIN;
		if (permission.value() == Constants.USER_COMMON) {
			int group = user.getGroup();
			if (group == Constants.USER_COMMON || group == Constants.USER_ADMIN)
				return invocation.invoke();
			return Constants.NO_PERMISSION;
		}
		if (permission.value() == Constants.USER_ADMIN) {
			int group = user.getGroup();
			if (group == Constants.USER_ADMIN)
				return invocation.invoke();
			return Constants.NO_PERMISSION;
		}
		return invocation.invoke();
	}

	@Override
	public void destroy() {
		log.info("AccessInterceptor destory");
	}

}

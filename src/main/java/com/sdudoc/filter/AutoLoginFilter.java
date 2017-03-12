package com.sdudoc.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sdudoc.bean.User;
import com.sdudoc.service.UserService;
import com.sdudoc.utils.Constants;
import com.sdudoc.utils.CookieUtil;

/**
 * 自动登录
 * @author zhangjk
 *
 */
public class AutoLoginFilter implements Filter {

	Logger log = LogManager.getLogger(AutoLoginFilter.class);

	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("AutoLoginFilter Init");
		//使用以下方式来完成userService的注入
		ServletContext context = filterConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		userService = (UserService) ctx.getBean("userService");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		log.info("AutoLoginFilter doFilter");
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpSession session = request2.getSession();
		User user = (User) session.getAttribute(Constants.USER);
		if (user != null && user.getUsername() != null && user.getPassword() != null) {
			chain.doFilter(request, response);
			return;
		}
		
		String username = CookieUtil.getCookie(request2, Constants.COOKIE_USERNAME);
		String password = CookieUtil.getCookie(request2, Constants.COOKIE_PASSWORD);
		if(username != null && password != null) {
			user= userService.login(username, password);
			if(user != null) {
				session.setAttribute(Constants.USER, user);
				log.info("AutoLoginFilter: 自动登陆成功");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info("AutoLoginFilter destory");

	}
}

package com.sdudoc.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.bean.User;
import com.sdudoc.service.UserService;
import com.sdudoc.utils.Constants;
import com.sdudoc.utils.CookieUtil;
import com.sdudoc.utils.MD5;

@Controller
@ParentPackage("sdudoc")
@Namespace("/")
@Scope("prototype")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		@Result(name = BaseAction.SUCCESS, location = "/index.jsp"),
		@Result(name="redirect", type="redirectAction", location="book/showBookByClickTimes")
})
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1243114590691425282L;
	
	@Resource(name = "userService")
	private UserService userService;
	
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 验证 码*/
	private String random;
	/** 是否自动登录 */
	private boolean autoLogin;
	
	

	@Action("index")
	public String index() {
		/*url = "index.jsp";*/
		/*return DYNAMIC;*/
		return "redirect";
	}

	@Action("loginJsp")
	public String loginJsp() {
		url = "login.jsp";
		return DYNAMIC;
	}

	@MethodDesc(description="登录系统")
	@Action(value = "login")
	public String login() {
		url = "login.jsp";
		String random1 = (String) session.get(Constants.RANDOM);
		if (random1 == null || !random1.equals(random)) {
			addFieldError("random", "验证码错误");
			return DYNAMIC;
		}

		User userLogin = userService.login(username, MD5.md5_base64(password));
		if (userLogin == null) {
			addFieldError("username", "用户名或密码错误");
			return DYNAMIC;
		}

		if (userLogin.getState() == Constants.USER_NOACTICED) {
			url = "handleFailure.jsp?handleType=user_noactived";
			return DYNAMIC;
		}

		System.out.println("autologin: " + autoLogin);
		// 如果选中自动登录，想浏览器端写入cookie
		if (autoLogin) {
			CookieUtil cookieUtil = new CookieUtil();
			List<Cookie> cookies = cookieUtil.addCookie(userLogin);
			for (Cookie cookie : cookies) {
				response.addCookie(cookie);
			}
		}
		session.put(Constants.USER, userLogin);
		url = "handleSuccess.jsp?handleType=user_login";
		return DYNAMIC;
	}

	
	@Action("logout")
	public String logout() {
		if (session != null)
			session.remove(Constants.USER);
		removeCookie();
		/*url = "index.jsp";
		return DYNAMIC;*/
		return "redirect";
	}

	/** 移除cookie */
	private void removeCookie() {
		CookieUtil cookieUtil = new CookieUtil();
		Cookie username = cookieUtil.delCookie(request, Constants.COOKIE_USERNAME);
		Cookie password = cookieUtil.delCookie(request, Constants.COOKIE_PASSWORD);
		if (username != null)
			response.addCookie(username);
		if (password != null)
			response.addCookie(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

}

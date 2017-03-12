package com.sdudoc.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdudoc.bean.User;

public class CookieUtil {

	/**添加cookie*/
	public List<Cookie> addCookie(User user) {
		Cookie cookieU = new Cookie(Constants.COOKIE_USERNAME, user.getUsername());
		Cookie cookieP = new Cookie(Constants.COOKIE_PASSWORD, user.getPassword());
		cookieU.setMaxAge(60 * 60 * 24 * 14);
		cookieP.setMaxAge(60 * 60 * 24 * 14);
		cookieU.setPath("/");
		cookieP.setPath("/");
		List<Cookie> list = new ArrayList<Cookie>();
		list.add(cookieP);
		list.add(cookieU);
		return list;
	}

	/** 获得cookie */
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		// System.out.println("cookies: " + cookies);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// System.out.println("cookieName: " + cookie.getName());
				if (key.equals(cookie.getName())) {
					String value = cookie.getValue();
					// System.out.println("cookieValue: " + cookie.getValue());
					return value;
				}
			}
		}
		return null;
	}

	/** 删除cookie */
	public Cookie delCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return null;
	}

	/** 删除cookie */
	public Cookie delCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
	}
}

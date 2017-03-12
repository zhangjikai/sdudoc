package com.sdudoc.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware {

	private static final long serialVersionUID = -401077295472415733L;

	protected static final String DYNAMIC = "dynamic";

	/** 使用IOC的方式注入request,response,session    */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;

	/**记录用户操作日志操作时传递一些额外信息 */
	protected String logMessage = "";
	/** action执行完返回的动态url */
	protected String url;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/** 记录更多的关于用户操作的信息 */
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

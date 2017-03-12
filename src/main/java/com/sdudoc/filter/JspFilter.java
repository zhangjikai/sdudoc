package com.sdudoc.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JspFilter implements Filter {

	Logger log = LogManager.getLogger(JspFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("JspFilter Init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		log.info("JspFilter doFilter: " + request2.getRequestURL());
		HttpServletResponse response2 = (HttpServletResponse) response;
		response2.sendRedirect("index");
	}

	@Override
	public void destroy() {
		log.info("JspFilter destory");
	}

}
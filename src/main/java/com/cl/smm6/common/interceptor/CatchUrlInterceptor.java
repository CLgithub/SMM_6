package com.cl.smm6.common.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cl.smm6.system.service.SysRightsService;

/**
 * 捕获rul拦截器
 * 
 * @author L
 * @date 2015年12月2日
 */
public class CatchUrlInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String contextPath = request.getContextPath();
		String url = request.getServletPath().toString();
		String nurl = url.split(".action")[0].toString();

		ServletContext sc = request.getServletContext();
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(sc);
		SysRightsService rs = (SysRightsService) act.getBean("sysRightsServiceImpl");

		rs.appendRigheByURL(nurl, "", "", false);
		return true;
	}

}

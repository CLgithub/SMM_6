package com.cl.smm6.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cl.smm6.common.uitl.ValidateUtil;

/**
 * 自定义权限拦截器
 * 
 * @author L
 * @date 2015年11月25日
 */
public class RightInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 前置拦截器，判断是否有访问权限
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String contextPath = request.getContextPath();
		String url = request.getServletPath().toString();
		String nurl = url.split(".action")[0].toString();
		if ("/loginController/doLogin".equals(nurl) || "/loginController/doLogout".equals(nurl)) {
			return true;
		} else if (ValidateUtil.hasRight(nurl, request)) {
			return true;
		} else {
			response.sendRedirect(contextPath);
			return false;
		}
	}

}

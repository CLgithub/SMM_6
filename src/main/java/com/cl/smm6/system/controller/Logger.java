package com.cl.smm6.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cl.smm6.common.entity.SysLog;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.uitl.DataUtil;
import com.cl.smm6.system.service.SysLogService;

/**
 * 日志记录仪
 * 
 * @author L
 * @date 2015年11月27日
 */
public class Logger {

	private SysLogService sysLogService;

	public void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	/**
	 * 记录日志
	 * @param pjp 连接点
	 * @return
	 */
	public Object record(ProceedingJoinPoint pjp) {
		SysLog log = new SysLog();
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
			if (sysUser != null) {
				log.setOperator(sysUser.toString());// 设置操作人
				String methodName = pjp.getSignature().getName();
				log.setOpername(methodName);// 设置操作方法名
				Object[] args = pjp.getArgs();
				log.setOperparams(DataUtil.arrToStr(args));// 方法参数列表
				Object ret = pjp.proceed();
				log.setOperresult("success");// 设置结果
				if (ret != null) {
					log.setResultmsg(ret.toString());// 结果消息
				}
				return ret;
			}
		} catch (Throwable e) {
			log.setOperresult("failure");
			log.setResultmsg(e.getMessage());
		} finally {
			sysLogService.insert0(log);
		}
		return null;
	}

}

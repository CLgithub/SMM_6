package com.cl.smm6.common.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.system.service.SysRightsService;

/**
 * 权限初始化监听器
 * @author L
 * @date 2015年12月12日
 */
@Component
public class IniRightListener implements ApplicationListener, ServletContextAware {

	@Resource
	private SysRightsService sysRightsService;

	// 接收servletContext对象
	private ServletContext servletContext;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// 是否是上下文刷新事件
		if (event instanceof ContextRefreshedEvent) {
			List<SysRights> list = sysRightsService.getAlllist();
			Map<String, SysRights> map = new HashMap<String, SysRights>();
			for (SysRights rights : list) {
				map.put(rights.getRighturl(), rights);
			}
			if (servletContext != null) {
				servletContext.setAttribute("all_rights_map", map);
				System.out.println("权限初始化完成!");
			}
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}

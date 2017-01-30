package com.cl.smm6.common.listener;

import javax.annotation.Resource;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cl.smm6.common.uitl.LogUtil;
import com.cl.smm6.system.service.SysLogService;

/**
 * 初始化日志表监听器
 * 
 * @author L
 * @date 2015年12月15日
 */
@Component
public class IniLogTablesListener implements ApplicationListener {

	@Resource
	private SysLogService sysLogService;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// 是否是上下文刷新事件
		if (event instanceof ContextRefreshedEvent) {
			sysLogService.createLogTab(LogUtil.generateLogTableName(0) );
			sysLogService.createLogTab(LogUtil.generateLogTableName(1) );
			sysLogService.createLogTab(LogUtil.generateLogTableName(2) );
			System.out.println("日志表初始化完成!!!");
		}
	}

}

package com.cl.smm6.common.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cl.smm6.common.uitl.LogUtil;
import com.cl.smm6.system.service.SysLogService;

/**
 * 使用spring集成的石英调度,动态生成日志表
 * 
 * @author L
 * @date 2015年12月15日
 */
public class GenerateLogTableTak extends QuartzJobBean {

	private SysLogService sysLogService;

	public void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	/**
	 * 执行任务
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		String tableName = LogUtil.generateLogTableName(1);
		String sql = "create table if not exists " + tableName + " like sys_log";
		sysLogService.executeSql(sql);
		System.out.println(tableName + " 生成了! ");

		tableName = LogUtil.generateLogTableName(2);
		sql = "create table if not exists " + tableName + " like sys_log";
		sysLogService.executeSql(sql);
		System.out.println(tableName + " 生成了! ");
	}

}

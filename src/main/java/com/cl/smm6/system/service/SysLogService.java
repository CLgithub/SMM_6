package com.cl.smm6.system.service;

import com.cl.smm6.common.entity.SysLog;
import com.cl.smm6.common.servicebase.BaseService;
import com.cl.smm6.common.uitl.PageBean;

public interface SysLogService extends BaseService<SysLog> {

	/**
	 * 将日志插入到当月日志表
	 * 
	 * @author L
	 * @date 2015年12月1日
	 * @param t
	 */
	public void insert0(SysLog t);

	/**
	 * 根据条件查询日志，默认查询当月的
	 * 
	 * @author L ＊ @date 2016年1月7日
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param startTime 开始时间
	 * @param endTime 截至时间
	 * @param searchOperator 操作人
	 * @param searchOpername 操作方法
	 * @param operresult 操作结果
	 * @return
	 */
	PageBean getLogsPBBySearch(Integer page, Integer rows, String startTime, String endTime, String searchOperator,
			String searchOpername, String operresult);

}

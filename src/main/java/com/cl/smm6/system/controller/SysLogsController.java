package com.cl.smm6.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.smm6.system.service.SysLogService;

@Controller
@RequestMapping(value = "sysLogsController")
public class SysLogsController {

	@Resource
	private SysLogService sysLogService;

	/**
	 * 根据条件查询日志，默认查询近两个月的
	 * 
	 * @author L
	 * @date 2016年1月6日
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param SearchOperator 操作人
	 * @param SearchOpername 操作方法
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param operresult 操作结果
	 * @return
	 */
	@RequestMapping(value = "getPBBySearch")
	@ResponseBody
	public Object getPBBySearch(Integer page, Integer rows, String SearchOperator, String SearchOpername,
			String startTime, String endTime, String operresult) {
		return sysLogService.getLogsPBBySearch(page, rows );
	}

}

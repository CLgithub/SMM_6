package com.cl.smm6.system.controller;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用户控制类
 * @author L
 * @date 2016年1月6日
 */
@Controller
@RequestMapping(value = "sysUserController")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 根据条件，得到当前用户部门及其子部门用户列表
	 * @author L
	 * @date 2015年12月28日
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param searchWhere 条件
	 * @return
	 */
	@RequestMapping(value = "getPBBySearch")
	@ResponseBody
	public Object getPBBySearch(HttpSession session, Integer page, Integer rows, String searchWhere) {
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		return sysUserService.getUserPBBySearch(sysUser, page, rows, searchWhere);
	}

	/**
	 * 新增或修改用户,id为null则新增，否则为修改
	 * @author L
	 * @date 2016年1月2日
	 * @param sysUser
	 * @return true:成功,false:失败,失败原因
	 */
	@RequestMapping(value = "saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(SysUser sysUser,Integer depcode) {
		try {
			return sysUserService.saveOrUpdateUser(sysUser);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 根据ids删除用户
	 * @author L
	 * @date 2015年12月28日
	 * @param ids 要删除的id字符串,用“,”隔开
	 * @return true:成功,false:失败,其他即删除异常
	 */
	@RequestMapping(value = "deleteByIDs")
	@ResponseBody
	public Object deleteByIDs(String ids) {
		try {
			return sysUserService.deleteUserByIDs(ids);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}

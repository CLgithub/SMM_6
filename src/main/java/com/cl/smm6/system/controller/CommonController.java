package com.cl.smm6.system.controller;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysDepartmentService;
import com.cl.smm6.system.service.SysMenuService;
import com.cl.smm6.system.service.SysRightsService;
import com.cl.smm6.system.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "commonController")
public class CommonController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRightsService sysRightsService;
	@Resource
	private SysDepartmentService sysDepartmentService;
	@Resource
	private SysMenuService sysMenuService;
	

	/**
	 * 加载用户目录
	 * @author L
	 * @date 2015年12月10日
	 * @return 用户菜单pageBean
	 */
	@RequestMapping(value = "loadUserMenu")
	@ResponseBody
	public Object loadUserMenu(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		return sysMenuService.loadUserMenu(sysUser);
	}
	
	/**
	 * 根据用户id得到该用户的权限ListMap,如果uid为空，则得到当前登录者id
	 * @author L
	 * @date 2016年1月21日
	 * @param uid 用户id
	 * @return 权限idPageBean
	 */
	@RequestMapping(value = "getRightByUser")
	@ResponseBody
	public Object getRightByUser(HttpServletRequest request, Integer uid) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		uid=uid==null?sysUser.getId():uid;
		return sysRightsService.getRightByUser(uid);
	}
	
	/**
	 * 得到当前登录用户所在部门及其子部门列表
	 * @author L
	 * @date 2016年1月27日
	 * @return
	 */
	@RequestMapping(value = "getUserDepList")
	@ResponseBody
	public Object getUserDepList(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		return sysDepartmentService.getUserDepList(sysUser);
	}
	
	
	/**
	 * 得到用户菜单下拉框
	 * @author L
	 * @date 2016年1月19日
	 * @return
	 */
	@RequestMapping(value = "getUserMenu")
	@ResponseBody
	public Object getUserMenu(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		return sysMenuService.getUserMenu(sysUser);
	}
	

	
}

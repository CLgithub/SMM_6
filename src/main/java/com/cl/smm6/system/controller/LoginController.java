package com.cl.smm6.system.controller;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysMenuService;
import com.cl.smm6.system.service.SysRightsService;
import com.cl.smm6.system.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "loginController")
public class LoginController {

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysRightsService sysRightsService;

	@Resource
	private SysMenuService sysMenuService;

	/**
	 * 用户登录
	 * @author L
	 * @date 2015-11-20
	 * @return true：登录成功，false：登录失败
	 */
	@RequestMapping(value = "doLogin")
	@ResponseBody
	public Object doLogin(String userName,String password,HttpSession session) {
		SysUser sysUser = sysUserService.doLogin(userName, password);
		if (sysUser == null) {
			return false;
		} else {
			session.setAttribute("sysUser", sysUser);
			return true;
		}
	}


	/**
	 * 用户注销
	 * @author L
	 * @date 2015年11月27日
	 * @return true：注销成功，false：注销失败
	 */
	@RequestMapping(value = "doLogout")
	@ResponseBody
	public Object doLogout(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("sysUser", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

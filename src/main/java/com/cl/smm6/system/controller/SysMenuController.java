package com.cl.smm6.system.controller;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.smm6.common.entity.SysMenu;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysMenuService;

/**
 * 菜单控制器
 * @author L
 * @date 2015年12月12日
 */
@Controller
@RequestMapping(value = "sysMenuController")
public class SysMenuController {

	@Resource
	private SysMenuService sysMenuService;

	/**
	 * 根据条件，查询菜单
	 * @author L
	 * @date 2015年12月12日
	 * @param request
	 * @return 菜单pageBean
	 */
	@RequestMapping(value = "getPBBySearch")
	@ResponseBody
	public Object getPBBySearch(String searchWhere) {
		return sysMenuService.getMenuPBBySearch(searchWhere);
	}
	
	/**
	 * 新增或修改菜单
	 * @author L
	 * @date 2015年12月11日
	 * @return true：成功，false：失败
	 */
	@RequestMapping(value = "saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(SysMenu sysMenu) {
		return sysMenuService.saveOrUpdateMenu(sysMenu);

	}


	/**
	 * 根据id删除菜单
	 * @author L
	 * @date 2015年12月12日
	 * @return true：成功 false：失败
	 */
	@RequestMapping(value = "deleteByID")
	@ResponseBody
	public Object deleteByID(Integer id) {
		try {
			return sysMenuService.deleteMenuByIDs(id);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	

	

}

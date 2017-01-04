package com.cl.smm6.system.service;

import com.cl.smm6.common.entity.SysMenu;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.servicebase.BaseService;
import com.cl.smm6.common.uitl.PageBean;

import java.util.HashMap;
import java.util.List;

public interface SysMenuService extends BaseService<SysMenu> {

	/**
	 * 根据用户，加载其菜单
	 * 
	 * @author L
	 * @date 2015年12月2日
	 * @param sysUser
	 * @return 用户菜单pageBean
	 */
	List<HashMap<String, Object>> loadUserMenu(SysUser sysUser);

	/**
	 * 新增或修改菜单,id=0则为新增
	 * 
	 * @author L
	 * @date 2015年12月14日
	 * @param sysMenu
	 * @return true：成功，false：失败
	 */
	Boolean saveOrUpdateMenu(SysMenu sysMenu);

	/**
	 * 根据条件，查询菜单
	 * @author L
	 * @param searchWhere 查询条件
	 * @date 2015年12月12日
	 * @return 菜单pageBean
	 */
	PageBean getMenuPBBySearch(String searchWhere);

	/**
	 * 得到用户菜单下拉框
	 * @author L
	 * @date 2016年2月10日
	 * @param sysUser 当前用户
	 * @return
	 */
	List<HashMap<String, Object>> getUserMenu(SysUser sysUser);

	/**
	 * 根据id删除菜单
	 * @author L
	 * @date 2016年2月16日
	 * @param id
	 * @return true：成功，false：失败，及其失败原因
	 */
	boolean deleteMenuByIDs(Integer id);
	



}

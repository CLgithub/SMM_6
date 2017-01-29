package com.cl.smm6.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysRightsService;

/**
 * 权限管理操作类
 * @author L
 * @date 2016年1月1日
 */
@Controller
@RequestMapping(value = "sysRightsController")
public class SysRightsController {

	@Resource
	private SysRightsService sysRightsService;

	/**
	 * 根据条件得到该用户的权限列表
	 * @author L
	 * @date 2016年1月1日
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param searchWhere 条件
	 * @return
	 */
	@RequestMapping(value = "getPBBySearch")
	@ResponseBody
	public Object getPBBySearch(HttpSession session,Integer page, Integer rows, String searchWhere) {
	    SysUser sysUser= (SysUser) session.getAttribute("sysUser"	);
		return sysRightsService.getRightPBBySearch(page, rows, searchWhere);
	}

	/**
	 * 新增或修改权限，id为null则为新增，否则为修改
	 * @author L
	 * @date 2016年1月20日
	 * @param sysRights
	 * @param smid 该权限所属菜单ID
	 * @return true:成功,false:失败,失败原因
	 */
	@RequestMapping(value = "saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(SysRights sysRights, Integer smid) {
		try {
			return sysRightsService.saveOrUpdateRights(sysRights, smid);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 根据ids删除权限
	 * @author L
	 * @date 2015年12月28日
	 * @param ids 要删除的id字符串,用“,”隔开
	 * @return true:成功,false:失败,其他即删除异常
	 */
	@RequestMapping(value = "deleteByIDs")
	@ResponseBody
	public Object deleteByIDs(String ids) {
		try {
			return sysRightsService.deleteRightsByIDs(ids);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 批量设置用户权限
	 * @author L
	 * @date 2016年1月21日
	 * @param uids 用户ids，用“,”隔开
	 * @param rids 权限ids，用“,”隔开
	 * @return @return true:成功，false:失败、失败原因
	 */
	@RequestMapping(value = "setRights")
	@ResponseBody
	public Object setRights(String uids, String rids) {
		return sysRightsService.setRights("uids:" + uids, "rids:" + rids);
	}

	
}

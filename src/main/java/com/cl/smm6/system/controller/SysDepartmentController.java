package com.cl.smm6.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.smm6.common.entity.SysDepartment;
import com.cl.smm6.common.entity.SysMenu;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysDepartmentService;

/**
 * 部门控制器
 * @author L
 * @date 2016年1月26日
 */
@Controller
@RequestMapping(value = "sysDepartmentController")
public class SysDepartmentController {
	
	@Resource
	private SysDepartmentService sysDepartmentService;
	
	/**
	 * 根据条件查询部门
	 * @author L
	 * @date 2016年1月26日
	 * @return
	 */
	@RequestMapping(value="getPBBySearch")
	@ResponseBody
	public Object getPBBySearch(String searchWhere){
		return sysDepartmentService.getDepPBBySearch(searchWhere);
	}
	
	/**
	 * 新增或修改部门
	 * @author L
	 * @date 2016年1月27日
	 * @param sysMenu
	 * @return true:成功,false:失败,失败原因
	 */
	@RequestMapping(value = "saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(SysDepartment sysDepartment) {
		try{
			return sysDepartmentService.saveOrUpdateDep(sysDepartment);
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	/**
	 * 根据部门code删除部门，删除后将其子部门移动到与总部平级，等待处理
	 * @author L
	 * @date 2016年1月29日
	 * @param depcode
	 * @return true:成功,false:失败,失败原因
	 */
	@RequestMapping(value = "deleteByCode")
	@ResponseBody
	public Object deleteByCode(Integer depcode){
		try{
			return sysDepartmentService.deleteDepByCode(depcode);
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	
	
}

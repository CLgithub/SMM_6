package com.cl.smm6.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cl.smm6.common.entity.SysDepartment;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.servicebase.BaseService;
import com.cl.smm6.common.uitl.PageBean;

public interface SysDepartmentService extends BaseService<SysDepartment> {

	/**
	 * 根据条件，查询部门
	 * @author L
	 * @date 2016年1月26日
	 * @param searchWhere
	 * @return 部门pageBean
	 */
	PageBean getDepPBBySearch(String searchWhere);


	/**
	 * 新增或修改部门
	 * @author L
	 * @date 2016年1月27日
	 * @param sysDepartment 部门
	 * @return true:成功,false:失败,失败原因
	 */
	Object saveOrUpdateDep(SysDepartment sysDepartment);
	
	/**
	 * 根据部门父编号，计算部门编号
	 * @author L
	 * @date 2016年1月27日
	 * @param parentcode 部门父部门编号
	 * @return 部门编号
	 */
	Integer getDepCode(Integer parentcode);

	/**
	 * 根据部门code删除部门，删除后将其子部门移动到与总部平级，等待处理
	 * @author L
	 * @date 2016年1月29日
	 * @param depcode
	 * @return true:成功,false:失败,失败原因
	 */
	Object deleteDepByCode(Integer depcode);

	/**
	 * 得到当前登录用户所在部门及其子部门列表
	 * @author L
	 * @date 2016年2月5日
	 * @param sysUser 当前用户
	 * @return 
	 */
	List<HashMap<String, Object>> getUserDepList(SysUser sysUser);

}

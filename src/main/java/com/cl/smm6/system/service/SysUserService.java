package com.cl.smm6.system.service;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.servicebase.BaseService;
import com.cl.smm6.common.uitl.PageBean;
import javax.jws.WebService;

@WebService
public interface SysUserService extends BaseService<SysUser> {

	/**
	 * 根据条件，得到当前用户部门及其子部门用户列表
	 * @author L
	 * @param sysUser 当前登录者
	 * @date 2015年11月17日
	 * @param page 第几页
	 * @param pageSize 每页多少行
	 * @param searchWhere 模糊搜索条件
	 * @return
	 */
	public PageBean getUserPBBySearch(SysUser sysUser, Integer page, Integer pageSize, String searchWhere);

	/**
	 * 用户登录，查询是否有该用户,如果有,则计算其权限总和，并返回该用户，如果没有，返回null
	 * 
	 * @author L
	 * @date 2015-11-20
	 * @param loginName 登录名
	 * @param password 密码
	 * @return
	 */
	public SysUser doLogin(String loginName, String password);

	/**
	 * 新增或修改用户,id=0为新增,否则为修改
	 * @author L
	 * @date 2016年1月2日
	 * @param user
	 * @return true:成功,false:失败
	 */
	public boolean saveOrUpdateUser(SysUser user);

	/**
	 * 根据ids删除用户
	 * 
	 * @author L
	 * @date 2015年12月28日
	 * @param idTxts idTxts 要删除的id字符串,用“,”隔开
	 * @return true:成功,false:失败,其他即删除异常
	 */
	public boolean deleteUserByIDs(String idTxts);
	
	public void test();

}

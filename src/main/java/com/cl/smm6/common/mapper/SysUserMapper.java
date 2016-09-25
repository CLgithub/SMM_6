package com.cl.smm6.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapperbase.BaseMapper;

public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 根据用户名和密码查询用户list
	 * @param loginName
	 * @param password
	 * @return
	 */
	List<SysUser> selectUListByLogin(@Param("loginName") String loginName, @Param("password") String password);

	/**
	 * 根据用户id，查询其权限
	 * @param id
	 * @return
	 */
	List<SysRights> getRightByUserID(@Param("id") Integer id);

}
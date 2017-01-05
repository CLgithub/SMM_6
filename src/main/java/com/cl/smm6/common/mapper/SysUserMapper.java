package com.cl.smm6.common.mapper;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapperbase.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


	/**
	 * 根据ids，删除用户
	 * @param ids
	 * @return
	 */
    int deleteUserByIDs(@Param("ids") String[] ids);
}
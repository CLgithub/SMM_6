package com.cl.smm6.common.mapper;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.mapperbase.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRightsMapper extends BaseMapper<SysRights> {

	/**
	 * 得到最大权限位
	 * @return
	 */
	int getMaxRigthPos();

	/**
	 * 根据用户id得到其拥有的权限
	 * @param uid
	 * @return
	 */
    List<Map<String,Object>> getRightByUser(@Param("uid") Integer uid);

	/**
	 * 根据用户id删除用户的权限
	 * @param ids 用户id数组
	 */
	void delUserRight(@Param("ids") String[] ids);

	/**
	 * 给这些用户添加这些权限
	 * @param uids 用户id数组
	 * @param rids 权限id数组
	 */
	void addUserRight(@Param("uids") String[] uids, @Param("rids") String[] rids);


}
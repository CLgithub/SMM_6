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
}
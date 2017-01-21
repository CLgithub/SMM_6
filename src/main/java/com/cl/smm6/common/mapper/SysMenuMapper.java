package com.cl.smm6.common.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cl.smm6.common.entity.SysMenu;
import com.cl.smm6.common.mapperbase.BaseMapper;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 根据用户id得到其菜单id
	 * @param userId
	 * @return
	 */
	List<Integer> getMidByUid(@Param("userId")Integer userId);

	/**
	 * 更加用户id，加载用户菜单
	 * @param userId
	 * @return
	 */
	List<HashMap<String, Object>> loadUserMenu(@Param("userId")Integer userId);


	/**
	 * 得到所有菜单
	 * @return
	 */
	List<Map<String,Object>> getAllListMap();
}
package com.cl.smm6.common.mapper;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.mapperbase.BaseMapper;

public interface SysRightsMapper extends BaseMapper<SysRights> {

	/**
	 * 得到最大权限位
	 * @return
	 */
	int getMaxRigthPos();

}
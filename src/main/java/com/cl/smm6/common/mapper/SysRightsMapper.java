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
	int getMaxRightPos();

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

	/**
	 * 根据rul得到权限
	 * @param righturl 权限url
	 * @return 权限对象
	 */
	SysRights getRightByUrl(@Param("righturl") String righturl);

	/**
	 * 删除权限的权限所属菜单关系
	 * @param rids 权限ids数组
	 */
	void delMenuRight(@Param("rids") String[] rids);

	/**
	 * 设置权限所属菜单
	 * @param rid 权限id
	 * @param smid 菜单id
	 */
	void setMenuRgithu(@Param("rid") Integer rid, @Param("smid") Integer smid);

	/**
	 * 根据ids数组，删除对应id的权限
	 * @param rids
	 */
	void deleteRightsByIDs(@Param("rids") String[] rids);

	/**
	 * 查询所以权限
	 * @return
	 */
	List<SysRights> getAllList();

	/**
	 * 得到最大权限位及最大权限位上的权限码
	 * @return
	 */
	List<Map<String,Object>> getMaxRPosAndMaxRCode();
}
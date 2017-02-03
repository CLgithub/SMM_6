package com.cl.smm6.system.service;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.servicebase.BaseService;
import com.cl.smm6.common.uitl.PageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SysRightsService extends BaseService<SysRights> {

	/**
	 * 得到所有权限list
	 * @author L
	 * @date 2015年11月26日
	 * @return
	 */
	List<SysRights> getAlllist();

	/**
	 * 查询最大权限位
	 * @author L
	 * @date 2015年11月26日
	 * @return
	 */
	int getMaxRightPos();

	/**
	 * 按照url追加权限
	 * @author L
	 * @date 2016年1月2日
	 * @param url url
	 * @param rightname 权限名称
	 * @param rightdesc 权限描述
	 * @param common 是否是公共权限
	 * @return true:成功,false:失败
	 */
	boolean appendRigheByURL(String url, String rightname, String rightdesc, Boolean common);

	/**
	 * 根据条件，得到权限列表
	 * 
	 * @author L
	 * @date 2016年1月1日
	 * @param page 第几页
	 * @param rows 每页多少条
	 * @param searchWhere  条件
	 * @return
	 */
	PageBean getRightPBBySearch(Integer page, Integer rows, String searchWhere);

	/**
	 * 新增或修改权限，id为null则为新增，否则为修改
	 * @author L
	 * @date 2016年1月10日
	 * @param sysRights
	 * @param smid 该权限所属菜单id
	 * @return true:成功,false:失败
	 */
	boolean saveOrUpdateRights(SysRights sysRights, Integer smid);

	/**
	 * 根据ids删除权限
	 * @author L
	 * @date 2016年1月2日
	 * @param idTxts idTxts 要删除的id字符串,用“,”隔开
	 * @return true:成功,false:失败,其他即删除异常
	 */
	boolean deleteRightsByIDs(String idTxts);

	/**
	 * 批量设置用户权限
	 * @author L
	 * @date 2016年1月21日
	 * @param uids 用户ids，用“,”隔开(为了方便查看日志，在其前面加有“uids：”字符串)
	 * @param rids 权限ids，用“,”隔开(为了方便查看日志，在其前面加有“rids：”字符串)
	 * @return @return true:成功，false:失败
	 */
	boolean setRights(String uids, String rids);

	/**
	 * 根据用户id得到该用户的权限ListMap
	 * @author L
	 * @date 2016年1月21日
	 * @param uid 用户id
	 * @return 权限listMap
	 */
	List<Map<String, Object>> getRightByUser(Integer uid);

	int getnum();
}

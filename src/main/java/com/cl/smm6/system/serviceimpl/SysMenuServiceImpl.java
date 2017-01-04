package com.cl.smm6.system.serviceimpl;

import com.cl.smm6.common.entity.SysMenu;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapper.SysMenuMapper;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.Constant;
import com.cl.smm6.common.uitl.DataUtil;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

	@Resource(name = "sysMenuMapper")
	public void setBaseMapper(BaseMapper<SysMenu> baseMapper) {
		super.setBaseMapper(baseMapper);
	}
	
	@Resource
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<HashMap<String, Object>> loadUserMenu(SysUser sysUser) {
		return sysMenuMapper.loadUserMenu(sysUser.getId());
	}

	@Override
	public List<HashMap<String, Object>> getUserMenu(SysUser sysUser) {
		String sql1 = "SELECT sm.id AS smid FROM " + " sys_menu sm JOIN sys_menu_right smr JOIN sys_user_right sur "
				+ " ON sm.id=smr.menuID and smr.rightID=sur.rightID WHERE sur.userID=?";
		List<HashMap<String, Object>> menuIDList = this.selectListMapBySql(sql1, sysUser.getId());
		String mids = "'";
		for (Map<String, Object> map : menuIDList) {
			int mid = (int) map.get("smid");
			mids += mid + "','";
		}
		if (mids.length() > 1) {
			mids = mids.substring(0, mids.length() - 2);
		} else {
			mids = null;
		}
		String sql2 = "SELECT id as id, menuName as text, menuUrl as menuurl, "
				+ "parentID as _parentId, status as status FROM sys_menu ";
		if(!sysUser.getLoginname().equals("admin")){
			sql2+= " WHERE id IN(" + mids + ") "
					+ "OR id IN(SELECT DISTINCT parentID FROM sys_menu WHERE id IN(" + mids + "))";
		}
		return DataUtil.getChildJson(this.selectListMapBySql(sql2));
	}
	
	@Override
	public Boolean saveOrUpdateMenu(SysMenu sysMenu) {
		if (null != sysMenu.getId()) {// 如果是修改
			if (this.updateByPrimaryKey(sysMenu) == 1) {
				return true;
			} else {
				return false;
			}
		} else {// 如果是新增
			if (this.insert(sysMenu) == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	@Override
	public PageBean getMenuPBBySearch(String searchWhere) {
		String sql = "SELECT id as id, menuName as menuname, menuUrl as menuurl, "
				+ "parentID as _parentId, status as status FROM sys_menu where 1=1";
		if (null!=searchWhere ) {
			sql += " and menuname like '%" + searchWhere + "%' or menuUrl like '%" + searchWhere + "%' ";
		}
//		List<Map<String,Object>> list=sysMenuMapper.getAllListMap();
		return this.getPageBean(Constant.PAGEBEANTYPE_MAP, sql, 1, 9999);
	}

	@Override
	public boolean deleteMenuByIDs(Integer id) {
		if (this.deleteByPrimaryKey(id) == 1) {
			return true;
		} else {
			return false;
		}
	}

}

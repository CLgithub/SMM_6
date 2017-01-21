package com.cl.smm6.system.serviceimpl;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.mapper.SysRightsMapper;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.Constant;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysRightsService;
import com.cl.smm6.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRightsServiceImpl extends BaseServiceImpl<SysRights> implements SysRightsService {

	@Resource(name = "sysRightsMapper")
	public void setBaseMapper(BaseMapper<SysRights> baseMapper) {
		super.setBaseMapper(baseMapper);
	}
	
	@Resource
	private SysRightsMapper sysRightsMapper;
	
	@Resource
	private SysUserService sysUserService;

	@Override
	public List<SysRights> getAlllist() {
		return this.selectListTBySql("SELECT * from sys_rights");
	}

	@Override
	public int getMaxRightPos() {
		return sysRightsMapper.getMaxRigthPos();
	}

	@Override
	public boolean appendRigheByURL(String url, String rightname, String rightdesc, Boolean common) {
		String sql1 = "SELECT COUNT(1) as count FROM sys_rights WHERE righturl=?";
		List<HashMap<String, Object>> list = this.selectListMapBySql(sql1, url);
		long count = (long) (list.get(0).get("count"));
		if (count == 0) {
			SysRights sysRights = new SysRights();
			sysRights.setRighturl(url);
			// 处理权限位和权限码
		int rightPos = 0;
			long rightCode = 1;
			// 查询最大权限位上的最大权限码
			String sql2 = "SELECT MAX(r.rightpos) as maxPos, MAX(r.rightcode)as maxCode from sys_rights r WHERE r.rightpos=(SELECT MAX(rr.rightpos) FROM sys_rights rr)";
			List<HashMap<String, Object>> list2 = this.selectListMapBySql(sql2);
			Integer topRightPos = (Integer) list2.get(0).get("maxPos");
			Long topRightCode = (Long) list2.get(0).get("maxCode");
			if (topRightPos == null) {// 如果当前权限表为空
				rightPos = 0;
				rightCode = 1;
			} else {
				if (topRightCode >= 1L << 60) {// 如果最大权限码>=1L左移60，就在最大权限位上加1，权限码从1开始
					rightPos = topRightPos + 1;
					rightCode = 1;
				} else {// 否则就最大权限位不变，权限码左移1
					rightPos = topRightPos;
					rightCode = topRightCode << 1;
				}
			}
			sysRights.setRightpos(rightPos);
			sysRights.setRightcode(rightCode);
			sysRights.setRightname(rightname);
			sysRights.setRightdesc(rightdesc);
			sysRights.setCommon(common);
			// 处理权限位和权限码完成
			if (this.insert(sysRights) == 1)
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public PageBean getRightPBBySearch(Integer page, Integer rows, String searchWhere) {
		String sql = "SELECT * from sys_rights sr left join(SELECT smr.rightID as rightID, sm.id as smid,sm.menuName as menuName "
				+ " FROM sys_menu_right smr left JOIN sys_menu sm on smr.menuID=sm.id) a on sr.id=a.rightID WHERE 1=1 "
				+ "and (righturl LIKE '%"
				+ searchWhere + "%' OR rightname LIKE '%" + searchWhere + "%' OR rightdesc LIKE '%" + searchWhere
				+ "%' OR common LIKE '%" + searchWhere + "%') ORDER BY id ";
//		System.out.println(sql);
		return this.getPageBean(Constant.PAGEBEANTYPE_MAP, sql, page, rows);
	}

	@Override
	public boolean saveOrUpdateRights(SysRights sysRights, Integer smid) {
		Integer rid = null;
		// 如果id为空则去新增，新增成功这返回刚刚新增的ID
		if (null == sysRights.getId() && this.appendRigheByURL(sysRights.getRighturl(), sysRights.getRightname(),
				sysRights.getRightdesc(), sysRights.getCommon())) {
			// 如果新增成功，这查询其id，设置其所属页面
			String sql1 = "SELECT id from sys_rights WHERE righturl = ?";
			List<HashMap<String, Object>> list = this.selectListMapBySql(sql1, sysRights.getRighturl());
			rid = (Integer) list.get(0).get("id");
		} else if (this.updateByPrimaryKeySelective(sysRights) == 1) {// 不是新增就尝试去修改，并且修改成功设置rid
			rid = sysRights.getId();
		}
		if (null != rid) {
			String sql2 = "delete from sys_menu_right where rightID=?";
			this.executeSql(sql2, rid);
			if (null != smid) {
				String sql3 = "INSERT INTO sys_menu_right(menuID,rightID) VALUES(?,?)";
				this.executeSql(sql3, smid, rid);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRightsByIDs(String idTxts) {
		idTxts = "'" + idTxts + "'";
		idTxts = idTxts.replaceAll(",", "','");
		String sql = "DELETE from sys_rights WHERE id in(" + idTxts + ")";
		if (this.executeSql(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean setRights(String uids, String rids) {
		uids = uids.split("uids:")[1];
		rids = rids.split("rids:").length > 1 ? rids.split("rids:")[1] : "";
		sysRightsMapper.delUserRight(uids.split(","));
        if(!"".equals(rids)){
			sysRightsMapper.addUserRight(uids.split(","),rids.split(","));
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> getRightByUser(Integer uid) {
		List<Map<String,Object>> listMap=sysRightsMapper.getRightByUser(uid);
		return listMap;
	}

}

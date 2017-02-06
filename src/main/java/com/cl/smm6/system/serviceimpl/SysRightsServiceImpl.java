package com.cl.smm6.system.serviceimpl;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.mapper.SysRightsMapper;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysRightsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
	
	@Override
	public List<SysRights> getAlllist() {
	    return sysRightsMapper.getAllList();
	}

	@Override
	public int getMaxRightPos() {
		return sysRightsMapper.getMaxRightPos();
	}

	@Override
	public boolean appendRigheByURL(String url, String rightname, String rightdesc, Boolean common) {
        SysRights sysRights1 = new SysRights();
        sysRights1.setRighturl(url);
        Integer count=sysRightsMapper.getAllRow(sysRights1);
        if (count == 0) {
			SysRights sysRights = new SysRights();
			sysRights.setRighturl(url);
			// 处理权限位和权限码
		    int rightPos = 0;
			long rightCode = 1;
			// 查询最大权限位上的最大权限码
			List<Map<String,Object>> list3=sysRightsMapper.getMaxRPosAndMaxRCode();
            Integer topRightPos= (Integer) list3.get(0).get("maxP");
            Long topRightCode= (Long) list3.get(0).get("maxC");

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
		return this.getPageBean(page,rows,null);
	}

	@Override
	public boolean saveOrUpdateRights(SysRights sysRights, Integer smid) {
		Integer rid = null;
		// 如果id为空则去新增，新增成功这返回刚刚新增的ID
		if (null == sysRights.getId() && this.appendRigheByURL(sysRights.getRighturl(), sysRights.getRightname(),
				sysRights.getRightdesc(), sysRights.getCommon())) {
			// 如果新增成功，这查询其id，设置其所属页面
			SysRights sysRights1=sysRightsMapper.getRightByUrl(sysRights.getRighturl());
			rid=sysRights1.getId();
		} else if (this.updateByPrimaryKeySelective(sysRights) == 1) {// 不是新增就尝试去修改，并且修改成功设置rid
			rid = sysRights.getId();
		}
		if (null != rid) {
		    String[] rids=new String[1];
		    rids[0]=rid.toString();
		    //修改权限所属菜单
			sysRightsMapper.delMenuRight(rids);
			if(null!=smid){
				sysRightsMapper.setMenuRgithu(rid,smid);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRightsByIDs(String idTxts) {
		String[] ids=idTxts.split(",");
        //删除改权限与菜单的关系
        sysRightsMapper.delMenuRight(ids);
        //删除权限本身
		sysRightsMapper.deleteRightsByIDs(ids);
        return true;
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

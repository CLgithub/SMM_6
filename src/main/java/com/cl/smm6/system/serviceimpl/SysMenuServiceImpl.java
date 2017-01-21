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
        return DataUtil.getChildJson(sysMenuMapper.loadUserMenu(sysUser.getId()));
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
        return this.getPageBean(1, 9999, null);
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

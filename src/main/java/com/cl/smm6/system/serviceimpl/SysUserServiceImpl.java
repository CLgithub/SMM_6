package com.cl.smm6.system.serviceimpl;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapper.SysUserMapper;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.Constant;
import com.cl.smm6.common.uitl.DataUtil;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.common.uitl.ValidateUtil;
import com.cl.smm6.system.service.SysRightsService;
import com.cl.smm6.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

	@Resource(name = "sysUserMapper")
	public void setBaseMapper(BaseMapper<SysUser> baseMapper) {
		super.setBaseMapper(baseMapper);
	}

	@Resource
	private SysUserMapper sysUserMapper; 
	
	@Resource
	private SysRightsService sysRightsService;

	@Override
	public PageBean getUserPBBySearch(SysUser sysUser, Integer page, Integer pageSize, String searchWhere) {
//		Integer depCode=sysUser.getSysdepcode();
//		if(null!=depCode){
//			depCode=DataUtil.getDepCodePByDepCode(depCode);
//		}
//		String sql = "SELECT id,name,loginname,number,su.status as status,sysdepcode,depname from sys_user su left join sys_department sd on su.sysDepCode=sd.depCode "
//				+ " where 1=1 "
//				+ " and sysdepcode like '"+depCode+"%'"
//				+ " AND (`name` LIKE '%" + searchWhere + "%' OR loginName LIKE '%"
//				+ searchWhere + "%' OR number LIKE '%" + searchWhere + "%')";
//		PageBean pageBean2=this.getPageBean(Constant.PAGEBEANTYPE_MAP, sql, page, pageSize);

//        SysUser sysUser2=new SysUser();
//        sysUser2.setName(searchWhere);
        PageBean pageBean=this.getPageBean(page,pageSize,null);
//        System.out.println(pageBean);
        return pageBean;
	}

	@Override
	public SysUser doLogin(String loginName, String password) {
		// 得到用户
		List<SysUser> list=sysUserMapper.selectUListByLogin(loginName,password);
		// 如果用户名密码正确
		if (ValidateUtil.isValid(list)) {
			int pos = 0;
			long code = 0;
			// 初始化权限总和数组
			int maxRightPos = sysRightsService.getMaxRightPos();
			long[] rightSum = new long[maxRightPos + 1];
			SysUser sysUser = list.get(0);
			List<SysRights> list2=sysUserMapper.getRightByUserID(sysUser.getId());
			for (SysRights sysRight : list2) {
				// 计算改用户的权限总和
				pos = sysRight.getRightpos();
				code = sysRight.getRightcode();
				rightSum[pos] = rightSum[pos] | code;
			}
			sysUser.setRightSum(rightSum);
			return sysUser;
		} else {
			return null;
		}
	}

	@Override
	public boolean saveOrUpdateUser(SysUser user) {
		if("".equals(user.getSysdepcode())){
			user.setSysdepcode(null);
		}
		if (null == user.getId()) {
			user.setPassword(DataUtil.md5(Constant.INITPASSWORD));
			if (this.insert(user) == 1)
				return true;
			else
				return false;
		} else {
			user.setPassword(this.selectByPrimaryKey(user.getId()).getPassword());
			if (this.updateByPrimaryKey(user) == 1)
				return true;
			else
				return false;
		}
	}

	@Override
	public boolean deleteUserByIDs(String idTxts) {
		idTxts = "'" + idTxts + "'";
		idTxts = idTxts.replaceAll(",", "','");
		String sql = "DELETE from sys_user WHERE id in(" + idTxts + ")";
		if (this.executeSql(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void test() {
		System.out.println("SysUserServiceImpl.test()");
	}

}

package com.cl.smm6.system.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cl.smm6.common.entity.SysDepartment;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.Constant;
import com.cl.smm6.common.uitl.DataUtil;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysDepartmentService;

@Service
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment> implements SysDepartmentService {

	@Resource(name="sysDepartmentMapper")
	public void setBaseMapper(BaseMapper<SysDepartment> baseMapper) {
		super.setBaseMapper(baseMapper);
	}

	@Override
	public PageBean getDepPBBySearch(String searchWhere) {
		String sql = "select depCode as depcode, depname as depname, parentcode as _parentId, status as status from sys_department where 1=1 ";
		if (null != searchWhere) {
			sql += " and depCode like '%" + searchWhere + "%' "
					+ "or depName like '%" + searchWhere + "%' "
					+ "or parentcode like '%" + searchWhere + "%' "
					+ "or status like '%" + searchWhere + "%' ";
		}
		return this.getPageBean(Constant.PAGEBEANTYPE_MAP, sql, 1, 9999);
	}

	@Override
	public Object saveOrUpdateDep(SysDepartment sysDepartment) {
		if(null==sysDepartment.getDepcode()){//新增
			Integer parentCode=sysDepartment.getParentcode();
			Integer depCode=this.getDepCode(parentCode);
			sysDepartment.setDepcode(depCode);
			this.insert(sysDepartment);
			return true;
		} else {
			if(sysDepartment.getParentcode().equals(sysDepartment.getDepcode())){
				return "不能将自身部门设置为自身部门的子部门";
			}
			Integer depCode = this.getDepCode(sysDepartment.getParentcode());
			//修改其自身部门
			String sql1 = "update sys_department set depcode=?,depname=?,parentCode=?,status=? where depcode=?";
			this.executeSql(sql1, depCode, sysDepartment.getDepname(), sysDepartment.getParentcode(),
					sysDepartment.getStatus(), sysDepartment.getDepcode());
			//修改其子部门父code设置为0
			String sql2="update sys_department set parentCode='0' where parentCode=?";
			this.executeSql(sql2,sysDepartment.getDepcode());
			return true;
		}
	}

	@Override
	public Integer getDepCode(Integer parentcode) {
		String sql1="select max(depcode) as depcode from sys_department where parentCode=?";
		List<HashMap<String, Object>> list1=this.selectListMapBySql(sql1, parentcode);
		if(null!=list1.get(0).get("depcode")){
			Integer maxDepcode=(Integer) list1.get(0).get("depcode");
			return DataUtil.getNextDepCode(maxDepcode);
		}else {
			return DataUtil.getFirstDepCode(parentcode);
		}
	}

	@Override
	public Object deleteDepByCode(Integer depcode) {
		this.deleteByPrimaryKey(depcode);
		String sql1="update sys_department set parentCode='0' where parentCode=?";
		this.executeSql(sql1, depcode);
		return true;
	}
	
	@Override
	public List<HashMap<String, Object>> getUserDepList(SysUser sysUser) {
		Integer depCode=sysUser.getSysdepcode();
		if(null!=depCode){
			depCode=DataUtil.getDepCodePByDepCode(depCode);
		}
		String sql="select depcode as id,depname as text,parentCode as _parentId,status from sys_department where depcode LIKE '"+depCode+"%'";
		return DataUtil.getChildJson(this.selectListMapBySql(sql));
	}

}

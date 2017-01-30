package com.cl.smm6.system.serviceimpl;

import com.cl.smm6.common.entity.SysDepartment;
import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapper.SysDepartmentMapper;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.DataUtil;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysDepartmentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment> implements SysDepartmentService {

	@Resource(name="sysDepartmentMapper")
	public void setBaseMapper(BaseMapper<SysDepartment> baseMapper) {
		super.setBaseMapper(baseMapper);
	}

	@Resource
	private SysDepartmentMapper sysDepartmentMapper;

	@Override
	public PageBean getDepPBBySearch(String searchWhere) {
		return this.getPageBean(1,9999,null);
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
			sysDepartmentMapper.updateByPrimaryCode(sysDepartment,depCode);
			//修改其子部门父code设置为0
			sysDepartmentMapper.updateCcode(sysDepartment.getDepcode());
			return true;
		}
	}

	@Override
	public Integer getDepCode(Integer parentcode) {
		Integer maxDepcode=sysDepartmentMapper.getMaxCDepCode(parentcode);
		if(null!=maxDepcode){
			return DataUtil.getNextDepCode(maxDepcode);
		}else {
			return DataUtil.getFirstDepCode(parentcode);
		}
	}

	@Override
	public Object deleteDepByCode(Integer depcode) {
		this.deleteByPrimaryKey(depcode);
		sysDepartmentMapper.updateCcode(depcode);
		return true;
	}
	
	@Override
	public List<HashMap<String, Object>> getUserDepList(SysUser sysUser) {
		Integer depCode=sysUser.getSysdepcode();
		if(null!=depCode){
			depCode=DataUtil.getDepCodePByDepCode(depCode);
		}
		return DataUtil.getChildJson(sysDepartmentMapper.getUserDepList(depCode));
	}

}

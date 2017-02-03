package com.cl.smm6.system.serviceimpl;

import java.util.List;
import javax.annotation.Resource;
import com.cl.smm6.common.mapper.SysLogMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.cl.smm6.common.entity.SysLog;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.LogUtil;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysLogService;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

	@Resource(name = "sysLogMapper")
	public void setBaseMapper(BaseMapper<SysLog> baseMapper) {
		super.setBaseMapper(baseMapper);
	}

	@Resource
	private SysLogMapper sysLogMapper;

	@Override
	@CacheEvict(value = "sysParamCache",allEntries = true)
	public void insert0(SysLog t) {
		sysLogMapper.insertD(t,LogUtil.generateLogTableName(0));
	}

	@Override
	@Cacheable(value="sysParamCache")
	public PageBean getLogsPBBySearch(Integer page, Integer rows) {
		List<String> logTabNames = sysLogMapper.getLogTabNames();
		Integer allRow = sysLogMapper.getTotla(logTabNames,(page-1)*rows,rows,null);// 得到总的记录长度
		List<SysLog> list= sysLogMapper.getTListBySearch(logTabNames,(page-1)*rows,rows,null);
		PageBean pageBean = new PageBean();
		pageBean.setTotal(allRow);
		pageBean.setRows(list);
		return pageBean;
	}

	@Override
	@CacheEvict(value = "sysParamCache",allEntries=true)
	public void createLogTab(String tabName) {
		sysLogMapper.createLogTab(tabName);
	}

}

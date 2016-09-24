package com.cl.smm6.system.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cl.smm6.common.entity.SysLog;
import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.servicebase.BaseServiceImpl;
import com.cl.smm6.common.uitl.Constant;
import com.cl.smm6.common.uitl.LogUtil;
import com.cl.smm6.common.uitl.PageBean;
import com.cl.smm6.system.service.SysLogService;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

	@Resource(name = "sysLogMapper")
	public void setBaseMapper(BaseMapper<SysLog> baseMapper) {
		super.setBaseMapper(baseMapper);
	}

	@Override
	public void insert0(SysLog t) {
		String sql = "insert into " + LogUtil.generateLogTableName(0)
				+ "(operator,opertime,opername,operparams,operresult,resultmsg) values(?,?,?,?,?,?)";
		this.executeSql(sql, t.getOperator(), t.getOpertime(), t.getOpername(), t.getOperparams(), t.getOperresult(),
				t.getResultmsg());
	}

	@Override
	public PageBean getLogsPBBySearch(Integer page, Integer rows, String startTime, String endTime,
			String searchOperator, String searchOpername, String operresult) {
		String sql2 = "";
		String search = "";
		if ("".equals(startTime) && "".equals(endTime)) {
			String table0 = LogUtil.generateLogTableName(0);
			sql2 = "SELECT * FROM " + table0 + " where 1=1 ";
			if (!"".equals(searchOperator)) {
				search += " and operator like '%" + searchOperator + "%'";
			}
			if (!"".equals(searchOpername)) {
				search += " and opername like '%" + searchOpername + "%'";
			}
			if (!"all".equals(operresult)) {
				search += " and operresult ='" + operresult + "'";
			}
			sql2 = sql2 + search + " ORDER BY operTime desc";
		} else {
			String sql1 = "SHOW OPEN TABLES WHERE `table` LIKE '%sys_log_%'";
			List<HashMap<String, Object>> list = this.selectListMapBySql(sql1);
			for (HashMap<String, Object> map : list) {
				sql2 = sql2 + " UNION SELECT * FROM " + map.get("Table") + " where 1=1 ";
				if (!"".equals(startTime)) {
					search += " and operTime>='" + startTime + "'";
				}
				if (!"".equals(endTime)) {
					search += " and operTime<='" + endTime + "'";
				}
				if (!"".equals(searchOperator)) {
					search += " and operator like '%" + searchOperator + "%'";
				}
				if (!"".equals(searchOpername)) {
					search += " and opername like '%" + searchOpername + "%'";
				}
				if (!"all".equals(operresult)) {
					search += " and operresult ='" + operresult + "'";
				}
				sql2 = sql2 + search;
			}
			sql2 = sql2.replaceFirst(" UNION", "SELECT * FROM(") + ") a ORDER BY operTime desc";
		}
		return this.getPageBean(Constant.PAGEBEANTYPE_T, sql2, page, rows);
	}

}

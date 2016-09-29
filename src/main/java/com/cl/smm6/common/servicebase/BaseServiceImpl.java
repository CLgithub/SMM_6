package com.cl.smm6.common.servicebase;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cl.smm6.common.mapperbase.BaseMapper;
import com.cl.smm6.common.uitl.Constant;
import com.cl.smm6.common.uitl.PageBean;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private BaseMapper<T> baseMapper;

	@Resource
	public void setBaseMapper(BaseMapper<T> baseMapper) {
		this.baseMapper = baseMapper;
	}

	private Class<T> clazz;

	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public int insert(T t) {
		return baseMapper.insert(t);
	}

	public int insertSelective(T t) {
		return baseMapper.insertSelective(t);
	}

	public int deleteByPrimaryKey(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(T t) {
		return baseMapper.updateByPrimaryKeySelective(t);
	}

	public int updateByPrimaryKey(T t) {
		return baseMapper.updateByPrimaryKey(t);
	}

	public T selectByPrimaryKey(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	public List<T> selectListTBySql(String sql, Object... objects) {
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz), objects);
	}

	public List<HashMap<String, Object>> selectListMapBySql(String sql, Object... objects) {
//		return jdbcTemplate.queryForList(sql, objects);
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, objects);
		List<HashMap<String,Object>> list2=new ArrayList<>();
		for(Map<String, Object> map:list){
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			HashMap<String, Object> hashMap=new HashMap<>();
			while(iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();
				hashMap.put(entry.getKey(), entry.getValue());
			}
			list2.add(hashMap);
		}
		return list2;
	}

	public int executeSql(String sql, Object... objects) {
		return jdbcTemplate.update(sql, objects);
	}

	public PageBean getPageBean(Integer type, String sql, Integer page, Integer pageSize, Object... objects) {
		String pageSql = this.getPageBeanSqlMYSQL(sql, page, pageSize);
		List<?> list = new ArrayList<T>();
		if (type == Constant.PAGEBEANTYPE_T) {
			list = this.selectListTBySql(pageSql, objects);// 得到某段记录列表
		} else if (type == Constant.PAGEBEANTYPE_MAP) {
			list = this.selectListMapBySql(pageSql, objects);
		}
		Integer allRow = this.getTotlaBySql(sql, objects);// 得到总的记录长度
		PageBean pageBean = new PageBean();
		pageBean.setTotal(allRow);
		pageBean.setRows(list);

		// pageBean.setTotal(allRow);
		// pageBean.setTotal_rows(allRow);
		// pageBean.setPage_data(list);

		return pageBean;
	}
	
	public String getPageBeanSqlMYSQL(String sql, Integer page, Integer pageSize) {
		Integer startI = (page - 1) * pageSize;
		String pageBeanSql = sql + " LIMIT " + startI + "," + pageSize;
		return pageBeanSql;
	}
	
	public Integer getTotlaBySql(String sql, Object... objects) {
		int index = sql.toLowerCase().indexOf("from");
		String subString = sql.substring(index);
		String newSql = "select count(*) " + subString;
		return jdbcTemplate.queryForObject(newSql, objects, Integer.class);
	}
	
	public PageBean getPageBean(int currentPage, int pageSize, T t) {
		Integer total = baseMapper.getAllRow(t);
		List<Map<?, ?>> rows = baseMapper.getTListBySearch(PageBean.countOffset(pageSize, currentPage), pageSize, t);
		PageBean pageBean = new PageBean();
		pageBean.setTotal(total);
		pageBean.setRows(rows);
		pageBean.setTotalPage(PageBean.countTatalPage(pageSize, total));
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		return pageBean;
	}

	

	

}

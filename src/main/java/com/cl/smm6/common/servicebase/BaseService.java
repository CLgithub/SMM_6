package com.cl.smm6.common.servicebase;

import com.cl.smm6.common.uitl.PageBean;

import java.util.HashMap;
import java.util.List;

public interface BaseService<T> {

	/**
	 * 增
	 */
	int insert(T t);

	/**
	 * 增，参数可以不全
	 */
	int insertSelective(T t);

	/**
	 * 删
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 改,参数可以不全
	 */
	int updateByPrimaryKeySelective(T t);

	/**
	 * 改
	 */
	int updateByPrimaryKey(T t);

	/**
	 * 根据id查询单个实体
	 */
	T selectByPrimaryKey(Integer id);

	/**
	 * 用sql查询listT
	 */
	List<T> selectListTBySql(String sql, Object... objects);

	/**
	 * 用sql查询ListMap
	 */
	List<HashMap<String, Object>> selectListMapBySql(String sql, Object... objects);

	/**
	 * 执行原生Sql
	 */
	int executeSql(String sql, Object... objects);

	/**
	 * 根据sql封装pageBean
	 * 
	 * @author L
	 * @date 2015-11-19
	 * @param type
	 *            PageBean封装类型,0:封装已封装好的泛型ListT,1:封装ListMap
	 * @param sql
	 *            sql语句
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页多少条记录
	 * @param objects
	 *            参数
	 * @return
	 */
	PageBean getPageBean(Integer type, String sql, Integer page, Integer pageSize, Object... objects);

	
	/**
	 * 得到pageBean
	 * @param currentPage 第几页
	 * @param pageSize 每页多少条记录
	 * @param t 条件封装
	 * @return pageBean
	 */
	PageBean getPageBean(int currentPage, int pageSize, T t);

	/**
	 * 通过sql，得到该sql查询的记录数
	 * @author L
	 * @date 2015-7-2
	 * @param sql
	 * @param objects
	 * @return
	 */
	Integer getTotlaBySql(String sql, Object... objects);

	/**
	 * 得到分页查询sql,此处为mysql语法
	 * 
	 * @author L
	 * @date 2015-11-18
	 * @param sql 原sql
	 * @param page 第几页
	 * @param pageSize 每页多少条记录
	 * @return
	 */
	String getPageBeanSqlMYSQL(String sql, Integer page, Integer pageSize);

}

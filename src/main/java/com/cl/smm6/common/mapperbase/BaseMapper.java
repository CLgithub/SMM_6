package com.cl.smm6.common.mapperbase;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {

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
	 * 查询满足条件的记录条数
	 * @param t 条件封装
	 * @return 记录条数
	 */
	Integer getAllRow(@Param("t")T t);

	/**
	 * 查询满足条件的记录数
	 * @param startIndex 开始的索引
	 * @param len 查询条数
	 * @param t 查询条件
	 * @return 放回listMap
	 */
	List<Map<?, ?>> getTListBySearch(@Param("startIndex")Integer startIndex, @Param("len")Integer len, @Param("t")T t);

}

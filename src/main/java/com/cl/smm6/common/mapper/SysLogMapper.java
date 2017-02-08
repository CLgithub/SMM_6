package com.cl.smm6.common.mapper;

import com.cl.smm6.common.entity.SysLog;
import com.cl.smm6.common.mapperbase.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 得到所有日志表名
     * @return
     */
    ArrayList<String> getLogTabNames();

    /**
     * 根据条件查询这些表名的日志纪录
     * @param logTabNames
     * @param startIndex
     * @param rows
     * @param sysLogMapper
     * @return
     */
    ArrayList<SysLog> getTListBySearch(@Param("logTabNames") List<String> logTabNames, @Param("startIndex") Integer startIndex, @Param("rows") Integer rows, @Param("t") SysLogMapper sysLogMapper);

    /**
     * 根据条件查询这这表的日志纪录总数
     * @param logTabNames
     * @param startIndex
     * @param rows
     * @param sysLogMapper
     * @return
     */
    Integer getTotla(@Param("logTabNames") List<String> logTabNames, @Param("startIndex") Integer startIndex, @Param("rows") Integer rows, @Param("t") SysLogMapper sysLogMapper);

    /**
     * 将日志纪录进当月日志表
     * @param t 日志
     * @param tableName 当月日志表名
     */
    void insertD(@Param("t") SysLog t, @Param("tableName") String tableName);

    /**
     * 创建日志表
     * @param tabName 表名
     */
    void createLogTab(@Param("tabName") String tabName);
}
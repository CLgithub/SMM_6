package com.cl.smm6.common.mapper;

import com.cl.smm6.common.entity.SysDepartment;
import com.cl.smm6.common.mapperbase.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {

    /**
     * 得到该部门最大的子部门code
     * @param code
     * @return
     */
    Integer getMaxCDepCode(@Param("code") Integer code);

    /**
     * 修改部门，编号修改为制定编号
     * @param sysDepartment 部门对象
     * @param depCode 指定编号
     */
    void updateByPrimaryCode(@Param("t") SysDepartment sysDepartment, @Param("depCode") Integer depCode);

    /**
     * 修改其子部门父code设置为0
     * @param depCode
     */
    void updateCcode(@Param("depCode") Integer depCode);

    /**
     * 得到该部门及其一下部门
     * @param depCode 部门编号（已经去尾部0）
     * @return
     */
    List<HashMap<String,Object>> getUserDepList(@Param("depCode0") Integer depCode);
}
package com.cl.smm6.common.entity;

public class SysDepartment extends BaseEntity{
    private Integer depcode;

    private String depname;

    private Integer parentcode;

    private Integer status;

    public Integer getDepcode() {
        return depcode;
    }

    public void setDepcode(Integer depcode) {
        this.depcode = depcode;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname == null ? null : depname.trim();
    }

    public Integer getParentcode() {
        return parentcode;
    }

    public void setParentcode(Integer parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
package com.cl.smm6.common.entity;

import java.util.List;

public class SysMenu extends BaseEntity {
	private Integer id;
	private String menuname;
	private String menuurl;
	private Integer parentid;
	private Integer status;
	public List<SysMenu> Children;

	public List<SysMenu> getChildren() {
		return Children;
	}

	public void setChildren(List<SysMenu> children) {
		Children = children;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname == null ? null : menuname.trim();
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl == null ? null : menuurl.trim();
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
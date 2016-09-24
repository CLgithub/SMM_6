package com.cl.smm6.common.entity;

public class SysRights extends BaseEntity {
	private Integer id;
	private String righturl;
	private String rightname;
	private String rightdesc;
	private Integer rightpos;
	private Long rightcode;
	private Boolean common;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRighturl() {
		return righturl;
	}

	public void setRighturl(String righturl) {
		this.righturl = righturl == null ? null : righturl.trim();
	}

	public String getRightname() {
		return rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname == null ? null : rightname.trim();
	}

	public String getRightdesc() {
		return rightdesc;
	}

	public void setRightdesc(String rightdesc) {
		this.rightdesc = rightdesc == null ? null : rightdesc.trim();
	}

	public Integer getRightpos() {
		return rightpos;
	}

	public void setRightpos(Integer rightpos) {
		this.rightpos = rightpos;
	}

	public Long getRightcode() {
		return rightcode;
	}

	public void setRightcode(Long rightcode) {
		this.rightcode = rightcode;
	}

	public Boolean getCommon() {
		return common;
	}

	public void setCommon(Boolean common) {
		this.common = common;
	}
}
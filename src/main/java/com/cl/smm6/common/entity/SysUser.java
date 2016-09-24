package com.cl.smm6.common.entity;

public class SysUser extends BaseEntity {
	private Integer id;
	private String name;
	private String loginname;
	private String password;
	private Integer number;
	private Integer status;
	private Integer sysdepcode;

	// 用户权限总和
	private long[] rightSum;

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSysdepcode() {
		return sysdepcode;
	}

	public void setSysdepcode(Integer sysdepcode) {
		this.sysdepcode = sysdepcode;
	}

	/**
	 * 判断该用户是否有该权限
	 * @author L
	 * @date 2015年11月26日
	 * @param r 权限
	 * @return true 有权限
	 */
	public boolean hasRight(SysRights r) {
		int pos = r.getRightpos();
		long code = r.getRightcode();
		long ret = rightSum[pos] & code;
		return !(ret == 0);
	}
}
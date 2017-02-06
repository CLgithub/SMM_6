package com.cl.smm6.common.entity;

import java.util.Date;

public class SysLog extends BaseEntity {
	private Integer id;

	private String operator;

	private Date opertime = new Date();

	private String opername;

	private String operparams;

	private String operresult;

	private String resultmsg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername == null ? null : opername.trim();
	}

	public String getOperparams() {
		return operparams;
	}

	public void setOperparams(String operparams) {
		this.operparams = operparams == null ? null : operparams.trim();
	}

	public String getOperresult() {
		return operresult;
	}

	public void setOperresult(String operresult) {
		this.operresult = operresult == null ? null : operresult.trim();
	}

	public String getResultmsg() {
		return resultmsg;
	}

	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg == null ? null : resultmsg.trim();
	}

	@Override
	public String toString() {
		return "SysLog{" +
				"id=" + id +
				", operator='" + operator + '\'' +
				", opertime=" + opertime +
				", opername='" + opername + '\'' +
				", operparams='" + operparams + '\'' +
				", operresult='" + operresult + '\'' +
				", resultmsg='" + resultmsg + '\'' +
				'}';
	}
}
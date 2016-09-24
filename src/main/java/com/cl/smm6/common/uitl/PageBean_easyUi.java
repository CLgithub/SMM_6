package com.cl.smm6.common.uitl;

import java.util.List;

/**
 * PageBean分装（目前只用到list和allRow,其余有ligerui自行计算得到）
 * 
 * @author L
 * @date 2015-5-4
 * @param <T>
 */
public class PageBean_easyUi {
	private int total; // 总记录数
	private List<?> rows; // 要返回的某一页的记录列表

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

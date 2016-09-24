package com.cl.smm6.common.uitl;

import java.util.List;

/**
 * PageBean分装（目前只用到list和allRow,其余有ligerui自行计算得到）
 * 
 * @author L
 * @date 2015-5-4
 * @param <T>
 */
public class PageBean_bs_grid {
	private int total_rows; // 总记录数
	private List<?> page_data; // 要返回的某一页的记录列表

	public int getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}

	public List<?> getPage_data() {
		return page_data;
	}

	public void setPage_data(List<?> page_data) {
		this.page_data = page_data;
	}

}

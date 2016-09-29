package com.cl.smm6.common.uitl;

import java.util.List;

/**
 * PageBean封装
 * 
 * @author L
 * @date 2015-5-4
 * @param <T>
 */
public class PageBean {
	private int total; // 总记录数
	private List<?> rows; // 要返回的某一页的记录列表
	private int totalPage; // 总页数
	private int currentPage; // 当前页
	private int pageSize; // 每页的记录数

	/**
	 * 计算总页数 静态方法
	 * 
	 * @param pageSize
	 *            每页的记录数
	 * @param total
	 *            总记录数
	 * @return 总页数
	 */
	public static int countTatalPage(final int pageSize, final int total) {
		int toalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		return toalPage;
	}

	/**
	 * 计算当前页开始的记录
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前第几页
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/**
	 * 计算当前页，若为0或者请求的URL中没有“?page = ”则用1代替
	 * 
	 * @param page
	 *            传入的参数（可能为空，即0 则返回1）
	 * @return
	 */
	public static int countCurrentPage(int page) {
		final int curpage = (page == 0 ? 1 : page);
		return curpage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageBean [total=" + total + ", rows=" + rows + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + "]";
	}

}

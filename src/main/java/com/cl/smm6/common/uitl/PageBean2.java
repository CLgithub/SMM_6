package com.cl.smm6.common.uitl;

import java.util.List;

/**
 * PageBean分装（目前只用到list和allRow,其余有ligerui自行计算得到）
 * 
 * @author L
 * @date 2015-5-4
 * @param <T>
 */
public class PageBean2 {
	private List<?> list; // 要返回的某一页的记录列表
	private int allRow; // 总记录数
	/*
	 * private int totalPage; // 总页数 private int currentPage; // 当前页 private int
	 * pageSize; // 每页的记录数 private boolean isFirstPage; // 是否为当前第一页 private
	 * boolean isLastPage; // 是否为最后一页 private boolean hasPreviousPage; // 是否有前一页
	 * private boolean hasNextPage; // 是否有下一页
	 */

	/**
	 * 计算总页数 静态方法
	 * 
	 * @param pageSize
	 *            每页的记录数
	 * @param allRow
	 *            总记录数
	 * @return 总页数
	 */
	public static int countTatalPage(final int pageSize, final int allRow) {
		int toalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
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

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	/**
	 * 根据原sql得到带分页查询的sql
	 * 
	 * @author L
	 * @date 2015-5-4
	 * @param sql
	 *            原sql
	 * @param page
	 *            第几页
	 * @param pagesize
	 *            每页大小
	 * @return 带分页查询的sql
	 */
	public static String getByPageSql(String sql, int page, int pagesize) {
		int max = page * pagesize;
		int min = max - pagesize + 1;
		return "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <= " + max + ") WHERE RN >="
				+ min;
	}

}

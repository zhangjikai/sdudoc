package com.sdudoc.utils;

import java.util.List;

/**
 * 用于分页显示的类
 * @author zhangjk
 *
 * @param <T>
 */
public class Pager<T> {

	/** 数据 */
	private List<T> records;
	/** 总记录数 */
	private long recordTotal;
	/** 总页数 */
	private int pageTotal = 1;
	/** 每页显示记录数 */
	private int pageSize = 5;
	/** 当前页 */
	private int pageNO = 1;

	/** 首页 */
	private int firstPageNo = 1;
	/** 前一页 */
	private int prePageNo = 1;
	/** 后一页 */
	private int nextPageNo = 1;
	/** 尾页 */
	private int lastPageNo = 1;

	/**
	 * 分页辅助类
	 * @param pageSize 每页记录数
	 * @param pageNo 当前页数
	 * @param recordTotal 总记录数
	 * @param records 记录
	 */
	public Pager(int pageSize, int pageNo, long recordTotal, List<T> records) {
		this.pageSize = pageSize;
		this.pageNO = pageNo;
		this.recordTotal = recordTotal;
		this.records = records;
		if (recordTotal % pageSize == 0) {
			pageTotal = (int) (recordTotal / pageSize);
		} else {
			pageTotal = (int) (recordTotal / pageSize) + 1;
		}
		lastPageNo = pageTotal;
		if (pageNo > 1) {
			prePageNo = pageNo - 1;
		}
		if (pageNo == lastPageNo) {
			nextPageNo = lastPageNo;
		} else {
			nextPageNo = pageNo + 1;
		}

	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}


	public long getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(long recordTotal) {
		this.recordTotal = recordTotal;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNO() {
		return pageNO;
	}

	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrePageNo() {
		return prePageNo;
	}

	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

}

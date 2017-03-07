package com.permission.core.queryable;

/**
 * 分页查询数据时候的请求基类，
 * 默认rows为20，page为0，order默认为"desc"
 */
public abstract class PageRequest {
	
	private int page = 0;								// 当前页，默认0
	private int rows = 20;								// 每页显示记录数，默认20
	private String sort = "id";							// 排序字段，默认"id"
	private String order = "desc";						// 排序方式，asc/desc，默认"desc"
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}

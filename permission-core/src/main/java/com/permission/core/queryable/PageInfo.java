package com.permission.core.queryable;

import java.util.List;

/**
 * 查询到的结果集封装成的数据分页对象
 * @param <T>
 */
public class PageInfo<T> {
	
	private Integer pageNo;				//当前页码
	private Integer totalPage;			//总页数
	private Long total;					//总记录数
	private List<T> date;				//数据
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getDate() {
		return date;
	}
	public void setDate(List<T> date) {
		this.date = date;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public PageInfo() {
		super();
	}
	
	/**
	 * 构建PageInfo实例
	 * @param pageNo		当前页码
	 * @param totalPage		总页数
	 * @param total			总记录数
	 * @param date			当前页数据
	 */
	public PageInfo(Integer pageNo, Integer totalPage, Long total, List<T> date) {
		super();
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.total = total;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "PageInfo [pageNo=" + pageNo + ", totalPage=" + totalPage + ", total=" + total + ", date=" + date + "]";
	}
	
}

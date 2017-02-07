package com.wrqzn.dbrecord.op;

import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class QueryResult<T> {

	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer totalCount;
	private boolean hasPrevPage = false;
	private boolean hasNextPage = false;
	private List<T> content;



	public QueryResult() {
		this.currentPage = 1;
		this.pageSize = 10;
	}

	public QueryResult(Integer currentPage) {
		this.currentPage = currentPage;
		this.pageSize = 10;
	}

	public QueryResult(Integer currentPage, Integer pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isHasPrevPage() {
		return hasPrevPage;
	}

	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getStartRow(){
		return  (currentPage -1) * pageSize;
	}
	public int getEndRow(){
		return currentPage * pageSize;
	}

}

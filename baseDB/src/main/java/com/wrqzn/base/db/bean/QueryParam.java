package com.wrqzn.base.db.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class QueryParam {
	private String sql;
	private List<Object> param;

	private StringBuilder builder;

	public QueryParam() {
		this.param = new ArrayList<>();
		this.builder = new StringBuilder();
	}

	public QueryParam(String sql) {
		this.sql = sql;
		this.param = new ArrayList<>();
		this.builder = new StringBuilder();
	}

	public QueryParam(String sql, List<Object> param) {
		this.sql = sql;
		this.param = param;
		this.builder = new StringBuilder();
	}


	public void addSql(String sqlSegment ){
		builder.append(sqlSegment);
	}


	public void addParam(Object object){
		param.add(object);
	}


	public String getSql() {
		if (null == sql) {
			this.sql = builder.toString();
		}
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParam() {
		return param;
	}

	public void setParam(List<Object> param) {
		this.param = param;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}
}

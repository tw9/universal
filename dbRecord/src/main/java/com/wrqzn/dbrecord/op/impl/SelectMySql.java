package com.wrqzn.dbrecord.op.impl;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.QueryResult;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.biz.DBQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class SelectMySql<T> extends Select<T> {


	public SelectMySql(Class<T> type) {
		super(type);
	}

	public SelectMySql(Class<T> type, DataSource dataSource) {
		super(type, dataSource);
	}

	public SelectMySql(DataSource dataSource) {
		super(dataSource);
	}

	public SelectMySql() {
		super();
	}

	@Override
	public QueryResult query() {
		QueryResult<T> result = new QueryResult<T>();
		List<T>  list = DBQuery.run(this,type);
		result.setContent( list );
		resetSql();
		return result;
	}

	@Override
	public QueryResult query(QueryResult pageQuery) {
		return null;
	}
}

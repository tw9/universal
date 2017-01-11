package com.wrqzn.dbrecord.op.impl;

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
public class SelectMySql<T> extends Select {


	final Class<T> type;

	public SelectMySql(Class<T> type) {
		this.type= type;
	}

	public SelectMySql() {
		type = (Class<T>) Map.class;
	}

	@Override
	public QueryResult query() {
		QueryResult<T> result = new QueryResult<T>();
		List<T>  list = null;
		if ( Map.class != type ) {
			list = DBQuery.run(this,type);
		} else {
			list = (List<T>) DBQuery.run(this);
		}
		result.setContent( list );
		return result;
	}

	@Override
	public QueryResult query(QueryResult pageQuery) {
		return null;
	}
}

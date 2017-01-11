package com.wrqzn.dbrecord.op;


import com.wrqzn.dbrecord.DataSource;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class Select<T> extends DBOperate {


	protected Class<T> type;
	protected String[] showFiedls;


	public Select(Class<T> type) {
		this.type= type;
	}
	public Select(Class<T> type, DataSource dataSource){
		this.dataSource = dataSource;
		this.type= type;
	}

	public Select(DataSource dataSource){
		this.dataSource = dataSource;
		type = (Class<T>) Map.class;
	}

	public Select() {
		type = (Class<T>) Map.class;
	}




	public abstract QueryResult query();
	public abstract QueryResult query(QueryResult pageQuery);


	public String[] getShowFiedls() {
		return showFiedls;
	}

	public void setShowFiedls(String[] showFiedls) {
		this.showFiedls = showFiedls;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}
}

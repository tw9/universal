package com.wrqzn.dbrecord.op;


import com.wrqzn.dbrecord.DataSource;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class Select<T> extends DBOperate {


	protected String[] showFiedls;



// constructor
	public Select(Class<T> type) {
		super();
		this.entityType= type;
	}
	public Select(Class<T> type, DataSource dataSource){
		super();
		this.dataSource = dataSource;
		this.entityType= type;
	}

	public Select(DataSource dataSource){
		super();
		this.dataSource = dataSource;
		entityType= (Class<T>) Map.class;
	}


	public Select() {
		entityType = (Class<T>) Map.class;
	}



// method
	public abstract QueryResult query();
	public abstract QueryResult query(QueryResult pageQuery);


// getter setter
	public String[] getShowFiedls() {
		return showFiedls;
	}

	public void setShowFiedls(String[] showFiedls) {
		this.showFiedls = showFiedls;
	}


}

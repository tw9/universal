package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class Select extends DBOperate {



	protected String[] showFiedls;


	public Select() {
	}

	public abstract QueryResult query();
	public abstract QueryResult query(QueryResult pageQuery);


	public String[] getShowFiedls() {
		return showFiedls;
	}

	public void setShowFiedls(String[] showFiedls) {
		this.showFiedls = showFiedls;
	}
}

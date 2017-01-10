package com.wrqzn.dbrecord.op;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class Select extends DBOperate {



	protected String[] selectFields;
	protected String[] showFiedls;


	public Select() {
	}

	public abstract QueryResult query();
	public abstract QueryResult query(QueryResult pageQuery);


	public String[] getSelectFields() {
		return selectFields;
	}

	public void setSelectFields(String[] selectFields) {
		this.selectFields = selectFields;
	}

	public String[] getShowFiedls() {
		return showFiedls;
	}

	public void setShowFiedls(String[] showFiedls) {
		this.showFiedls = showFiedls;
	}
}

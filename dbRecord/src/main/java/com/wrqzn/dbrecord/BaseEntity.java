package com.wrqzn.dbrecord;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class BaseEntity<T> {

	protected T id;


	public abstract List<?> formatResult(ResultSet resultSet);


	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}

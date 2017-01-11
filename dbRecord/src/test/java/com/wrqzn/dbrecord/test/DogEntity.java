package com.wrqzn.dbrecord.test;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.BaseEntity;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/11/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DogEntity extends BaseEntity<Integer> {

	@Override
	public DataSource getDataSource() {
		return null;
	}

	@Override
	public String getTableName() {
		return null;
	}

	@Override
	public List<String> selectAllFields() {
		return null;
	}

	@Override
	public List<?> formatResult(ResultSet resultSet) {
		return null;
	}
}

package com.wrqzn.base.permission.user;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.DBEntity;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 2/7/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UserEntity extends DBEntity<Integer> {
	private String loginName;
	private String password;
	private String name;

	@Override
	public DataSource getDataSource() {
		return ConfigData.getBaseDataSource();
	}

	@Override
	public String getTableName() {
		return null;
	}

	@Override
	public Map<String, Object> getFieldData() {
		return null;
	}

	@Override
	public List<?> formatResult(ResultSet resultSet) {
		return null;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

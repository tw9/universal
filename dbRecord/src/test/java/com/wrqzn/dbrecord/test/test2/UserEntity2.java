package com.wrqzn.dbrecord.test.test2;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.DBEntity;
import com.wrqzn.dbrecord.test.UserEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 2/4/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UserEntity2 extends DBEntity<Integer> {

	private String name;
	private Integer age;

	@Override
	public DataSource getDataSource() {
		return ConfigData.getBaseDataSource();
	}

	@Override
	public String getTableName() {
		return "user";
	}

	@Override
	public Map<String, Object> getFieldData() {
		Map<String,Object> data = new HashMap<>();
		data.put("name",getName());
		data.put("age",getAge());
		return data;
	}

	@Override
	public List<?> formatResult(ResultSet resultSet) {
		List<UserEntity> data = new ArrayList<>();
		List<Map<String,Object>> mapData = resultToList(resultSet);
		mapData.forEach( d -> {
			UserEntity userEntity = new UserEntity();
			userEntity.setId((Integer) d.get("id"));
			userEntity.setName((String) d.get("name"));
			userEntity.setAge((Integer) d.get("age"));
			data.add(userEntity);
		});
		return data;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

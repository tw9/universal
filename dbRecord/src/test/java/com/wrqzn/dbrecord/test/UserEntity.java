package com.wrqzn.dbrecord.test;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.BaseEntity;

import java.sql.ResultSet;
import java.util.*;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UserEntity extends BaseEntity<Integer> {
	private String name;
	private Integer age;

	public UserEntity() {
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

	@Override
	public List<UserEntity> formatResult(ResultSet resultSet) {
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

	@Override
	public List<String> selectAllFields() {
		return Arrays.asList("id","name","age");
	}

	@Override
	public String getTableName() {
		return "user";
	}

	@Override
	public DataSource getDataSource() {
		return ConfigData.getBaseDataSource();
	}
}

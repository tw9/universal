package com.wrqzn.dbrecord.test;

import com.wrqzn.dbrecord.BaseEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UserEntity extends BaseEntity<Integer> {
	private String name;
	private int age;

	public UserEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public List<UserEntity> formatResult(ResultSet resultSet) {
		List<UserEntity> list = new ArrayList<>();
		UserEntity u1 = new UserEntity();
		u1.setId(2);
		u1.setName("tom");
		u1.setAge(33);

		UserEntity u2 = new UserEntity();
		u2.setId(5);
		u2.setName("jerry");
		u2.setAge(34);

		list.add(u1);
		list.add(u2);
		return list;
	}
}

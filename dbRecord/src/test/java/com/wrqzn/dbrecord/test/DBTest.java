package com.wrqzn.dbrecord.test;

import com.wrqzn.dbrecord.op.QueryResult;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.impl.SelectMySql;
import org.junit.Test;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBTest {


	@Test
	public void test(){
		Select select = new SelectMySql<>(UserEntity.class);
		QueryResult<UserEntity> result = select.query();

		System.out.println(result);

	}

	@Test
	public void test2(){
		Select select = new SelectMySql<>();
		QueryResult<Map<String,Object>> result1 = select.query();

		System.out.println(result1);
	}




}
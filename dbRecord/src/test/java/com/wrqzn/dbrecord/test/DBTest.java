package com.wrqzn.dbrecord.test;

import com.sun.deploy.config.Config;
import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.DatabaseType;
import com.wrqzn.dbrecord.op.Insert;
import com.wrqzn.dbrecord.op.QueryResult;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.biz.SelectFactory;
import com.wrqzn.dbrecord.op.impl.SelectMySql;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBTest {

	static {
		DataSource dataSource = new DataSource();
		dataSource.setDatabaseType(DatabaseType.mysql);
		dataSource.setHost("127.0.0.1");
		dataSource.setPort(3306);
		dataSource.setSchema("tw");
		dataSource.setUserName("root");
		dataSource.setPassword("adminroot");
		ConfigData.addBaseDataSource(dataSource);
	}


	@Test
	public void test(){
		Select select = SelectFactory.getSelect(ConfigData.getBaseDataSource());
		select.addSql("select age,name from user");
		QueryResult result = select.query();
		System.out.println(result);
	}

	@Test
	public void test2(){
		UserEntity userEntity = new UserEntity();
		QueryResult<UserEntity> result = userEntity.findAll();
		QueryResult<UserEntity> result1 = userEntity.findOne(1);
		userEntity.addSql("select id, name from user");
		QueryResult<UserEntity> result2 = userEntity.query();
		System.out.println(result.toString());
	}

	@Test
	public void inserttest(){
//		Insert insert = new Insert();
//		Map<String,Object> newUser = new HashMap<>();
//		insert.save("user",newUser);
	}


}

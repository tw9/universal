package com.wrqzn.dbrecord.test;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.DatabaseType;
import com.wrqzn.dbrecord.DefaultData;
import com.wrqzn.dbrecord.op.QueryResult;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.impl.SelectMySql;
import org.junit.Test;


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
		DefaultData.addBaseDataSource(dataSource);
	}


	@Test
	public void test(){
		Select select = new SelectMySql<>(UserEntity.class);
//		Select<UserEntity> select = new SelectMySql<>();
		QueryResult<UserEntity> result = select.query();

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




}

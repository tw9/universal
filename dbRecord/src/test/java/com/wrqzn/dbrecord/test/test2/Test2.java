package com.wrqzn.dbrecord.test.test2;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.DatabaseType;
import com.wrqzn.dbrecord.op.QueryResult;
import org.junit.Test;


/**
 * Created by WANG, RUIQING on 2/4/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Test2 {

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
//		UserEntity2 userEntity2 = new UserEntity2();
//		QueryResult<UserEntity2> all = userEntity2.findAll();
//		System.out.println(all.toString());

		UserEntity2 u3 = new UserEntity2();
		u3.setName("cat");
		u3.setAge(3);
		u3.save();
		System.out.println(u3);

//		UserEntity2 u4 = new UserEntity2();
//		u4.setId(3);
//		u4.delete();

	}

	@Test
	public void tes2(){
		UserEntity2 userEntity2 = new UserEntity2();
		QueryResult<UserEntity2> all = new QueryResult<>(1);
		all = userEntity2.pageQuery(all);
		System.out.println(all);
	}

	@Test
	public void test3(){
	}



}

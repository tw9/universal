package com.wrqzn.base.test;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.DatabaseType;
import org.junit.Test;



/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UserTest {

	static {

		DataSource dataSource = new DataSource("192.168.9.183",3306);
		dataSource.setDatabaseType(DatabaseType.mysql);
		dataSource.setUserName("root");
		dataSource.setPassword("password");
		dataSource.setSchema("testdb");


		ConfigData.addBaseDataSource(dataSource);

	}

	@Test
	public void test(){
//		List<Map<String,Object>> list = BaseQuery.run("user_password");
//		List<Map<String,Object>> list = BaseQuery.runSql("select id,password from user_password");
//		System.out.println(list.size());
		System.out.println(String.class.getName());
		System.out.println(Integer.class.getName());
		System.out.println(int.class.getName());
		System.out.println(Double.class.getName());
		System.out.println(Long.class.getName());
		System.out.println(double.class.getName());
		System.out.println(long.class.getName());

		System.out.println(java.sql.Date.class.getName());
		System.out.println(java.sql.Time.class.getName());
		System.out.println(java.sql.Timestamp.class.getName());

	}


	@Test
	public void fjdk(){
	}


}

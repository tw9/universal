package com.wrqzn.base.test;

import com.wrqzn.base.db.bean.DataSource;
import com.wrqzn.base.db.bean.DatabaseType;
import com.wrqzn.base.db.bean.DefaultData;
import com.wrqzn.base.db.biz.BaseQuery;
import org.junit.Test;

import java.util.List;
import java.util.Map;


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


		DefaultData.addBaseDataSource(dataSource);

	}

	@Test
	public void test(){
//		List<Map<String,Object>> list = BaseQuery.run("user_password");
		List<Map<String,Object>> list = BaseQuery.runSql("select id,password from user_password");
		System.out.println(list.size());
	}

}

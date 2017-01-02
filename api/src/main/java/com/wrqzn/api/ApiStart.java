package com.wrqzn.api;

import com.wrqzn.base.db.bean.DataSource;
import com.wrqzn.base.db.bean.DatabaseType;
import com.wrqzn.base.db.bean.DefaultData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
@SpringBootApplication
public class ApiStart {
	public static void main(String[] args) {


		DataSource dataSource = new DataSource("192.168.9.183",3306);
		dataSource.setDatabaseType(DatabaseType.mysql);
		dataSource.setUserName("root");
		dataSource.setPassword("password");
		dataSource.setSchema("testdb");


		DefaultData.addBaseDataSource(dataSource);

		SpringApplication.run(ApiStart.class,args);
	}
}

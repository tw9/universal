package com.wrqzn.api;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.DatabaseType;
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


		DataSource dataSource = new DataSource();
		dataSource.setDatabaseType(DatabaseType.mysql);
		dataSource.setHost("127.0.0.1");
		dataSource.setPort(3306);
		dataSource.setSchema("tw");
		dataSource.setUserName("root");
		dataSource.setPassword("adminroot");
		ConfigData.addBaseDataSource(dataSource);


		SpringApplication.run(ApiStart.class,args);
	}
}

package com.wrqzn.taskflow.db;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by WANG, RUIQING on 2/17/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBConfig {

	public static String jdbcUrl;
	public static String userName;
	public static String password;
	public static String driver;
//	jdbc.url=jdbc:mysql://localhost:3306/tw
//	jdbc.userName=root
//	jdbc.password=adminroot
//	jdbc.driver=com.mysql.jdbc.Driver

	static {
		DBConfig config = new DBConfig();
		config.init();
	}

	public void init(){
		Properties prop = new Properties();
//		InputStream input = null;
		try {
//			input = new FileInputStream(new File("taskflowdb.properties"));
			prop.load(this.getClass().getClassLoader().getResourceAsStream("taskflowdb.properties"));
			jdbcUrl = prop.getProperty("jdbc.url");
			userName = prop.getProperty("jdbc.userName");
			password = prop.getProperty("jdbc.password");
			driver = prop.getProperty("jdbc.driver");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

package com.wrqzn.dbrecord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 12/8/16
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class ConfigData {

	public static final String dbBase = "dbBase";
	public static final String dbBiz = "dbBiz";


//	public static DataSource dataSource = null;
	public static Integer defaultPageSize = 10;


	public static Map<DatabaseType,String> dbDriverMap = new HashMap<>();
	public static Map<DatabaseType,String> dbUrlHead = new HashMap<>();

	public static Map<String,DataSource> dataSourceMap = new HashMap<>();



	static {
		dbDriverMap.put(DatabaseType.mysql,"com.mysql.jdbc.Driver");
		dbUrlHead.put(DatabaseType.mysql,"jdbc:mysql://");
	}

	public static String getDriver(DatabaseType dbType){
		return dbDriverMap.get(dbType);
	}

	public static String getUrlHead(DatabaseType dbType){
		return dbUrlHead.get(dbType);
	}

//	public static DataSource getDataSource() {
//		return dataSource;
//	}
//
//	public static void setDataSource(DataSource dataSource) {
//		DefaultData.dataSource = dataSource;
//	}



	public static void addDataSource(String key , DataSource dataSource){
		dataSource.setConnectionPool();
		dataSourceMap.put(key,dataSource);
	}
	public static void addBaseDataSource(DataSource dataSource){
		dataSource.setConnectionPool();
		dataSourceMap.put(dbBase,dataSource);
	}

	public static void addBizDataSource(DataSource dataSource){
		dataSource.setConnectionPool();
		dataSourceMap.put(dbBiz,dataSource);
	}

	public static DataSource getBaseDataSource(){
		return dataSourceMap.get(dbBase);
	}
	public static DataSource getBizDataSource(){
		return dataSourceMap.get(dbBiz);
	}
	public static DataSource getDataSource(String key){
		return dataSourceMap.get(key);
	}



}

//package com.wrqzn.base.db.biz;
//
//
//import com.wrqzn.base.db.bean.DataSource;
//import com.wrqzn.base.db.bean.DatabaseType;
//import com.wrqzn.base.db.bean.DefaultData;
//import com.wrqzn.base.db.bean.QueryResult;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by WANG, RUIQING on 12/8/16
// * Twitter : @taylorwang789
// * E-mail : i@wrqzn.com
// */
//public class RunTest {
//
//	public static void main(String[] args) {
//
//		DataSource dataSource = new DataSource("192.168.9.183",3306);
//		dataSource.setDatabaseType(DatabaseType.mysql);
//		dataSource.setUserName("root");
//		dataSource.setPassword("password");
//		dataSource.setSchema("testdb");
//
//		Connection conn = null;
//		Statement stmt = null;
//
//		DefaultData.addBaseDataSource(dataSource);
//
//
//
//	}
//}

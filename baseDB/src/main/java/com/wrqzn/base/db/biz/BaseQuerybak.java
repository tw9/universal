//package com.wrqzn.base.db.biz;
//
//
//import com.wrqzn.base.db.bean.DataSource;
//import com.wrqzn.base.db.bean.DefaultData;
//import com.wrqzn.base.db.bean.QueryResult;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by WANG, RUIQING on 12/8/16
// * Twitter : @taylorwang789
// * E-mail : i@wrqzn.com
// */
//public class BaseQuerybak {
//
//
//
////	public static List<Map<String,Object>> runSql(String sql){
////		return run(DefaultData.getBaseDataSource(),sql);
////	}
////
////	public static List<Map<String,Object>> runSql(DataSource dataSource,String sql){
////		return run(dataSource,sql);
////	}
////
//	public static <T> QueryResult<T> run(String sql,T type){
//		return run(DefaultData.getBaseDataSource(),sql,type);
//	}
////
////	public static List<Map<String,Object>> run(String tableName,String condition){
////		return run(DefaultData.getBaseDataSource(),tableName,condition);
////	}
////
////	public static List<Map<String,Object>> run(DataSource dataSource,String tableName,String condition){
////		return runDb(dataSource,tableName,condition);
////	}
//
////
////	private static <T> QueryResult<T> runDb(DataSource dataSource,String tableName,String condition,T type){
////		StringBuffer sqlBuffer = new StringBuffer();
////		sqlBuffer.append(" select * from ");
////		sqlBuffer.append(tableName);
////		if (null != condition) {
////			String tempCondition = condition.toLowerCase().trim();
////			if (tempCondition.startsWith("where")) {
////				sqlBuffer.append(" "+ condition +" ");
////			} else if ( tempCondition.startsWith("and") || tempCondition.startsWith("or") ){
////				sqlBuffer.append(" where 1=1 ");
////				sqlBuffer.append(condition);
////			} else {
////				sqlBuffer.append(" where 1=1 and ");
////				sqlBuffer.append(condition);
////			}
////		}
////		return run(dataSource,sqlBuffer.toString(),type);
////	}
//
//
//	private static <T> QueryResult<T> run(DataSource dataSource, String sql , T type){
//		Connection conn = null;
//		Statement stmt = null;
//		List<Map<String,Object>> list = new ArrayList<>();
//		try {
//			Class.forName(dataSource.getDriver());
//			conn = DriverManager.getConnection(dataSource.getUrl(),dataSource.getUserName(),dataSource.getPassword());
//			conn.setAutoCommit(true);
//			stmt = conn.createStatement();
//			System.out.println(sql);
//			ResultSet resultSet = stmt.executeQuery(sql);
//			list = DBMethod.resultToList(resultSet);
//			resultSet.close();
//			stmt.close();
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		QueryResult<T>  queryResult = new QueryResult(list);
//		return queryResult;
//
//	}
//
//
//
//}

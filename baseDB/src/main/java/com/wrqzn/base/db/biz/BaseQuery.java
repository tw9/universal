package com.wrqzn.base.db.biz;


import com.wrqzn.base.db.bean.DataSource;
import com.wrqzn.base.db.bean.DefaultData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 12/8/16
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class BaseQuery {

	private static List<Map<String,Object>> query(DataSource dataSource, String sql){
//		DataSource dataSource = new DataSource("88.88.88.211",3306);
//		dataSource.setDatabaseType(DatabaseType.mysql);
//		dataSource.setUserName("root");
//		dataSource.setPassword("adminroot");
//		dataSource.setSchema("pay");
		Connection conn = null;
		Statement stmt = null;
		List<Map<String,Object>> list = new ArrayList<>();
		try {
			Class.forName(dataSource.getDriver());
			conn = DriverManager.getConnection(dataSource.getUrl(),dataSource.getUserName(),dataSource.getPassword());
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
//			String sql = "select  * from payment";
			System.out.println(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			list = DBMethod.resultToList(resultSet);
//			ResultSetMetaData md = resultSet.getMetaData();
//			int columns = md.getColumnCount();
//			while (resultSet.next()){
//				HashMap row = new HashMap(columns);
//				for(int i=1; i<=columns; ++i){
//					row.put(md.getColumnName(i),resultSet.getObject(i));
//				}
//				list.add(row);
//			}
			resultSet.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}



//	public static List<Map<String,Object>> runSql(String sql){
//		return run(DefaultData.getDataSource(),sql);
//	}
//
//	public static List<Map<String,Object>> runSql(DataSource dataSource,String sql){
//		return run(dataSource,sql);
//	}

	public static List<Map<String,Object>> run(String tableName){
		return run(DefaultData.getBaseDataSource(),tableName,null);
	}

	public static List<Map<String,Object>> runSql(String sql){
		return query(DefaultData.getBaseDataSource(),sql);
	}


	public static List<Map<String,Object>> run(String tableName,String condition){
		return run(DefaultData.getBaseDataSource(),tableName,condition);
	}

	public static List<Map<String,Object>> run(DataSource dataSource,String tableName,String condition){
		return runDb(dataSource,tableName,condition);
	}

//
	private static List<Map<String,Object>> runDb(DataSource dataSource,String tableName,String condition){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select * from ");
		sqlBuffer.append(tableName);
		if (null != condition) {
			String tempCondition = condition.toLowerCase().trim();
			if (tempCondition.startsWith("where")) {
				sqlBuffer.append(" "+ condition +" ");
			} else if ( tempCondition.startsWith("and") || tempCondition.startsWith("or") ){
				sqlBuffer.append(" where 1=1 ");
				sqlBuffer.append(condition);
			} else {
				sqlBuffer.append(" where 1=1 and ");
				sqlBuffer.append(condition);
			}
		}
		return query(dataSource,sqlBuffer.toString());
	}




}

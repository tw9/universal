package com.wrqzn.base.db.biz;


import com.wrqzn.base.db.bean.DataSource;
import com.wrqzn.base.db.bean.DefaultData;
import com.wrqzn.base.db.bean.QueryParam;

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

	private static List<Map<String,Object>> query(DataSource dataSource, String sql,List<Object> param){
//		DataSource dataSource = new DataSource("88.88.88.211",3306);
//		dataSource.setDatabaseType(DatabaseType.mysql);
//		dataSource.setUserName("root");
//		dataSource.setPassword("adminroot");
//		dataSource.setSchema("pay");
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Map<String,Object>> list = new ArrayList<>();
		try {
			Class.forName(dataSource.getDriver());
			conn = DriverManager.getConnection(dataSource.getUrl(),dataSource.getUserName(),dataSource.getPassword());
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement(sql);

//java.lang.String
//java.lang.Integer
//int
//java.lang.Double
//java.lang.Long
//double
//long
//java.sql.Date
//java.sql.Time
//java.sql.Timestamp

			if (null != param) {
				for (int i = 0; i < param.size() ; i++) {
					Object obj = param.get(i);

					switch (obj.getClass().getName()) {
						case "java.lang.String":
							stmt.setString(i+1,obj.toString());
							break;
						case "java.lang.Integer":
							stmt.setInt(i+1,Integer.valueOf(obj.toString()));
							break;
						case "int":
							stmt.setInt(i+1,Integer.valueOf(obj.toString()));
							break;

						default:
							stmt.setString(i+1,obj.toString());
							break;
					}

				}
			}


//			System.out.println(sql);
			System.out.println(stmt.toString());
			ResultSet resultSet = stmt.executeQuery();
			list = DBMethod.resultToList(resultSet);
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

//	public static List<Map<String,Object>> run(String tableName){
//		return run(DefaultData.getBaseDataSource(),tableName,null);
//	}

	public static List<Map<String,Object>> run(String sql){
		return query(DefaultData.getBaseDataSource(),sql,null);
	}

	public static List<Map<String,Object>> run(QueryParam queryParam){
		return query(DefaultData.getBaseDataSource(),queryParam.getSql(),queryParam.getParam());
	}



//	public static List<Map<String,Object>> run(String tableName,String condition){
//		return run(DefaultData.getBaseDataSource(),tableName,condition);
//	}

//	public static List<Map<String,Object>> run(DataSource dataSource,String tableName,String condition){
//		return runDb(dataSource,tableName,condition);
//	}
//
////
//	private static List<Map<String,Object>> runDb(DataSource dataSource,String tableName,String condition){
//		StringBuffer sqlBuffer = new StringBuffer();
//		sqlBuffer.append(" select * from ");
//		sqlBuffer.append(tableName);
//		if (null != condition) {
//			String tempCondition = condition.toLowerCase().trim();
//			if (tempCondition.startsWith("where")) {
//				sqlBuffer.append(" "+ condition +" ");
//			} else if ( tempCondition.startsWith("and") || tempCondition.startsWith("or") ){
//				sqlBuffer.append(" where 1=1 ");
//				sqlBuffer.append(condition);
//			} else {
//				sqlBuffer.append(" where 1=1 and ");
//				sqlBuffer.append(condition);
//			}
//		}
//		return query(dataSource,sqlBuffer.toString());
//	}




}

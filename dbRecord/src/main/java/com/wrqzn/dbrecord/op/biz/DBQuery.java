package com.wrqzn.dbrecord.op.biz;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.Select;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBQuery {


	public static  <T> List<T> run(Select select,Class<T> clz){
		List<T> content = query(select,clz);
		return content;
	}

//	public static   List<Map<String,Object>> run(Select select){
//		List<Map<String,Object>> content = new ArrayList<>();
//		Map<String,Object> u1 = new HashMap<>();
//		u1.put("key","hello");
//		u1.put("time","323232");
//		content.add(u1);
//
//		return content;
//	}

	private static <T> List<T> query(Select select, Class<T> clz){
		DataSource dataSource = select.getDataSource();
		String sql = select.getSql() ;
		List<Object> param = select.getParameters();

		List<T> content = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
//			Class.forName(dataSource.getDriver());
//			conn = DriverManager.getConnection(dataSource.getUrl(),dataSource.getUserName(),dataSource.getPassword());
			conn = dataSource.getConnection();
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement(sql);
//java.lang.String //java.lang.Integer //int //java.lang.Double //java.lang.Long //double //long //java.sql.Date //java.sql.Time //java.sql.Timestamp
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
			System.out.println(stmt.toString());
			resultSet = stmt.executeQuery();

			if ( Map.class != select.getType()) {
				try {
					T object = clz.newInstance();
					Method method = clz.getDeclaredMethod("formatResult",ResultSet.class);
					content = (List<T>) method.invoke(object,resultSet);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			} else {
				content = new ArrayList();
				ResultSetMetaData md = null;
				try {
					md = resultSet.getMetaData();
					int columns = md.getColumnCount();
					while (resultSet.next()){
						HashMap row = new HashMap(columns);
						for(int i=1; i<=columns; ++i){
							row.put(md.getColumnName(i),resultSet.getObject(i));
						}
						content.add((T) row);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//			resultSet.close();
//			stmt.close();
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return content;
		}

	}



}

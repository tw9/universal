package com.wrqzn.dbrecord.op.biz;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.QueryResult;
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
public class DBPageQuery {


	public  static <T> QueryResult query(Select select, Class<T> clz , QueryResult result,String cntSql,String dtSql){
		DataSource dataSource = select.getDataSource();
		List<Object> param = select.getParameters();
		List<T> content = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement cntstmt = null;
		ResultSet cntresultSet = null;
		ResultSet resultSet = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(true);
			cntstmt = conn.prepareStatement(cntSql);
			stmt = conn.prepareStatement(dtSql);
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

			cntresultSet = stmt.executeQuery();
			cntresultSet.next();
			result.setTotalCount( resultSet.getInt("cnt") );


			if ( Map.class != select.getEntityType()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return result;
		}

	}



}

package com.wrqzn.dbrecord.op.biz;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.DBOperate;

import java.sql.*;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBInsert {


//	public static  <T> T run(Insert<T> insert, Class<T> clz){
//		return null;
//	}

	public  static int insert(DBOperate insert){
		DataSource dataSource = insert.getDataSource();
		String sql = insert.getSql() ;
		List<Object> param = insert.getParameters();

		int content = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(true);
//			stmt = conn.prepareStatement(sql);
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
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

			stmt.executeUpdate();
			ResultSet result = stmt.getGeneratedKeys();
			result.next();
			content = result.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return content;
		}

	}



}

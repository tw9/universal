package com.wrqzn.dbrecord.op.biz;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.DBOperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBDelete {


//	public static  <T> T run(Insert<T> insert, Class<T> clz){
//		return null;
//	}

	public  static int delete(DBOperate delete){
		DataSource dataSource = delete.getDataSource();
		String sql = delete.getSql() ;
		List<Object> param = delete.getParameters();

		int content = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement(sql);
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return content;
		}

	}



}

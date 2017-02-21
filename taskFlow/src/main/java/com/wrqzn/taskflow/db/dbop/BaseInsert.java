package com.wrqzn.taskflow.db.dbop;



import com.wrqzn.taskflow.db.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 12/8/16
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class BaseInsert {



    public static Integer run( String tableName, List<Map<String, Object>> data) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Integer autoId = null;
        try {
            Class.forName(DBConfig.driver);
            conn = DriverManager.getConnection(DBConfig.jdbcUrl, DBConfig.userName,DBConfig.password);
            conn.setAutoCommit(true);

            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("insert into  ");
            sqlBuffer.append(tableName);
            sqlBuffer.append(" ( ");
            List<String> keys = new ArrayList<>();
            boolean flag = true;
            for (Map.Entry<String, Object> entry : data.get(0).entrySet()) {
                if (flag) {
                    sqlBuffer.append(entry.getKey());
                    flag = false;
                } else {
                    sqlBuffer.append("," + entry.getKey());
                }
                keys.add(entry.getKey());
            }
            sqlBuffer.append(" ) values  ");

			for (int i = 0; i < data.size() ; i++) {
				if (i !=0 ){
					sqlBuffer.append(",");
				}
				sqlBuffer.append("(");

				for (int j = 0; j < keys.size(); j++) {
                	Object obj = data.get(i).get(keys.get(j));
					if (j == 0) {
                    	sqlBuffer.append(getDataSql(obj));
					} else {
                    	sqlBuffer.append(" , " + getDataSql(obj));
					}
				}
				sqlBuffer.append(" ) ");
			}


            System.out.println(sqlBuffer.toString());
            stmt = conn.prepareStatement(sqlBuffer.toString());
            autoId = stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autoId;

    }


	private static String getDataSql(Object obj) {
		String dataSql = "";
		switch (obj.getClass().toString()) {
			case "class java.lang.String":
				dataSql = "'" + obj.toString() + "'";
				break;
			case "class java.lang.Integer":
				dataSql = obj.toString();
			case "int":
				dataSql = obj.toString();
				break;
			case "class java.util.Date":
				dataSql = obj.toString();
				break;
		}
		return dataSql;
	}






}

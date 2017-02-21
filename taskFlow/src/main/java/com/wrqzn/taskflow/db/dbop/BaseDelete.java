package com.wrqzn.taskflow.db.dbop;



import com.wrqzn.taskflow.db.DBConfig;

import java.sql.*;

/**
 * Created by WANG, RUIQING on 12/8/16
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class BaseDelete {



    public static void run( String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(DBConfig.driver);
            conn = DriverManager.getConnection(DBConfig.jdbcUrl, DBConfig.userName,DBConfig.password);
            conn.setAutoCommit(true);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}

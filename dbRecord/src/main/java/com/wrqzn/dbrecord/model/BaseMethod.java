package com.wrqzn.dbrecord.model;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.impl.SelectMySql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/11/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class BaseMethod {

	public List<Map<String,Object>> resultToList(ResultSet resultSet) {
		List<Map<String,Object>> list = new ArrayList<>();
		ResultSetMetaData md = null;
		try {
			md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			while (resultSet.next()){
				HashMap row = new HashMap(columns);
				for(int i=1; i<=columns; ++i){
					row.put(md.getColumnName(i),resultSet.getObject(i));
				}
				list.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public <T> Select<T> getSelectInstance(Class<T> clz, DataSource dataSource){
		Select<T> select = null ;
		switch (dataSource.getDatabaseType()) {
			case mysql:
				select = new SelectMySql<>(clz,dataSource);
				break;

		}
		return select;
	}
}

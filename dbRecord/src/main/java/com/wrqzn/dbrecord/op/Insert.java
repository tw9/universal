package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.biz.DBInsert;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Insert<T> extends DBOperate {
	protected Class<T> type;


	public Insert(Class<T> type) {
		this.type= type;
	}
	public Insert(Class<T> type, DataSource dataSource){
		this.dataSource = dataSource;
		this.type= type;
	}

	public Insert(DataSource dataSource){
		this.dataSource = dataSource;
		type = (Class<T>) Map.class;
	}

	public Insert() {
		type = (Class<T>) Map.class;
	}


	public <T> T  save(T t){
		return null;
	}

	public Map<String,Object> save(String tableName , Map<String,Object> mapData) {
		addSql("insert into");
		addSql(tableName);
		addSql("(");


		DBInsert.run(this, (Class<T>) Map.class);

		return mapData;

	}


}

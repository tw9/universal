package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.biz.DBInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Insert<T> extends DBOperate {

	public Insert(Class<T> type) {
		this.entityType = type;
	}
	public Insert(Class<T> type, DataSource dataSource){
		this.dataSource = dataSource;
		this.entityType = type;
	}

	public Insert(DataSource dataSource){
		this.dataSource = dataSource;
		entityType = (Class<T>) Map.class;
	}

	public Insert() {
		entityType = (Class<T>) Map.class;
	}

	@Override
	public QueryResult run() {
		QueryResult queryResult = new QueryResult();
		DBInsert.insert(this);
		return queryResult;
	}


	public int insert(String table,Map<String,Object> data){
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		data.forEach( (k,v) -> {
				if ( null != v ) {
					keys.add(k);
					values.add(v);
				}
				});

		this.addSql( "insert into " + table );
		this.addSql("(");
		for (int i = 0; i < keys.size() ; i++) {
			if (i !=0 ) {
				this.addSql(",");
			}
			this.addSql(keys.get(i));
		}
		this.addSql(") values (");

		for (int i = 0; i < values.size(); i++) {
			if ( i != 0 ) {
				this.addSql(",");
			}
			this.addParameter(values.get(i));
		}
		this.addSql(")");


		int autoId = DBInsert.insert(this);
		return autoId;
	}

}

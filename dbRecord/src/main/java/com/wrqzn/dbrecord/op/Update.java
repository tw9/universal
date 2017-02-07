package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.biz.DBUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Update<T> extends DBOperate {

	public Update(Class<T> type) {
		this.entityType = type;
	}
	public Update(Class<T> type, DataSource dataSource){
		this.dataSource = dataSource;
		this.entityType = type;
	}

	public Update(DataSource dataSource){
		this.dataSource = dataSource;
		entityType = (Class<T>) Map.class;
	}

	public Update() {
		entityType = (Class<T>) Map.class;
	}

	@Override
	public QueryResult run() {
		QueryResult queryResult = new QueryResult();
		DBUpdate.update(this);
		return queryResult;
	}


	public  void update(String table ,Map<String,Object> data,String whereSql) {
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		data.forEach( (k,v) -> {
				if ( null != v ) {
					keys.add(k);
					values.add(v);
				}
				});

		this.addSql(" update " + table );
		this.addSql(" set ");
		for (int i = 0; i < keys.size() ; i++) {
			if (null != values.get(i)) {
				if (i != 0 ) {
					this.addSql(",");
				}
				this.addSql( keys.get(i) + " = ");
				this.addParameter(values.get(i));
			}
		}
		this.addSql(whereSql);

		DBUpdate.update(this);
	}

}

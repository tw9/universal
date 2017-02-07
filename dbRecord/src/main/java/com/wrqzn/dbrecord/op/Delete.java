package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.DBEntity;
import com.wrqzn.dbrecord.op.biz.DBDelete;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Delete<T> extends DBOperate<T> {

	public Delete(Class<T> type) {
		this.entityType = type;
	}
	public Delete(Class<T> type, DataSource dataSource){
		this.dataSource = dataSource;
		this.entityType = type;
	}

	public Delete(DataSource dataSource){
		this.dataSource = dataSource;
		entityType = (Class<T>) Map.class;
	}

	public Delete() {
		entityType = (Class<T>) Map.class;
	}

	@Override
	public QueryResult run() {
		QueryResult queryResult = new QueryResult();
		DBDelete.delete(this);
		return queryResult;
	}


	public void delete(T t){
		DBEntity entity = (DBEntity) t;
		this.addSql( " delete from " + entity.getTableName());
		this.addSql(" where id = ");
		this.addParameter( entity.getId() );
		DBDelete.delete(this);
	}

}

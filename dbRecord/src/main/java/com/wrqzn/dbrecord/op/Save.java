package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.DBEntity;
import com.wrqzn.dbrecord.op.biz.DBInsert;
import com.wrqzn.dbrecord.op.biz.DBUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Save<T> extends DBOperate {

	public Save(Class<T> type) {
		this.entityType = type;
	}
	public Save(Class<T> type, DataSource dataSource){
		this.dataSource = dataSource;
		this.entityType = type;
	}

	public Save(DataSource dataSource){
		this.dataSource = dataSource;
		entityType = (Class<T>) Map.class;
	}

	public Save() {
		entityType = (Class<T>) Map.class;
	}

	@Override
	public QueryResult run() {
		QueryResult queryResult = new QueryResult();
		DBUpdate.update(this);
		return queryResult;
	}


	public <T> T save(T t){
		DBEntity entity = (DBEntity) t;
		if ( null == entity.getId()) {
			int autoId = insert(entity);
			((DBEntity) t).setId(autoId);
		} else {
			update(entity);
		}
		return t;
	}

	private void update(DBEntity entity) {
		Map<String,Object>  entityData = entity.getFieldData();
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		entityData.forEach( (k,v) -> {
				if ( null != v ) {
					keys.add(k);
					values.add(v);
				}
				});

		this.addSql(" update " + entity.getTableName() );
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
		this.addSql("where id = ");
		this.addParameter(entity.getId());

		DBUpdate.update(this);

	}

	private int insert(DBEntity entity){
		Map<String,Object>  entityData = entity.getFieldData();
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		entityData.forEach( (k,v) -> {
				if ( null != v ) {
					keys.add(k);
					values.add(v);
				}
				});

		this.addSql( "insert into " + entity.getTableName() );
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

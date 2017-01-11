package com.wrqzn.dbrecord.model;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.QueryResult;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.biz.SelectFactory;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class BaseEntity<T> extends BaseMethod {

	protected T id;

//	protected transient Select select = new SelectMySql<>(this.getClass(),getDataSource());
	protected transient Select select = SelectFactory.getSelect(this.getClass(),getDataSource());

	public abstract DataSource getDataSource();
	public abstract String getTableName();
	public abstract List<String> selectAllFields();
	public abstract List<?> formatResult(ResultSet resultSet);



	public QueryResult findAll(){
		buildSql();
		return select.query();
	}

	public QueryResult findOne(T primaryKey){
		buildSql();
		select.addSql("where id = ? ");
		select.addParameter(primaryKey);
		return select.query();
	}

	public void buildSql(){
		select.addSql("select");
		List<String> fields = selectAllFields();
		if (null == fields ) {
			select.addSql("*");
		} else {
			for (int i = 0; i < fields.size() ; i++) {
				if (i != 0 ) {
					select.addSql(",");
				}
				select.addSql(fields.get(i));
			}
		}
		select.addSql("from");
		select.addSql(getTableName());
	}


	public void addSql(String sqlSegment){
		select.addSql(sqlSegment);
	}

	public void addParameter(Object object){
		select.addParameter(object);
	}

	public QueryResult query(){
		return select.query();
	}



	// getter setter
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}

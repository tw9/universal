package com.wrqzn.dbrecord.model;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.*;
import com.wrqzn.dbrecord.op.biz.SelectFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class DBEntity<T> extends BaseMethod {

	protected T id;


	protected Select select = SelectFactory.getSelect(this.getClass(),getDataSource());
	protected Save save = new Save(this.getClass(),getDataSource());
	protected Delete delete= new Delete(this.getClass(),getDataSource());

	public abstract DataSource getDataSource();
	public abstract String getTableName();
	public abstract Map<String,Object> getFieldData();
	public abstract List<?> formatResult(ResultSet resultSet);





	public QueryResult findAll(){
		buildSql();
		return select.query();
	}

	public QueryResult pageQuery(QueryResult queryResult){
		buildSql();
		return select.query(queryResult);
	}


	public QueryResult findOne(T primaryKey){
		buildSql();
		select.addSql("where id = ");
		select.addParameter(primaryKey);
		return select.query();
	}

	private void buildSql(){
		select.addSql("select");
//		Map<String,Object> data = getFieldData();
//		List<String> fields = new ArrayList<>();
//		data.forEach( (k,v) -> {fields.add(k);});
//		if (null == fields ) {
//			select.addSql("*");
//		} else {
//			for (int i = 0; i < fields.size() ; i++) {
//				if (i != 0 ) {
//					select.addSql(",");
//				}
//				select.addSql(fields.get(i));
//			}
//		}
		select.addSql(getAllField());
		select.addSql("from");
		select.addSql(getTableName());
	}

	public String getAllField(){
		Map<String,Object> data = getFieldData();
		List<String> fields = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		data.forEach( (k,v) -> {fields.add(k);});
		if (null == fields ) {
			stringBuilder.append("*");
		} else {
			for (int i = 0; i < fields.size() ; i++) {
				if (i != 0 ) {
					stringBuilder.append(",");
				}
				stringBuilder.append(fields.get(i));
			}
		}
		return " " + stringBuilder.toString() + " ";
	}


	public void addSql(String sqlSegment){
		select.addSql(sqlSegment);
	}

	public void addParameter(Object object){
		select.addParameter(object);
	}
	public void addData(Object arg){
		select.addData(arg);
	}


//	public QueryResult query(){
//		return select.query();
//	}

// insert
	public void save(){
		save.save(this);
	}
	public void delete(){
		delete.delete(this);
	}


	// getter setter
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}

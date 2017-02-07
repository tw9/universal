package com.wrqzn.dbrecord.op;

import com.wrqzn.dbrecord.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class DBOperate<T> {

	protected DataSource dataSource;
	protected StringBuilder sqlBuilder;
	protected String sql;
	protected List<Object> parameters;
	protected Class<T> entityType;

	public DBOperate() {
		this.sqlBuilder = new StringBuilder();
		this.parameters = new ArrayList<>();
	}



	public void addSql(String sqlSegment){
		sqlBuilder.append(" "+sqlSegment+" ");
	}
	public void addParameter(Object arg){
		sqlBuilder.append("?");
		parameters.add(arg);
	}

	public String getSql() {
		if ( null == sql && null != sqlBuilder ) {
			sql = sqlBuilder.toString();
		}
		return sql;
	}

	public void resetSql(){
		sqlBuilder.delete(0,sqlBuilder.length());
		sql = null;
		parameters.clear();
	}

	public abstract QueryResult run();



	public void setSql(String sql) {
		this.sql = sql;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public StringBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(StringBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}

	public Class<T> getEntityType() {
		return entityType;
	}

	public void setEntityType(Class<T> entityType) {
		this.entityType = entityType;
	}
}

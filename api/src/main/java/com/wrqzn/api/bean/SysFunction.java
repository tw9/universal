package com.wrqzn.api.bean;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.DBEntity;
import com.wrqzn.dbrecord.op.QueryResult;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 2/7/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class SysFunction extends DBEntity<Integer> {

	private String name;
	private String path;
	private Integer pathLength;
	private Integer method;
	private String sql;
	private String parameter;
	private Integer pathVariable; // 1:路径有值, 0:路径上无值


	@Override
	public DataSource getDataSource() {
		return ConfigData.getBaseDataSource();
	}

	@Override
	public String getTableName() {
		return "sys_function";
	}

	@Override
	public Map<String, Object> getFieldData() {
		Map<String,Object> map = new HashMap<>();
		map.put("name",getName());
		map.put("path",getPath());
		map.put("path_length",getPathLength());
		map.put("method",getMethod());
		map.put("sql",getSql());
		map.put("parameter",getParameter());
		map.put("path_variable",getPathVariable());
		return map;
	}

	@Override
	public List<?> formatResult(ResultSet resultSet) {
		return null;
	}


	public SysFunction findByPath(String[] path,HttpMethod httpMethod){
		int inputPathLength = path.length;
		select.addSql("select");
		select.addSql(getAllField());
		select.addSql("from" + getTableName());
		select.addSql("where path_length = " + inputPathLength);
		select.addSql("and method = " + httpMethod.getValue() );
		select.addSql("and path = '"+path+"' ");
		QueryResult<SysFunction> functionResult = select.query();
		if (functionResult.isNull()) {
			select.resetSql();
			select.addSql("select");
			select.addSql(getAllField());
			select.addSql("from" + getTableName());
			select.addSql("where path_length = " + inputPathLength);
			select.addSql("and method = " + httpMethod.getValue() );
			select.addSql("and path_variable = 1 ");
			functionResult = select.query();
		}
		if (functionResult.isNull()){
			return null;
		} else {

//			String[] paths = path.split("/");
			for (int i = 0; i < functionResult.getContent().size() ; i++) {
				String[] pathQuery = functionResult.getContent().get(i).getPath().split("/");
				for (int j = 0; j < inputPathLength ; j++) {
					if (j+1 == inputPathLength){
						if ( pathQuery[j].equals(path[j]) ) {
							return functionResult.getContent().get(i);
						}
					} else if (pathQuery[j].startsWith("#")){
						continue;
					} else if (! pathQuery[j].equals(path[j])) {
						break;
					}
				}
			}

			return null;
		}

	}





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Integer getPathLength() {
		return pathLength;
	}

	public void setPathLength(Integer pathLength) {
		this.pathLength = pathLength;
	}

	public Integer getPathVariable() {
		return pathVariable;
	}

	public void setPathVariable(Integer pathVariable) {
		this.pathVariable = pathVariable;
	}
}

package com.wrqzn.api.bean;

import com.wrqzn.dbrecord.ConfigData;
import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.model.DBEntity;
import com.wrqzn.dbrecord.op.QueryResult;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.biz.SelectFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
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
	private String functionSql;
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
		map.put("function_sql",getFunctionSql());
		map.put("parameter",getParameter());
		map.put("path_variable",getPathVariable());
		return map;
	}

	@Override
	public List<?> formatResult(ResultSet resultSet) {
		List<SysFunction> data = new ArrayList<>();
		List<Map<String,Object>> mapData = resultToList(resultSet);
		mapData.forEach( d -> {
			SysFunction entity= new SysFunction();
			entity.setId((Integer) d.get("id"));
			entity.setName((String) d.get("name"));
			entity.setPath((String) d.get("path"));
			entity.setPathLength((Integer) d.get("path_length"));
			entity.setMethod((Integer) d.get("method"));
			entity.setFunctionSql((String) d.get("function_sql"));
			entity.setParameter((String) d.get("parameter"));
			entity.setPathVariable((Integer) d.get("path_variable"));
			data.add(entity);
		});
		return data;
	}

	public QueryResult exeSql(Map<String, Object> param) {
		Select exeSelect = SelectFactory.getSelect(getDataSource());
		exeSelect.addSql(functionSql);
		if (null != parameter) {
			String[] parameters = parameter.split(",");
			for (int i = 0; i < parameters.length ; i++) {
				exeSelect.addData(param.get(parameters[i]));
			}
		}
		Integer currentPage = (Integer) param.get("currentPage");
		Integer pageSize = (Integer) param.get("pageSize");
		QueryResult result = new QueryResult(currentPage,pageSize);
		result = exeSelect.query(result);
		return result;
	}

	public SysFunction findByPath(String[] path,HttpMethod httpMethod){
		int inputPathLength = path.length;
		String pathUnion = "";
		for (int i = 0; i < path.length ; i++) {
			pathUnion += "/"+path[i];
		}
		select.addSql("select");
		select.addSql(getAllField());
		select.addSql("from " + getTableName());
		select.addSql("where path_length = " + inputPathLength);
		select.addSql("and method = " + httpMethod.getValue() );
		select.addSql("and path = '"+pathUnion+"' ");
		QueryResult<SysFunction> functionResult = select.query();
		if (functionResult.isNull()) {
			select.resetSql();
			select.addSql("select");
			select.addSql(getAllField());
			select.addSql("from " + getTableName());
			select.addSql("where path_length = " + inputPathLength);
			select.addSql("and method = " + httpMethod.getValue() );
			select.addSql("and path_variable = 1 ");
			functionResult = select.query();

			if (functionResult.isNull()){
				return null;
			} else {

//				String[] paths = path.split("/");
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
		return functionResult.getContent().get(0);

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

	public String getFunctionSql() {
		return functionSql;
	}

	public void setFunctionSql(String functionSql) {
		this.functionSql = functionSql;
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

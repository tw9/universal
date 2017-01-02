package com.wrqzn.base.permission.base;

import com.wrqzn.base.db.bean.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public interface BaseBiz<T> {

	QueryResult findAll();
	Object  findOne(T id);
	T save(Map<String,Object> param);
	void save(List<Map<String,Object>> params);
	void delete(Map<String,Object> param);


}

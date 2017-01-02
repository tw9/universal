package com.wrqzn.base.db.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/2/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class QueryResult {

	private List<Map<String,Object>> data ;
	private int count;

	public QueryResult() {
		this.data = new ArrayList<>();
		this.count = 0;
	}

	public QueryResult(List<Map<String, Object>> data) {
		if (null != data) {
			this.data = data;
			this.count = data.size();
		} else {
			this.data = new ArrayList<>();
			this.count = 0;
		}
	}

	public Object get(int index,String key){
		return  data.get(index).get(key);
	}

	public Map<String,Object> get(int index){
		return data.get(index);
	}

	public Object getFirst(String key){
		return  data.get(0).get(key);
	}

	public Map<String,Object> getFirst(){
		return  data.get(0);
	}

	public Object getLast(String key){
		return  data.get(count-1).get(key);
	}

	public Map<String,Object> getLast(){
		return  data.get(count-1);
	}


	public boolean notNull(){
		if ( null != data && count > 0 ){
			return  true;
		}else {
			return false;
		}
	}


	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int size() {
		return count;
	}
}

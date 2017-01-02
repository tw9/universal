//package com.wrqzn.base.db.bean;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by WANG, RUIQING on 1/2/17
// * Twitter : @taylorwang789
// * E-mail : i@wrqzn.com
// */
//public class QueryResult<T> {
//
//	private List<T> data ;
//	private int count;
//
//	public QueryResult() {
//	}
//
//	public QueryResult(List<T> data) {
//		if (null != data) {
//			this.data = data;
//			this.count = data.size();
//		} else {
//			this.data = new ArrayList<>();
//			this.count = 0;
//		}
//
//	}
//
//
//
//	public T getFirst(){
//		return data.get(0);
//	}
//
//	public T getSecond(){
//		return data.get(1);
//	}
//	public T getThird(){
//		return data.get(2);
//	}
//	public T getLast(){
//		return data.get(data.size()-1);
//	}
//
//
//	//
//
//
//	public List<T> getData() {
//		return data;
//	}
//
//	public void setData(List<T> data) {
//		if (null != data) {
//			this.data = data;
//			this.count = data.size();
//		} else {
//			this.data = new ArrayList<>();
//			this.count = 0;
//		}
//	}
//
//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}
//}

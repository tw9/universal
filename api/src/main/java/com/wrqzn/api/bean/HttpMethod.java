package com.wrqzn.api.bean;

/**
 * Created by WANG, RUIQING on 2/7/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public enum HttpMethod {
	get(0)
	,post(1)
	,put(2)
	,delete(3);

	private  final  int index ;
	HttpMethod(int i) {
		index = i ;
	}

	public int getValue(){
		return index;
	}



}

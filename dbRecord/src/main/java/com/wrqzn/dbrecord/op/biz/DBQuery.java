package com.wrqzn.dbrecord.op.biz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG, RUIQING on 1/10/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class DBQuery {


	public static  <T> List<T> run(Class<T> clz){
		List<T> content = new ArrayList<T>();
		try {
			content.add(clz.newInstance());
			content.add(clz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return content;
	}

}

package com.wrqzn.dbrecord.op.biz;

import com.wrqzn.dbrecord.DataSource;
import com.wrqzn.dbrecord.op.Select;
import com.wrqzn.dbrecord.op.impl.SelectMySql;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/11/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class SelectFactory {


	public static  Select getSelect(DataSource dataSource){
		Select select = null;
		switch (dataSource.getDatabaseType()){
			case mysql:
				select = new SelectMySql(Map.class,dataSource);
				break;
		}
		return select;
	}

	public static  <T> Select<T> getSelect(Class<T> clz,DataSource dataSource){
		Select select = null;
		switch (dataSource.getDatabaseType()){
			case mysql:
				select = new SelectMySql<>(clz,dataSource);
				break;
		}
		return select;
	}

}

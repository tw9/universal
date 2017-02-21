package com.wrqzn.dbrecord.test.test2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG, RUIQING on 2/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Test3 {

	@Test
	public void test(){
		System.out.println(hello());
	}


	public  void hello(List a ){
		System.out.println("hello List");
	}

	public  void hello(ArrayList a ){
		System.out.println("hello ArrayList");
	}

	public Integer hello(){
		Integer a = 0;
		try {
			a = 1;
			return a;
		} finally {
			a= 2;
			return a;
		}
	}

}

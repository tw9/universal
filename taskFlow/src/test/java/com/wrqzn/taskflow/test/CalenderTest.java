package com.wrqzn.taskflow.test;

import org.junit.Test;

import java.util.Calendar;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class CalenderTest {

	@Test
	public void test(){
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.DAY_OF_MONTH,29);
		System.out.println("888888888888888888888888888888888");
		System.out.println(calendar.getTime());

		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH) + 1 );
		System.out.println("23333333333333333333");
		System.out.println(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH) + 1 );
		System.out.println("23333333333333333333");
		System.out.println(calendar.getTime());

	}

	@Test
	public void test1(){

		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.clear();
		System.out.println(calendar.getTime());
		calendar.set(Calendar.YEAR,2016);
		System.out.println(calendar.getTime());
	}
}

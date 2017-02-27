package com.wrqzn.taskflow.test;

import com.wrqzn.taskflow.tasks.Email;
import com.wrqzn.taskflow.tasks.Phone;
import com.wrqzn.taskflow.tasks.SMS;
import com.wrqzn.taskflow.works.Task;
import com.wrqzn.taskflow.works.TaskFlow;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class UnionTest {

//		Task mail = new Email();
//		Map<String,Object> param = new HashMap<>();
//		param.put("userName","tw");
//		mail.setArgs(param);
//		Task sms = new SMS();
//		Task phone = new Phone();
//		TaskFlow taskFlow  = new TaskFlow("test   ", Arrays.asList(mail,sms,phone));
//		Thread mainT = new Thread(taskFlow);
//		mainT.start();
//		System.out.println("mainend");


//		Task amail = null ;
//
//		try {
//			Class<?> dd = Class.forName("com.wrqzn.taskflow.tasks.Email");
//			Class<?>[] params = {};
//			Constructor  constructor = dd.getDeclaredConstructor(params);
//			amail = (Task) constructor.newInstance();
//			amail = (Task) dd.newInstance();
//			System.out.println(amail.success);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		}
//
//		Map<String,Object> parama = new HashMap<>();
//		parama.put("userName","abc");
//		amail.setArgs(parama);
//		Task asms = new SMS();
//		Task aphone = new Phone();
//		TaskFlow ataskFlow  = new TaskFlow("bb   ", Arrays.asList(amail,asms,aphone));
//		Thread thread = new Thread(ataskFlow);
//		thread.start();

//		System.out.println("mainend");
//


	@Test
	public  void evalTest(){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");

		String func = " if( false ) { 3354;} else { 1111 ;} ";
		try {
			Integer res = (Integer) engine.eval(func);
			System.out.println( res );
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void stringTest(){
		String func = " if( 9 == $afdd$ ) { true;} else { false  ;} ";
		Map<String,String> datas = new HashMap<>();
		datas.put("afdd","7");

		String pattern = "\\$\\w*\\$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(func);

		while (m.find()) {
			String strm = m.group();
			String str = strm.substring(1,strm.length()-1);
			System.out.println(str);
			if ( null != datas.get(str)) {
				func = func.replace(strm,datas.get(str));
			}
		}

		System.out.println( func );

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");

		try {
			boolean flag = (boolean) engine.eval(func);
			System.out.println("flag :" + flag);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}


	@Test
	public void testeee(){
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
	}





}

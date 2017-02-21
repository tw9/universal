package com.wrqzn.taskflow;

import com.wrqzn.taskflow.tasks.Email;
import com.wrqzn.taskflow.tasks.Phone;
import com.wrqzn.taskflow.tasks.SMS;
import com.wrqzn.taskflow.works.Task;
import com.wrqzn.taskflow.works.TaskFlow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Test {
	public static void main(String[] args) {

		Task mail = new Email();
		Map<String,Object> param = new HashMap<>();
		param.put("userName","tw");
		mail.setArgs(param);
		Task sms = new SMS();
		Task phone = new Phone();
		TaskFlow taskFlow  = new TaskFlow("test   ", Arrays.asList(mail,sms,phone));
		Thread mainT = new Thread(taskFlow);
		mainT.start();
		System.out.println("mainend");


//		Task amail = null ;
//
//		try {
//			Class<?> dd = Class.forName("com.wrqzn.taskflow.tasks.Email");
////			Class<?>[] params = {};
////			Constructor  constructor = dd.getDeclaredConstructor(params);
////			amail = (Task) constructor.newInstance();
//			amail = (Task) dd.newInstance();
////			System.out.println(amail.success);
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
//
//		System.out.println("mainend");
//



	}
}

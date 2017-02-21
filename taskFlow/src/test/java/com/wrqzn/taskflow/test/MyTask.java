package com.wrqzn.taskflow.test;

import java.util.TimerTask;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class MyTask extends TimerTask{

	private String name;

	public MyTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(name+"run timer task " + System.currentTimeMillis());
		System.gc();
	}
}

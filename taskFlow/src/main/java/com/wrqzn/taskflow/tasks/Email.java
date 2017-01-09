package com.wrqzn.taskflow.tasks;

import com.wrqzn.taskflow.Task;



/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Email extends Task {
	@Override
	public void run() {
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result = args;
		result.put("email","send eamil " + System.currentTimeMillis());
		System.out.println("send Email");
		success = true;
		taskFlow.afterTask(getIndex());
	}
}

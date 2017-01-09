package com.wrqzn.taskflow.tasks;

import com.wrqzn.taskflow.Task;


/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Phone extends Task {
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result = args;
		result.put("phone","make phone call " + System.currentTimeMillis());
		System.out.println("make phone call");
		success = true;
		taskFlow.afterTask(getIndex());
	}
}

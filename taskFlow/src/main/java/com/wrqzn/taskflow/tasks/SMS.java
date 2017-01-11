package com.wrqzn.taskflow.tasks;

import com.wrqzn.taskflow.Task;


/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class SMS extends Task {
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result = args;
		result.put("SMS","send sms " + System.currentTimeMillis());
		System.out.println( taskFlow.getFlowName() + "send sms");

		success = true;
		taskFlow.afterTask(getIndex());
	}

	@Override
	public void rollback() {

		System.out.println( taskFlow.getFlowName() + "rollback sms");
		rollback = true;
		taskFlow.rollback(getIndex());
	}
}

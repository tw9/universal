package com.wrqzn.taskflow.tasks;

import com.wrqzn.taskflow.works.Task;


/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class SMS extends Task {
	@Override
	public void run() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		result = args;
		System.out.println(System.currentTimeMillis()+" send sms " + args.get("msg"));
	}

	@Override
	public void rollback() {

	}
}

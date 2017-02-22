package com.wrqzn.taskflow.tasks;

import com.wrqzn.taskflow.works.Task;



/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Email extends Task {
	@Override
	public void run() {
		result = args;

		System.out.println( System.currentTimeMillis()+" send eamil to " + args.get("address") +" after call " + args.get("lastphone"));
	}

	@Override
	public void rollback() {

	}
}

package com.wrqzn.taskflow.tasks;

import com.wrqzn.taskflow.works.Task;


/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class Phone extends Task {
	@Override
	public void run() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		result = args;
		System.out.println( System.currentTimeMillis() + " make phone call to  " + args.get("number") );
//		System.out.println( taskFlow.getFlowName() + "make phone call");
//		success = false;
//		taskFlow.afterTask(getIndex());
	}

	@Override
	public void rollback() {

//		System.out.println( taskFlow.getFlowName() + "rollback phone ");
//		taskFlow.rollback(getIndex());
	}
}

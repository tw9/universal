package com.wrqzn.taskflow.timer;

import com.wrqzn.taskflow.works.TaskFlow;

import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskFlowContainer {

	private List<TaskFlow> taskFlows;
	private List<List<Date>> runTimeList;
	private List<Long> flowPeriods;






	public void runTask(){
		for (int i = 0; i < taskFlows.size() ; i++) {
			Timer timer = new Timer();
		}
	}


	public List<TaskFlow> getTaskFlows() {
		return taskFlows;
	}

	public void setTaskFlows(List<TaskFlow> taskFlows) {
		this.taskFlows = taskFlows;
	}

	public List<List<Date>> getRunTimeList() {
		return runTimeList;
	}

	public void setRunTimeList(List<List<Date>> runTimeList) {
		this.runTimeList = runTimeList;
	}

	public List<Long> getFlowPeriods() {
		return flowPeriods;
	}

	public void setFlowPeriods(List<Long> flowPeriods) {
		this.flowPeriods = flowPeriods;
	}
}

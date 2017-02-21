package com.wrqzn.taskflow.works;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskFlow  extends TimerTask
//		implements Runnable
{

	private int flowId;
	private String flowName;
	private List<Task> tasks;
	private Map<String,Object> commonsParam;


	public TaskFlow() {
	}

	public TaskFlow(List<Task> tasks) {
		this.tasks = tasks;
	}

	public TaskFlow(String flowName, List<Task> tasks) {
		this.flowName = flowName;
		this.tasks = tasks;
	}


	@Override
	public void run() {
		for (int i = 0; i < tasks.size() ; i++) {
			tasks.get(i).addCommonsParameter(commonsParam);
			tasks.get(i).run();
			if (tasks.get(i).isSuccess()){
				// success , run next
				if (i !=0 && i < tasks.size() -1 ) {
					tasks.get(i+1).addPipeArgs(tasks.get(i).getResult());
				}
			} else {
				// rollback
				rollback(i);
				break;
			}
		}

	}

	public void rollback(int index){
		for (int i = index ; i >= 0 ; i--) {
			tasks.get(i).rollback();
			tasks.get(i).setSuccess(false);
		}
	}



	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Map<String, Object> getCommonsParam() {
		return commonsParam;
	}

	public void setCommonsParam(Map<String, Object> commonsParam) {
		this.commonsParam = commonsParam;
	}

}

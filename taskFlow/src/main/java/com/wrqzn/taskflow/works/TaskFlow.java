package com.wrqzn.taskflow.works;

import com.wrqzn.taskflow.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskFlow {

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

	public void start(){
		tasks.get(0).setIndex(0);
		tasks.get(0).setTaskFlow(this);
		Thread thread = new Thread(tasks.get(0));
		thread.start();
	}

	public void afterTask(int index){
		Map<String,Object> result  = tasks.get(index).getResult();
		result.putAll(commonsParam);
//		System.out.println( Thread.activeCount() );
//		System.out.println(result);
		index ++ ;
		if (tasks.size() > index) {
			tasks.get(index).setArgs(result);
			tasks.get(index).setIndex(index);
			tasks.get(index).setTaskFlow(this);
			Thread thread = new Thread(tasks.get(index));
			thread.start();
		} else {
			System.out.println( flowName + " task flow end " );
		}

	}


	////


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

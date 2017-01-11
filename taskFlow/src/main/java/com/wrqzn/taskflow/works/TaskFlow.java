package com.wrqzn.taskflow.works;

import com.wrqzn.taskflow.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskFlow  implements Runnable{

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
		tasks.get(0).setIndex(0);
		tasks.get(0).setTaskFlow(this);
		tasks.get(0).run();
	}

	public void afterTask(int index){
		Map<String,Object> result  = tasks.get(index).getResult();
		if(null != commonsParam ) {
			result.putAll(commonsParam);
		}

		if ( tasks.get(index).isSuccess() == true ) {
			index ++ ;
			if (tasks.size() > index) {
				tasks.get(index).setArgs(result);
				tasks.get(index).setIndex(index);
				tasks.get(index).setTaskFlow(this);
				tasks.get(index).run();
			} else {
				System.out.println( flowName + " task flow end " );
			}

		} else {
			rollback(++index);


	}

	public void rollback(int index){
		index--;

		if (index>=0) {
			tasks.get(index).rollback();
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

package com.wrqzn.taskflow;

import com.wrqzn.taskflow.works.TaskFlow;

import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class Task {

	protected Map<String,Object> args;
	protected Map<String,Object> result;
	protected boolean success = false;
	protected boolean rollback = false;
	protected int index;
	protected TaskFlow taskFlow;

	public Task() {
	}


	public abstract void run();
	public abstract void rollback();


	public Task(Map<String, Object> args) {
		this.args = args;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public TaskFlow getTaskFlow() {
		return taskFlow;
	}

	public void setTaskFlow(TaskFlow taskFlow) {
		this.taskFlow = taskFlow;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public void setArgs(Map<String, Object> args) {
		this.args = args;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isRollback() {
		return rollback;
	}

	public void setRollback(boolean rollback) {
		this.rollback = rollback;
	}
}

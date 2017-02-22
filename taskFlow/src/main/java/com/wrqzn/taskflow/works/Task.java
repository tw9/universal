package com.wrqzn.taskflow.works;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 1/9/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public abstract class Task {

	protected Map<String,Object> args = new HashMap<>();
	protected Map<String,String> pipe_args = new HashMap<>();
	protected Map<String,Object> result = new HashMap<>();
	protected boolean success = true;
	protected int index;

	public Task() {
	}


	public abstract void run();
	public abstract void rollback();

	public void setParameter(List<Map<String,Object>> allParameters) {
		if (null != allParameters && allParameters.size() > 0){
			for (int i = 0; i < allParameters.size() ; i++) {
				if (allParameters.get(i).get("parameter_type").equals(1)){
					pipe_args.put(allParameters.get(i).get("parameter_code").toString(),allParameters.get(i).get("parameter_value").toString());
				} else if (allParameters.get(i).get("parameter_type").equals(2)){
					args.put(allParameters.get(i).get("parameter_code").toString(),allParameters.get(i).get("parameter_value"));
				}
			}
		}
	}


	public void addCommonsParameter(Map<String,Object> commonsParam){
		if (null != commonsParam) {
			this.args.putAll(commonsParam);
		}
	}
	public void addPipeArgs(Map<String,Object> previousResult){
		pipe_args.forEach( (k,v) -> {
			this.args.put(v,previousResult.get(k));
		});
	}


	public Task(Map<String, Object> args) {
		this.args = args;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public Map<String, String> getPipe_args() {
		return pipe_args;
	}

	public void setPipe_args(Map<String, String> pipe_args) {
		this.pipe_args = pipe_args;
	}

}

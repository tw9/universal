package com.wrqzn.taskflow.works;

import com.wrqzn.taskflow.db.dbop.BaseQuery;

import java.util.*;

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
//	private List<Task> tasks;
	private Map<Integer,Task> tasks;
	private List<Integer> rollbacksort;
	private Map<String,Object> commonsParam;

	private Date firstRunTime;
	private List<Date> runTime;
	private Long periods;

	private Map<String,Object> flowResult = new HashMap<>();


	public TaskFlow() {
	}

	public TaskFlow clone(){
		TaskFlow cflow = new TaskFlow();
		cflow.setFlowId(this.flowId);
		cflow.setFlowName(this.flowName);
		cflow.setTasks(this.tasks);
		cflow.setCommonsParam( this.commonsParam );
		cflow.setFirstRunTime( this.firstRunTime );
		cflow.setRunTime( this.runTime );
		cflow.setPeriods( this.periods );
		return cflow;
	}

	public TaskFlow(List<Map<String,Object>> taskFlowData) {

		if (null != taskFlowData && taskFlowData.size() > 0 ){
			this.flowId = (int) taskFlowData.get(0).get("taskflow_id");
			this.flowName = null != taskFlowData.get(0).get("name") ? (String) taskFlowData.get(0).get("name") : "";

			String commSql = "select" +
					" parameter_code" +
					",parameter_value" +
					" from task_input_parameter" +
					" where parameter_type = 0 " +
					" and taskflow_id = " + flowId ;
			List<Map<String,Object>> commonParamDB = BaseQuery.run(commSql);
			Map<String,Object> tempCommon = new HashMap<>();
			for (int i = 0; i < commonParamDB.size() ; i++) {
				tempCommon.put(commonParamDB.get(i).get("parameter_code").toString(),commonParamDB.get(i).get("parameter_value"));
			}
			this.commonsParam = tempCommon;

//			List<Task> temptasks = new ArrayList<>();
			Map<Integer,Task> temptasks = new HashMap<>();

			for (int i = 0; i < taskFlowData.size() ; i++) {
				if (null == taskFlowData.get(i).get("class_path") || taskFlowData.get(i).get("class_path").equals("")) {
					continue;
				}
				if (null == taskFlowData.get(i).get("task_id") || taskFlowData.get(i).get("task_id").equals("")) {
					continue;
				}

				String classPath = (String) taskFlowData.get(i).get("class_path");
				Integer taskSortFlag = (Integer) taskFlowData.get(i).get("sort_flag");
				String paramSql = "select" +
						" parameter_type" +
						",parameter_code" +
						",parameter_value" +
						" from task_input_parameter" +
						" where parameter_type in (1,2,3,4) " +
						" and taskflow_id = " + flowId +
						" and sort_id =  " + taskSortFlag;
				Task task= null ;
				Class<?> dd = null;
				try {
					dd = Class.forName(classPath);
					task = (Task) dd.newInstance();
					task.setParameter(BaseQuery.run(paramSql));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				task.setNextTask( taskFlowData.get(i).get("next_task_sort")  );
				task.setBranchCondition( taskFlowData.get(i).get("branch_condition")  );
				task.setBranchSort( taskFlowData.get(i).get("branch_task_sort")  );
				temptasks.put(taskSortFlag,task);
//				temptasks.add(task);
			}
			this.tasks = temptasks;
		}

	}

//	public TaskFlow(List<Task> tasks) {
//		this.tasks = tasks;
//	}

//	public TaskFlow(String flowName, List<Task> tasks) {
//		this.flowName = flowName;
//		this.tasks = tasks;
//	}


	@Override
	public void run() {
		Task task = tasks.get(0);
		while (null != task){
			task.addCommonsParameter(commonsParam);
			task.run();

//			-- 3: 保存結果到taskflow的总结果
			for (int j = 0; j < task.getSaveToFlowResult().size() ; j++) {
				String key = task.getSaveToFlowResult().get(j);
				flowResult.put(key,task.getResult().get(key));
			}



			if (task.isSuccess()){
				// success , run next
				rollbacksort.add(task.getSortFlag());
				Integer nextTaskSort = null;
				if (null == task.getBranchCondition() || "".equals(task.getBranchCondition())) {
					nextTaskSort = task.getBranchSort();
				}  else {
					nextTaskSort = task.getNextTask();
				}
				Task nextTask = tasks.get(nextTaskSort);

				Map<String,Object> lastResult = task.getResult();

				// -- 4: 从taskflow总结果中获取数据
				for (int j = 0; j < nextTask.getGetFromFlowResult().size() ; j++) {
					String key = nextTask.getGetFromFlowResult().get(j);
					lastResult.put(key,flowResult.get(key));
				}

				nextTask.addPipeArgs(lastResult);

				task = nextTask;
			} else {
				// rollback
				rollback();
				break;
			}
		}

	}

	public void rollback(){
		for (int i = rollbacksort.size() ; i >= 0 ; i--) {
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

	public Map<Integer, Task> getTasks() {
		return tasks;
	}

	public void setTasks(Map<Integer, Task> tasks) {
		this.tasks = tasks;
	}

	public Map<String, Object> getCommonsParam() {
		return commonsParam;
	}

	public void setCommonsParam(Map<String, Object> commonsParam) {
		this.commonsParam = commonsParam;
	}

	public List<Date> getRunTime() {
		return runTime;
	}

	public void setRunTime(List<Date> runTime) {
		this.runTime = runTime;
	}

	public Long getPeriods() {
		return periods;
	}

	public void setPeriods(Long periods) {
		this.periods = periods;
	}

	public Date getFirstRunTime() {
		return firstRunTime;
	}

	public void setFirstRunTime(Date firstRunTime) {
		this.firstRunTime = firstRunTime;
	}

	public Map<String, Object> getFlowResult() {
		return flowResult;
	}

	public void setFlowResult(Map<String, Object> flowResult) {
		this.flowResult = flowResult;
	}

	public List<Integer> getRollbacksort() {
		return rollbacksort;
	}

	public void setRollbacksort(List<Integer> rollbacksort) {
		this.rollbacksort = rollbacksort;
	}
}

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
	private List<Task> tasks;
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

			List<Task> temptasks = new ArrayList<>();
			for (int i = 0; i < taskFlowData.size() ; i++) {
				if (null == taskFlowData.get(i).get("class_path") || taskFlowData.get(i).get("class_path").equals("")) {
					continue;
				}
				if (null == taskFlowData.get(i).get("task_id") || taskFlowData.get(i).get("task_id").equals("")) {
					continue;
				}

				String classPath = (String) taskFlowData.get(i).get("class_path");
				String paramSql = "select" +
						" parameter_type" +
						",parameter_code" +
						",parameter_value" +
						" from task_input_parameter" +
						" where parameter_type in (1,2,3,4) " +
						" and taskflow_id = " + flowId +
						" and sort_id =  " + taskFlowData.get(i).get("sort_flag") ;
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
				temptasks.add(task);
			}
			this.tasks = temptasks;
		}

	}

//	public TaskFlow(List<Task> tasks) {
//		this.tasks = tasks;
//	}

	public TaskFlow(String flowName, List<Task> tasks) {
		this.flowName = flowName;
		this.tasks = tasks;
	}


	@Override
	public void run() {
		for (int i = 0; i < tasks.size() ; i++) {
			tasks.get(i).addCommonsParameter(commonsParam);
			tasks.get(i).run();

//			-- 3: 保存結果到taskflow的总结果
			for (int j = 0; j < tasks.get(i).getSaveToFlowResult().size() ; j++) {
				String key = tasks.get(i).getSaveToFlowResult().get(j);
				flowResult.put(key,tasks.get(i).getResult().get(key));
			}



			if (tasks.get(i).isSuccess()){
				// success , run next
				if ( i < tasks.size() -1 ) {
					Map<String,Object> lastResult = tasks.get(i).getResult();


					// -- 4: 从taskflow总结果中获取数据
					for (int j = 0; j < tasks.get(i+1).getGetFromFlowResult().size() ; j++) {
						String key = tasks.get(i+1).getGetFromFlowResult().get(j);
						lastResult.put(key,flowResult.get(key));
					}

					tasks.get(i+1).addPipeArgs(lastResult);

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
}

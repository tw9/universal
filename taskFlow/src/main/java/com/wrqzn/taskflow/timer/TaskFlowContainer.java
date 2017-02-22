package com.wrqzn.taskflow.timer;

import com.wrqzn.taskflow.db.dbop.BaseQuery;
import com.wrqzn.taskflow.db.entity.TaskTimerEntity;
import com.wrqzn.taskflow.works.TaskFlow;

import java.util.*;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskFlowContainer {

	private List<TaskFlow> taskFlows = new ArrayList<>();

	private List<TaskTimerEntity> taskTimerEntities;





	public void runTask(){

		for (int i = 0; i < taskTimerEntities.size() ; i++) {
			startFlow(i);
		}

	}

	private void startFlow(int index ){

		String sql = "select " +
				" a.taskflow_id" +
				",a.task_id" +
				",a.sort_flag" +
				",b.class_path" +
				",c.name "+
				" from taskflow_task a " +
				" left join task b " +
				"  on a.task_id = b.id " +
				" left join taskflow c" +
				"  on a.taskflow_id = c.id " +
				" order by a.sort_flag";
		List<Map<String,Object>> taskflowTask =  BaseQuery.run(sql);


		TaskFlow taskFlow = new TaskFlow(taskflowTask);
		taskFlow.setFirstRunTime( taskTimerEntities.get(index).getFirstRun() );
		taskFlow.setPeriods( taskTimerEntities.get(index).getRunPeriod() );
		if (null == taskFlow.getPeriods()  || taskFlow.getPeriods() == 0 ) {
			TaskFlowPeriod taskFlowPeriod = new TaskFlowPeriod();
			List<Date> runTime = taskFlowPeriod.taskFlowPeriod(taskTimerEntities.get(index));
			taskFlow.setRunTime(runTime);
			taskFlows.add(taskFlow);

			if (null != runTime && runTime.size() > 0 ) {
				int i = 0;
				Date sTime = runTime.get(i++);
				while (sTime.getTime() < System.currentTimeMillis() + (24*3600*1000)) {
					Timer timer = new Timer();
					timer.schedule(taskFlow.clone(),sTime);
					if (i < runTime.size()) {
						sTime = runTime.get(i++);
					} else {
						break;
					}

				}

			}


		}else {
			Timer timer = new Timer();
			taskFlows.add(taskFlow);
			if (null == taskFlow.getFirstRunTime()  || taskFlow.getFirstRunTime().getTime() < System.currentTimeMillis() ){
				timer.schedule(taskFlow,0,taskFlow.getPeriods());
			} else {
				timer.schedule(taskFlow,taskFlow.getFirstRunTime(),taskFlow.getPeriods());
			}
		}


	}



	public List<TaskFlow> getTaskFlows() {
		return taskFlows;
	}

	public void setTaskFlows(List<TaskFlow> taskFlows) {
		this.taskFlows = taskFlows;
	}

	public List<TaskTimerEntity> getTaskTimerEntities() {
		return taskTimerEntities;
	}

	public void setTaskTimerEntities(List<TaskTimerEntity> taskTimerEntities) {
		this.taskTimerEntities = taskTimerEntities;
	}

}

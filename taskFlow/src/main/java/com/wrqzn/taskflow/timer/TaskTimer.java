package com.wrqzn.taskflow.timer;

import com.wrqzn.taskflow.db.dbop.BaseQuery;

import java.util.*;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskTimer {

	private Date nextChecktime;

	public void check(){

//		Calendar calendar = Calendar.getInstance();
//		if ( calendar.get(Calendar.HOUR_OF_DAY) < 23 ) {
//			checkDB(calendar.getTime());
//		} else {
//			checkDB(null);
//		}
//
//		calendar.set(Calendar.HOUR_OF_DAY,0);
//		calendar.set(Calendar.MINUTE,0);
//		calendar.set(Calendar.SECOND,0);
//		TimerTask timerTask = new TimerTask() {
//			@Override
//			public void run() {
//				checkDB(null);
//			}
//		};
//		Timer timer = new Timer();
//		timer.schedule(timerTask,calendar.getTime(),24*3600*1000);
		checkDB();

	}




	// null date : check next day 00:00:00
	private void checkDB(){
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select");
		sqlBuilder.append(" id");
		sqlBuilder.append(" ,task_id");
		sqlBuilder.append(" ,active");
		sqlBuilder.append(" ,start_time");
		sqlBuilder.append(" ,end_time");
		sqlBuilder.append(" ,first_run");
		sqlBuilder.append(" ,v1_year");
		sqlBuilder.append(" ,v2_month");
		sqlBuilder.append(" ,v3_day");
		sqlBuilder.append(" ,v4_weekday");
		sqlBuilder.append(" ,v5_hour");
		sqlBuilder.append(" ,v6_minute");
		sqlBuilder.append(" ,v7_second");
		sqlBuilder.append(" from task_timer");
		sqlBuilder.append(" where active=1 ");
		sqlBuilder.append(" and start_time >= now() ");
		sqlBuilder.append(" and end_time < now() ");

		List<Map<String,Object>>  datas = BaseQuery.run(sqlBuilder.toString());

		System.out.println(datas.size());


		//
		TaskFlowContainer todayTasks = new TaskFlowContainer();
	}


}

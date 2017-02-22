package com.wrqzn.taskflow.db.entity;

import java.util.Date;
import java.util.Map;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskTimerEntity {

//	id int ,
//	task_id int,
//	active int,  -- 0:not active  1:active
//	start_time datetime,
//	end_time datetime,
//	v1_year varchar(15),
//	v2_month varchar(15),
//	v3_day varchar(15),
//	v4_workday varchar(15),
//	v5_hour varchar(15),
//	v6_minute varchar(15),
//	v7_second varchar(15)

	private Integer id;
	private Integer taskflowId;
	private boolean active;
	private Date startTime;
	private Date endTime;
	private Date firstRun;
	private Long runPeriod;
	private String year;
	private String month;
	private String day;
	private String weekday;
	private String hour;
	private String minute;
	private String second;

	private Map<String,Object> mapData ;

	public boolean canRun(){
		if ( null != runPeriod  && runPeriod>0 ) {
			return true;
		} else if (null != year
				|| null != month
				|| null != day
				|| null != weekday
				|| null != hour
				|| null != minute
				|| null != second
				) {
			return true;
		} else  if (System.currentTimeMillis() <= firstRun.getTime()){
			return true;
		} else {
			return false;
		}
	}


	public TaskTimerEntity() {
	}

	public TaskTimerEntity(Map<String,Object> data) {
		this.mapData = data;

		this.id = (Integer) verify("id");
		this.taskflowId = (Integer) verify("taskflow_id");
		this.active = verify("active").equals(1) ? true :false;
		this.startTime = (Date) verify("start_time");
		this.endTime = (Date) verify("end_time");
		this.firstRun = (Date) verify("first_run");
		this.runPeriod = null ==  verify("run_period")?0: Long.valueOf( verify("run_period").toString());
		this.year = (String) verify("v1_year");
		this.month = (String) verify("v2_month");
		this.day = (String) verify("v3_day");
		this.weekday = (String) verify("v4_weekday");
		this.hour = (String) verify("v5_hour");
		this.minute = (String) verify("v6_minute");
		this.second = (String) verify("v7_second");
	}

	private Object verify(String key){
		Object obj = mapData.get(key);

		if (null == obj || obj.toString().trim().equals("") ){
			return null;
		} else {
			return obj;
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskflowId() {
		return taskflowId;
	}

	public void setTaskflowId(Integer taskflowId) {
		this.taskflowId = taskflowId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public Date getFirstRun() {
		return firstRun;
	}

	public void setFirstRun(Date firstRun) {
		this.firstRun = firstRun;
	}

	public Long getRunPeriod() {
		return runPeriod;
	}

	public void setRunPeriod(Long runPeriod) {
		this.runPeriod = runPeriod;
	}
}

package com.wrqzn.taskflow.timer;

import com.wrqzn.taskflow.db.entity.TaskTimerEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TaskFlowPeriod {
//	first_run datetime,
//	v1_year varchar(15),
//	v2_month varchar(15),
//	v3_day varchar(15),
//	v4_workday varchar(15),
//	v5_hour varchar(15),
//	v6_minute varchar(15),
//	v7_second varchar(15)

	public static long currentTime;
	public static long maxTime;
	public static MillisecondPeriod millisecondPeriod;

	static {
		Calendar calendar = Calendar.getInstance();
		currentTime = calendar.getTimeInMillis();
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+11);
		maxTime = calendar.getTimeInMillis();
		millisecondPeriod = new MillisecondPeriod();
		millisecondPeriod.setStart(currentTime);
		millisecondPeriod.setEnd(maxTime);
	}



	private Integer taskFlowId;
	private Date firstRun;
//	private List<MillisecondPeriod>  year ;
//	private List<MillisecondPeriod>  month ;
//	private List<MillisecondPeriod>  weekday ;
//	private List<MillisecondPeriod>  day;
//	private List<MillisecondPeriod>  hour;
//	private List<MillisecondPeriod>  minute;
//	private List<MillisecondPeriod>  second;



	public List<Date> taskFlowPeriod(TaskTimerEntity dbData) {
		this.taskFlowId = dbData.getTaskflowId();
		if (null != dbData.getFirstRun()) {
			this.firstRun = dbData.getFirstRun();
		}


		List<String> datas = new ArrayList<>();
		datas.add(dbData.getYear());
		datas.add(dbData.getMonth());
		datas.add(dbData.getWeekday());
		datas.add(dbData.getDay());
		datas.add(dbData.getHour());
		datas.add(dbData.getMinute());
		datas.add(null == dbData.getSecond()?"0":dbData.getSecond());

		List<Integer> calType = new ArrayList<>();
		calType.add(Calendar.YEAR);
		calType.add(Calendar.MONTH);
		calType.add(Calendar.DAY_OF_WEEK);
		calType.add(Calendar.DAY_OF_MONTH);
		calType.add(Calendar.HOUR_OF_DAY);
		calType.add(Calendar.MINUTE);
		calType.add(Calendar.SECOND);

		List<List<MillisecondPeriod>> result = new ArrayList<>();

		for (int i = 0; i < datas.size(); i++) {
			if (null == datas.get(i)) {
				result.add(result.get(i-1));
				continue;
			}
			if (i == 0) {
				result.add(calcMillis(datas.get(i),calType.get(i),millisecondPeriod));
			} else {
				if ( null != result.get(i-1) && result.get(i-1).size()>0){
					List<MillisecondPeriod> tempResult = new ArrayList<>();
					for (int j = 0; j < result.get(i-1).size() ; j++) {
						tempResult.addAll(calcMillis(datas.get(i),calType.get(i),result.get(i-1).get(j)));
					}
					if (tempResult.size()>0) {
						result.add(tempResult);
					} else {
						result.add(result.get(i-1));
					}
				} else {
					result.add(calcMillis(datas.get(i),calType.get(i),millisecondPeriod));
				}
			}
		}

		List<Date> runTimeList = new ArrayList<>();
		List<MillisecondPeriod> secondPeriods = result.get(6);

		for (int i = 0; i <  secondPeriods.size() ; i++) {
			Date date = new Date(secondPeriods.get(i).getStart());
			runTimeList.add(date);
		}
		if (null !=firstRun && firstRun.getTime() >= System.currentTimeMillis() ) {
			runTimeList.add(firstRun);
		}

		return runTimeList;

	}


	private List<MillisecondPeriod> calcMillis(String value,int fieldType,MillisecondPeriod millisecondPeriod){


		List<MillisecondPeriod> periods = new ArrayList<>();

		if ( value.contains("-") && value.contains("/")) {  //  2016-2020/2
			String[] values = value.split("-");
			String[] valueSencond = values[1].split("/");

			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(fieldType, Integer.valueOf(values[0]));
			long minValue = cal.getTimeInMillis();
			cal.set(fieldType,Integer.valueOf(valueSencond[0]));
			long maxValue = cal.getTimeInMillis();

			if (millisecondPeriod.contains(minValue,maxValue)){

				while (minValue <= cal.getTimeInMillis() && cal.getTimeInMillis() < maxValue) {
					MillisecondPeriod period = new MillisecondPeriod(cal.getTimeInMillis(),fieldType);
					periods.add(period);
					cal.set(fieldType,cal.get(fieldType)+Integer.valueOf(valueSencond[1]));
				}

			}

		} else if ( value.contains("-") ){  // 2016-2018
			String[] values = value.split("-");


			MillisecondPeriod period = new MillisecondPeriod(values[0],values[1],fieldType);

			if ( millisecondPeriod.contains(period.getStart(),period.getEnd() )){
				periods.add(period);
			}

		} else if (value.contains("/")) {  //  */2

			String[] values = value.split("/");

			Calendar cal = Calendar.getInstance();
//			int start = cal.get(fieldType);
			cal.setTimeInMillis(millisecondPeriod.getStart());
//			cal.set(fieldType,start);
			long currentTime = System.currentTimeMillis();

			while ( millisecondPeriod.contains(cal.getTimeInMillis()) ) {
				if (cal.getTimeInMillis() > currentTime ) {
					MillisecondPeriod period = new MillisecondPeriod(cal.getTimeInMillis(),fieldType);
					periods.add(period);
				}
				cal.set(fieldType,cal.get(fieldType)+Integer.valueOf(values[1]));
			}

		}
		return periods;
	}



}

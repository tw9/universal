package com.wrqzn.taskflow.timer;

import java.util.Calendar;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class MillisecondPeriod {

	private Calendar calendar = Calendar.getInstance();

	private long start; // greater equal
	private long end; // less than

	public MillisecondPeriod() {
	}

	public MillisecondPeriod(String startStr, String endStr, int fieldType) {
		calendar.clear();
		calendar.set(fieldType,Integer.valueOf(startStr));
		this.start = calendar.getTimeInMillis();
		calendar.set(fieldType,Integer.valueOf(endStr)+1);
		this.end = calendar.getTimeInMillis();
	}

	public MillisecondPeriod(int startStr,int endStr,int fieldType) {
		calendar.clear();
		calendar.set(fieldType,startStr);
		this.start = calendar.getTimeInMillis();
		calendar.set(fieldType,endStr+1);
		this.end = calendar.getTimeInMillis();
	}

	public MillisecondPeriod(int startStr,int fieldType) {
		calendar.clear();
		calendar.set(fieldType,startStr);
		this.start = calendar.getTimeInMillis();
		calendar.set(fieldType,startStr+1);
		this.end = calendar.getTimeInMillis();
	}

	public MillisecondPeriod(long startStr,int fieldType) {
		this.start = startStr;
		calendar.setTimeInMillis(this.start);
		calendar.set(fieldType,calendar.get(fieldType)+1);
		this.end = calendar.getTimeInMillis();
	}

	public boolean contains(long minValue, long maxValue ) {
		if ( minValue > end || maxValue < start ) {
			return false;
		} else {
			return true;
		}
	}

	public boolean contains(long timeInMillis) {
		if ( start <= timeInMillis && timeInMillis < end){
			return true;
		} else {
			return false;
		}
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

}

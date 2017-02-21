package com.wrqzn.taskflow.test;

import org.junit.Test;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WANG, RUIQING on 2/21/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class TimerTest {


//	@Test
	public void test(){
//		TimerTask timerTask = new MyTask();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("hello");
			}
		};

		Timer timer  = new Timer();
		timer.schedule(timerTask,200);

	}

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH,2);
		System.out.println(Thread.activeCount());

		for (int i = 0; i < 10 ; i++) {
		TimerTask timerTask = new MyTask("T"+i+"  ");
		Timer timer  = new Timer();
		timer.schedule(timerTask,0);
			System.out.println("count:"+Thread.activeCount());
		}

		for (int i = 0; i < 100; i++) {

			System.out.println("count:"+Thread.activeCount());
		}
		System.gc();

//		TimerTask timerTask = new MyTask("A");
//		Timer timer  = new Timer();
//		timer.schedule(timerTask,0);
//		System.out.println("fffff");
//		System.out.println(Thread.activeCount());



//		timer.cancel();

//		TimerTask timerTask2 = new MyTask("B");
//		Timer timer2  = new Timer();
//		timer2.schedule(timerTask2,100,1000);

	}
}

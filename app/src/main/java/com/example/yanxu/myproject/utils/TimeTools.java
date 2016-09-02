package com.example.yanxu.myproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author yanxu
 */
public class TimeTools {
	static SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm:ss");
	public static String parseTime(String time) {
		String newTime = null;
		
		long time1 = Long.valueOf(time);
		newTime = formatter.format(new Date(time1 * 1000L));
		return newTime;
	}
	public static String parseTimeYmd(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日");
		String newTime = null;
		
		long time1 = Long.valueOf(time);
		newTime = formatter.format(new Date(time1 * 1000L));
		return newTime;
	}
	public static String createTime(String time) {
		String newTime = null;
		Date d;
		try {
			d = formatter.parse(time);
			long l = d.getTime();
			String str = String.valueOf(l);
			newTime = str.substring(0, 10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newTime;
	}
	/**
	 * 普通的年月
	 * @return
	 */
	public static String getCurTime(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");       
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
		String time = formatter.format(curDate); 
		return time;
	}
	/**
	 * 斜杠的时间
	 * @param time
	 * @return
	 */
	public static String parseTimeSlash(String time) {
		SimpleDateFormat formatter2 = new SimpleDateFormat(
				"yyyy/MM/dd日 HH/mm/ss");
		String newTime = null;
		long time1 = Long.valueOf(time);
		newTime = formatter2.format(new Date(time1 * 1000L));
		return newTime;
	}
	/**
	 * 横杠的时间
	 * @param time
	 * @return
	 */
	public static String parseTimeBar(String time) {
		SimpleDateFormat formatter2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String newTime = null;
		long time1 = Long.valueOf(time);
		newTime = formatter2.format(new Date(time1 * 1000L));
		return newTime;
	}
	/**
	 * 00:00
	 * @param time
	 * @return
	 */
	public static String parseTimeS(String time) {
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
		String newTime = null;
		long time1 = Long.valueOf(time);
		newTime = formatter2.format(new Date(time1 * 1000L));
		return newTime;
	}
	/**
	 * 00:00：00
	 * @param time
	 * @return
	 */
	public static String parseTimeSS(String time) {
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
		String newTime = null;
		long time1 = Long.valueOf(time);
		newTime = formatter2.format(new Date(time1 * 1000L));
		return newTime;
	}
}

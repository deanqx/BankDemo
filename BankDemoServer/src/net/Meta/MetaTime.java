package net.Meta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MetaTime {
	private static long StopWatch;
	
	public static String getTime() {
		SimpleDateFormat Date = new SimpleDateFormat("HH:mm:ss");
		return Date.format(new Date());
	}
	
	public static String getDate() {
		SimpleDateFormat Date = new SimpleDateFormat("dd.MM.yyyy");
		return Date.format(new Date());
	}
	
	public static void StartStopWatch() {
		StopWatch = System.currentTimeMillis();
	}
	
	public static long EndStopWatch() {
		return System.currentTimeMillis() - StopWatch;
	}
}
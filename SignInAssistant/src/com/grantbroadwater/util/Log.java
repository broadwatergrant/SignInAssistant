package com.grantbroadwater.util;

import java.util.ArrayList;

public class Log {

	public enum LogType{
		VERBOSE, INFO, WARN, ERROR, DEBUG;
	}
	
	private final LogType type;
	private final String message;
	
	private static boolean v, i, w, e, d;
	private static ArrayList<Log> logList;
	
	public Log(String message) {
		this(LogType.VERBOSE, message);
	}
	
	public Log(LogType type, String message){
		this.message = displayMessage(message);
		this.type = type;
		
		logList.add(this);
	}
	
	private String displayMessage(String message){
		StackTraceElement element = Thread.currentThread().getStackTrace()[3];
		String className = element.getClassName();
		String methodName = element.getMethodName();
		int lineNumber = element.getLineNumber();
		String debugInfo = className + "." + methodName + "() [" + lineNumber + "] :: \n\t"; 
		String result = debugInfo + message;
		
		switch(this.type){
		case DEBUG:
			if(d)
				System.out.println(message);
			break;
		case ERROR:
			if(e)
				System.out.println(message);
			break;
		case INFO:
			if(i)
				System.out.println(message);
			break;
		case VERBOSE:
			if(v)
				System.out.println(message);
			break;
		case WARN:
			if(w)
				System.out.println(message);
			break;
		default:
			break;
		}
		
		return result;
	}

	public static boolean isV() {
		return v;
	}

	public static void setV(boolean b) {
		Log.v = b;
	}

	public static boolean isI() {
		return i;
	}

	public static void setI(boolean i) {
		Log.i = i;
	}

	public static boolean isW() {
		return w;
	}

	public static void setW(boolean w) {
		Log.w = w;
	}

	public static boolean isE() {
		return e;
	}

	public static void setE(boolean e) {
		Log.e = e;
	}

	public static boolean isD() {
		return d;
	}

	public static void setD(boolean d) {
		Log.d = d;
	}
	
	public String getMessage() {
		return message;
	}

	public LogType getType() {
		return type;
	}

	public static ArrayList<Log> getAllLogs(){
		return logList;
	}
	
	public static ArrayList<Log> getLogsOfType(LogType type){
		ArrayList<Log> result = new ArrayList<Log>();
		
		for(Log l : logList)
			if(l.type == type)
				result.add(l);
		
		return result;
	}

}

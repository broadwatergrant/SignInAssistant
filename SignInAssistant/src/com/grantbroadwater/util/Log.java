package com.grantbroadwater.util;

import java.util.ArrayList;

public class Log {

	public enum LogType{
		VERBOSE, INFO, WARN, ERROR, DEBUG;
	}
	
	private final LogType type;
	private final String message;
	
	private static boolean v = true, i = true, w = true, e = true, d = true;
	private static ArrayList<Log> logList = new ArrayList<Log>();
	
	public Log(LogType type, String message){
		this.type = type;
		this.message = displayMessage(message);
		
		logList.add(this);
	}
	
	private String displayMessage(String message){
		StackTraceElement element = Thread.currentThread().getStackTrace()[3];
		String className = element.getClassName();
		String methodName = element.getMethodName();
		int lineNumber = element.getLineNumber();
		String debugInfo = this.type + " - " + className + "." + methodName + "() [" + lineNumber + "] :: \n\t"; 
		String result = debugInfo + message;
		
		switch(this.type){
		case DEBUG:
			if(d)
				System.out.println(result);
			break;
		case ERROR:
			if(e)
				System.out.println(result);
			break;
		case INFO:
			if(i)
				System.out.println(result);
			break;
		case VERBOSE:
			if(v)
				System.out.println(result);
			break;
		case WARN:
			if(w)
				System.out.println(result);
			break;
		default:
			break;
		}
		
		return result;
	}

	public static boolean isV() {
		return v;
	}

	public static void setV(boolean v) {
		Log.v = v;
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

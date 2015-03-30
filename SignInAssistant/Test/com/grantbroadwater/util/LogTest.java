package com.grantbroadwater.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class LogTest {

	@Test
	public void test() {
		Log.setV(false);
		new Log(Log.LogType.VERBOSE, "Hello, world");
		new Log(Log.LogType.WARN, "Oops");
		Log.setV(true);
		new Log(Log.LogType.VERBOSE, "Hello, again");
		
		assertEquals(3, Log.getAllLogs().size());
		
		@SuppressWarnings("unused")
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		
		/*
		for(Thread t : map.keySet()){
			for(StackTraceElement e : t.getStackTrace()){
				System.out.println(t.getName() + " - " + e.getClassName() + "." + e.getMethodName() + "() :: " + e.getLineNumber());
			}
		}*/
		/*
		for(StackTraceElement[] l : map.values()){
			for(StackTraceElement e : l){
				System.out.println(l. e.getClassName() + "." + e.getMethodName() + "() :: " + e.getLineNumber());
			}
		}*/
		
		
	}

}

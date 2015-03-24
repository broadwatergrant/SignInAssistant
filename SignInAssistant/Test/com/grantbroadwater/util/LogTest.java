package com.grantbroadwater.util;

import static org.junit.Assert.*;

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
	}

}

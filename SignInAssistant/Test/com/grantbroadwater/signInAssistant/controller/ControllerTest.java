package com.grantbroadwater.signInAssistant.controller;

import org.junit.Test;

import com.grantbroadwater.signInAssistant.SignInAssistant;
import com.grantbroadwater.util.Log;

public class ControllerTest {

	@Test
	public void test() {
		
	}
	
	public static void main(String[] args){
		SignInAssistant.main(null);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
	
		SignInAssistant.getController().restartApplication();
		new Log(Log.LogType.DEBUG, "Post-Restart");
	}

}

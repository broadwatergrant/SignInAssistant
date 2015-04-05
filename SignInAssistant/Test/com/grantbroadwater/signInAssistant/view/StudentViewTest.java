package com.grantbroadwater.signInAssistant.view;

import org.junit.Test;

public class StudentViewTest {

	@Test
	public void test() {
		StudentFrame frame = new StudentFrame();
		frame.setVisible(true);
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {}
	}

}

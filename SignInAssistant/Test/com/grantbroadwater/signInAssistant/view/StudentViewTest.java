package com.grantbroadwater.signInAssistant.view;

import org.junit.Test;

import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.controller.StudentSignInActionListener;
import com.grantbroadwater.signInAssistant.model.Model;

public class StudentViewTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		Controller controller = new Controller();
		
		StudentFrame frame = new StudentFrame();
		
		StudentPanel studentPanel = frame.getStudentPanel();
		
		StudentSignInActionListener listener = new StudentSignInActionListener(model, studentPanel, controller);
		studentPanel.addConfirmListener(listener);
		
		frame.setVisible(true);
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {}
	}

}

package com.grantbroadwater.signInAssistant.view;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.school.Student;
//import com.grantbroadwater.signInAssistant.controller.AdminSignInListener;
import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;

public class AdministratorPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		@SuppressWarnings("unused")
		Controller controller = new Controller();
		
		AdministratorPanel panel = new AdministratorPanel();
//		AdminSignInListener listener = new AdminSignInListener(model, panel, controller);
//		panel.setActionListener(listener);
		
		
		JPanel[] panels = {panel};
		String[] panelNames = {panel.getCardLayoutName()};
		SIAFrame frame = new SIAFrame(panels, panelNames);
		
		frame.setMaximumSize(panel.getPreferredSize());
		frame.setMinimumSize(panel.getPreferredSize());
		frame.setVisible(true);
		
		Student student = new Student("Grant", "Broadwater", "10101");
		for(int i=0; i<26; i++){
			panel.addStudent(student);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {}
	}

}

package com.grantbroadwater.signInAssistant.view;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.controller.AdminStartSignInListener;
//import com.grantbroadwater.signInAssistant.controller.AdminSignInListener;
import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;

public class AdministratorPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		Controller controller = new Controller();
		
		AdministratorPanel panel = new AdministratorPanel(new ArrayList<BellSchedule>(Arrays.asList(model.getSchedules())));
		// TODO: add save action listener
		
		AdminStartSignInListener listener = new AdminStartSignInListener(model, panel, controller);
		panel.addStartActionListener(listener);
		
		JPanel[] panels = {panel};
		String[] panelNames = {panel.getCardLayoutName()};
		SIAFrame frame = new SIAFrame(panels, panelNames);
		
		frame.setMaximumSize(panel.getPreferredSize());
		frame.setMinimumSize(panel.getPreferredSize());
		frame.setVisible(true);
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {}
	}

}

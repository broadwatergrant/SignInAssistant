package com.grantbroadwater.signInAssistant.view;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.signInAssistant.controller.AdminSaveActionListener;
import com.grantbroadwater.signInAssistant.controller.AdminStartSignInListener;
import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;

public class AdministratorPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		Controller controller = new Controller();
		
		AdministratorPanel panel = new AdministratorPanel(new ArrayList<BellSchedule>(Arrays.asList(model.getSchedules())));
		
		AdminStartSignInListener listener = new AdminStartSignInListener(controller);
		panel.addStartActionListener(listener);
		
		AdminSaveActionListener saveListener = new AdminSaveActionListener(model, controller);
		panel.addSaveActionListener(saveListener);
		
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

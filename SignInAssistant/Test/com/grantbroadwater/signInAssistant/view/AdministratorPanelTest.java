package com.grantbroadwater.signInAssistant.view;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.signInAssistant.controller.AdminSignInListener;
import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;

public class AdministratorPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
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
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

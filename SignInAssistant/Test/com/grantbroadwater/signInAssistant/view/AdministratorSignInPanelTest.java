package com.grantbroadwater.signInAssistant.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.signInAssistant.controller.AdminSignInListener;
import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;

public class AdministratorSignInPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		Controller controller = new Controller();
		
		AdministratorSignInPanel panel = new AdministratorSignInPanel();
		AdminSignInListener listener = new AdminSignInListener(model, panel, controller);
		panel.setActionListener(listener);
		
		JPanel[] panels = {panel};
		String[] panelNames = {panel.getCardLayoutName()};
		SIAFrame frame = new SIAFrame(panels, panelNames);
		
		frame.setMaximumSize(new Dimension(AdministratorSignInPanel.WIDTH, AdministratorSignInPanel.HEIGHT));
		frame.setMinimumSize(new Dimension(AdministratorSignInPanel.WIDTH, AdministratorSignInPanel.HEIGHT));
		frame.setVisible(true);
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

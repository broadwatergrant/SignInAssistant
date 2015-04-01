package com.grantbroadwater.signInAssistant.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.junit.Test;

public class AdministratorSignInPanelTest {

	@Test
	public void test() {
		AdministratorSignInPanel panel = new AdministratorSignInPanel();
		JPanel[] panels = {panel};
		String[] panelNames = {panel.getCardLayoutName()};
		SIAFrame frame = new SIAFrame(panels, panelNames);
		frame.showPanel(0);
		frame.setSize(AdministratorSignInPanel.WIDTH, AdministratorSignInPanel.HEIGHT);
		frame.setMaximumSize(new Dimension(AdministratorSignInPanel.WIDTH, AdministratorSignInPanel.HEIGHT));
		frame.setMinimumSize(new Dimension(AdministratorSignInPanel.WIDTH, AdministratorSignInPanel.HEIGHT));
		frame.setVisible(true);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

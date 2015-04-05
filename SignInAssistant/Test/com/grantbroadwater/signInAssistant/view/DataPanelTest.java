package com.grantbroadwater.signInAssistant.view;

import javax.swing.JPanel;

import org.junit.Test;

public class DataPanelTest {

	@Test
	public void test() {
		SIAFrame frame = new SIAFrame();
		
		DataPanel panel = new DataPanel();
		
		JPanel[] panels = {panel};
		String[] panelNames = {panel.getCardLayoutName()};
		
		frame.setPanels(panels, panelNames);
		
		frame.setMinimumSize(panel.getPreferredSize());
		frame.setMaximumSize(panel.getPreferredSize());
	
		frame.setVisible(true);
		
		try {Thread.sleep(60000);} catch (InterruptedException e) {}
	}

}

package com.grantbroadwater.signInAssistant.view;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.signInAssistant.controller.DataDocumentListener;
import com.grantbroadwater.signInAssistant.model.Model;

public class DataPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		SIAFrame frame = new SIAFrame();
		
		DataPanel panel = new DataPanel();
		
		JPanel[] panels = {panel};
		String[] panelNames = {panel.getCardLayoutName()};
		
		frame.setPanels(panels, panelNames);
		
		frame.setMinimumSize(panel.getPreferredSize());
		frame.setMaximumSize(panel.getPreferredSize());
	
		DataDocumentListener listener = new DataDocumentListener(model, panel);
		panel.addDataPanelDocumentListener(listener);
		
		frame.setVisible(true);
		
		try {Thread.sleep(60000);} catch (InterruptedException e) {}
	}

}

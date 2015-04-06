package com.grantbroadwater.signInAssistant.view;

import javax.swing.JPanel;

import org.junit.Test;

import com.grantbroadwater.signInAssistant.controller.InquireDocumentListener;
import com.grantbroadwater.signInAssistant.controller.InquireListSelectionListener;
import com.grantbroadwater.signInAssistant.model.Model;

public class EntryPanelTest {

	@Test
	public void test() {
		Model model = new Model();
		model.loadData();
		
		SIAFrame frame = new SIAFrame();
		
		InquirePanel inquirePanel = new InquirePanel();
		
		InquireDocumentListener documentListener = new InquireDocumentListener(model, inquirePanel);
		inquirePanel.addInquirePanelDocumentListener(documentListener);
		
		InquireListSelectionListener listSelectionListener = new InquireListSelectionListener(model, inquirePanel);
		inquirePanel.addInquirePanelListSelectionListener(listSelectionListener);
		
		JPanel[] panels = {inquirePanel};
		String[] panelNames = {inquirePanel.getCardLayoutName()};
		
		frame.setPanels(panels, panelNames);
		
		frame.setMinimumSize(inquirePanel.getPreferredSize());
		frame.setMaximumSize(inquirePanel.getPreferredSize());
		
		frame.setVisible(true);
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {}
	}

}

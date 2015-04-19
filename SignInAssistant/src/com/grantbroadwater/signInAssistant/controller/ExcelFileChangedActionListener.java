package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.DataPanel;

public class ExcelFileChangedActionListener implements ActionListener {

	private final Model model;
	private final Controller controller;
	
	public ExcelFileChangedActionListener(Controller controller, DataPanel dataPanel, Model model) {
		this.controller = controller;
		dataPanel.changeExcelFilePathText(model.getExcelFileName(), model.getExcelFileLocation());
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!controller.promptUserForSave("This action Requires the program to restart.\n"))
			return;
		model.getExcelFileLocationFromUser();
		controller.restartApplication();
	}

}

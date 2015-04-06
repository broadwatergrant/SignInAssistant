package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.AdministratorPanel;

public class AdminStartSignInListener implements ActionListener {

	private Model model;
	private AdministratorPanel administratorPanel;
	private Controller controller;
	
	public AdminStartSignInListener(Model model, AdministratorPanel administratorPanel, Controller controller) {
		this.model = model;
		this.administratorPanel = administratorPanel;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().toLowerCase().indexOf("start") != -1){
			administratorPanel.setScheduleComboBoxEnabled(false);
			btn.setText("Stop");
			controller.startStudentSignIn(model.getScheduleWithName(administratorPanel.getSelectedScheduleName()));	
		}else if(btn.getText().toLowerCase().indexOf("stop") != -1){
			administratorPanel.setScheduleComboBoxEnabled(true);
			btn.setText("Start");
			controller.stopStudentSignIn();
		}
	}

}

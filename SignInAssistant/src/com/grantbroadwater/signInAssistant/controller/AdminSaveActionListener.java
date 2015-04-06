package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminSaveActionListener implements ActionListener {

	private Controller controller;
	
	public AdminSaveActionListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.saveSignInSheet();
	}

}

package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseMenuBarListener implements ActionListener {

	Controller controller;
	
	public CloseMenuBarListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.userRequestingToClose();
	}

}

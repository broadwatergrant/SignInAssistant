package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminStartSignInListener implements ActionListener {

	private Controller controller;
	
	public AdminStartSignInListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.adminClickedStartStop();
	}

}

package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SIAWindowAdapter extends WindowAdapter {

	private Controller controller;
	
	public SIAWindowAdapter(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		controller.userRequestingToClose();
	}
	
}

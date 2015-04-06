package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.View;

public class SignInMenuBarListener implements ActionListener {

	Model model;
	Controller controller;
	View view;
	
	public SignInMenuBarListener(Model model, View view, Controller controller) {
		this.model = model;
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.adminClickedStartStop();
	}

}

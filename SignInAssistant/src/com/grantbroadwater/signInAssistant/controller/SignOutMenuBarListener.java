package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.signInAssistant.view.View;

public class SignOutMenuBarListener implements ActionListener {

	View view;
	
	public SignOutMenuBarListener(View view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.getSiaFrame().setSIAMenuBarVisibility(false);
		view.showPanel(view.getAdministratorSignInPanel().getCardLayoutName());
	}
}

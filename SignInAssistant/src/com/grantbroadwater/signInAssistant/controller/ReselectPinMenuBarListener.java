package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.signInAssistant.view.View;

public class ReselectPinMenuBarListener implements ActionListener {
	
	View view;
	
	public ReselectPinMenuBarListener(View view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.getStudentPanel().refocus();
	}

}

package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.AdministratorSignInPanel;

public class AdminSignInListener implements ActionListener {

	private Model model;
	private AdministratorSignInPanel panel;
	private Controller controller;
	
	public AdminSignInListener(Model model, AdministratorSignInPanel panel, Controller controller) {
		this.model = model;
		this.panel = panel;
		this.controller = controller;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = panel.getName();
		String pin = panel.getPin();
		if(model.adminIsValid(pin, name)){
			controller.signInAdmin(model.getAdministration().get(pin));
		}else{
			panel.displayMessageBox("Username and password does not match any valid administrator");
		}
	}
	

}

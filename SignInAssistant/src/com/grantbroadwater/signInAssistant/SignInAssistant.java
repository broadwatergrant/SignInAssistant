package com.grantbroadwater.signInAssistant;

import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.View;

public class SignInAssistant {

	private static Model model;
	private static View view;
	private static Controller controller;
	
	public static void main(String[] args) {
		controller = new Controller();
		controller.createModelAndView();
		
		view = controller.getView();
		model = controller.getModel();
		
		controller.startApplication();
	}

	public static Model getModel() {
		return model;
	}

	public static View getView() {
		return view;
	}

	public static Controller getController() {
		return controller;
	}

}

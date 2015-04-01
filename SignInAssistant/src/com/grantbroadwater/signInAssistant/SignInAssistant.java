package com.grantbroadwater.signInAssistant;

import com.grantbroadwater.signInAssistant.controller.Controller;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.View;

public class SignInAssistant {

	private static Model model;
	private static View view;
	private static Controller controller;
	
	public static void main(String[] args) {
		model = new Model();
		view = new View();
		controller = new Controller();
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		SignInAssistant.model = model;
	}

	public static View getView() {
		return view;
	}

	public static void setView(View view) {
		SignInAssistant.view = view;
	}

	public static Controller getController() {
		return controller;
	}

	public static void setController(Controller controller) {
		SignInAssistant.controller = controller;
	}

}

package com.grantbroadwater.signInAssistant.controller;

import com.grantbroadwater.school.Administrator;

public class Controller {

	public Controller() {
		// TODO Auto-generated constructor stub
	}

	protected void signInAdmin(Administrator admin){
		System.out.println(admin.getFirstName() + " to be signed in");
	}
	
}
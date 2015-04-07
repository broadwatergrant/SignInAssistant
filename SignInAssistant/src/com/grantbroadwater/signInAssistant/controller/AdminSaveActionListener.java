package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;


public class AdminSaveActionListener implements ActionListener {

	private Controller controller;
	private Model model;
	
	public AdminSaveActionListener(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("model: "+(model == null));
		System.out.println("sis: "+(model.getSignInSheet() == null));
		System.out.println("students: "+(model.getSignInSheet().getSignInSheet() == null));
		Student[] students = model.getSignInSheet().getSignInSheet();
		Integer[] parallelClasses = model.getSignInSheet().getParrallelHours();
		controller.saveSignInSheet(students, parallelClasses);
	}

}

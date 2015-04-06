package com.grantbroadwater.signInAssistant.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.View;

public class Controller {

	Model model;
	View view;
	
	public Controller(){
		
	}
	
	public void createModelAndView(){
		model = new Model();
		model.loadData();
		
		view = new View(new ArrayList<BellSchedule>(Arrays.asList(model.getSchedules())));
	}
	
	public Model getModel(){
		return model;
	}
	
	public View getView(){
		return this.view;
	}
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	protected void signInAdmin(Administrator admin){
		System.out.println(admin.getFirstName() + " to be signed in");
	}
	
	protected void punchStudent(Student student){
		System.out.println(student.getFirstName() + " to be punched");
	}
	
	protected void startStudentSignIn(BellSchedule schedule){
		System.out.println("Starting sign in with schedule: "+schedule.getName());
	}
	
	protected void stopStudentSignIn(){
		System.out.println("Now closing student sign in");
	}
	
	protected void saveSignInSheet(){
		System.out.println("Save the sign in sheet");
	}
	
}

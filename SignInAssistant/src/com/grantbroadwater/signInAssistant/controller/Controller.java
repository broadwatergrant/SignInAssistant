package com.grantbroadwater.signInAssistant.controller;

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Student;

public class Controller {

	public Controller() {
		// TODO Auto-generated constructor stub
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
	
}

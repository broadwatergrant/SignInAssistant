package com.grantbroadwater.signInAssistant.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;

public class SignInSheet {

	private ArrayList<Student> list;
	
	public SignInSheet() {
		list = new ArrayList<Student>();
	}
	
	public void signStudentIn(Student s){
		list.add(s);
		s.setTimeIn(new GregorianCalendar());
		s.setTimeOut(null);
		s.setStatus(Status.IN);
	}
	
	public void signStudentOut(Student s){
		s.setTimeOut(new GregorianCalendar());
		s.setStatus(Status.OUT);
	}
	
	public void punchStudent(Student s){
		if(s.getStatus() == Status.IN)
			signStudentOut(s);
		else if(s.getStatus() == Status.OUT)
			signStudentIn(s);
	}
	
	public Student[] getSignInSheet(){
		return list.toArray(new Student[list.size()]);
	}

}

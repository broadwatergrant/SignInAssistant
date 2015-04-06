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
	
	private void signStudentIn(Student s){
		list.add(s);
		s.setTimeIn(new GregorianCalendar());
		s.setTimeOut(null);
		s.setStatus(Status.IN);
	}
	
	private void signStudentOut(Student s){
		for(int i=list.size() - 1; i >= 0; i--){
			if(list.get(i).essentialyEquals(s)){
				list.get(i).setTimeOut(new GregorianCalendar());
				list.get(i).setStatus(Status.OUT);
				s.setTimeOut(new GregorianCalendar());
				s.setStatus(Status.OUT);
				return;
			}
		}
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

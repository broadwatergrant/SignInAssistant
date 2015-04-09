package com.grantbroadwater.signInAssistant.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;

public class SignInSheet {

	private Model model;
	
	private ArrayList<Student> list;
	private ArrayList<Integer> classList;
	
	public SignInSheet(Model model) {
		list = new ArrayList<Student>();
		classList = new ArrayList<Integer>();
		this.model = model;
	}
	
	private void signStudentIn(Student s){
		s.setTimeIn(new GregorianCalendar());
		s.setTimeOut(null);
		s.setAutoSignedOut(false);
		s.setStatus(Status.IN);
		
		Student listStudent = new Student(s);
		listStudent.setTimeIn(new GregorianCalendar());
		listStudent.setTimeOut(null);
		listStudent.setAutoSignedOut(false);
		listStudent.setStatus(Status.IN);
		list.add(listStudent);
		
		classList.add(model.getCurrentHour());
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
	
	public void autoSignStudentOut(Student s){
		for(int i=list.size() - 1; i >= 0; i--){
			if(list.get(i).essentialyEquals(s)){
				list.get(i).setTimeOut(new GregorianCalendar());
				list.get(i).setStatus(Status.OUT);
				list.get(i).setAutoSignedOut(true);
				
				s.setTimeOut(new GregorianCalendar());
				s.setStatus(Status.OUT);
				s.setAutoSignedOut(true);
				return;
			}
		}
	}
	
	public Student[] getSignInSheet(){
		return list.toArray(new Student[list.size()]);
	}
	
	public Integer[] getParrallelHours(){
		return classList.toArray(new Integer[classList.size()]);
	}
	
	public void clear(){
		list.clear();
		classList.clear();
	}

}

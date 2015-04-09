package com.grantbroadwater.signInAssistant.controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.StudentPanel;
import com.grantbroadwater.util.Log;
import com.grantbroadwater.util.Log.LogType;

public class StudentPinDocumentListener implements DocumentListener {

	private Model model;
	private StudentPanel studentPanel;
	
	public StudentPinDocumentListener(Model model, StudentPanel studentPanel) {
		this.model = model;
		this.studentPanel = studentPanel;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		determinePinValidity();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		determinePinValidity();
	}

	private void determinePinValidity(){
		String pin = zeroTrim(studentPanel.getPin());
		if(model.studentIsValid(pin)){
			Student s = model.getStudentBody().get(pin);
			String status = (s.getStatus() == Status.IN) ? "out" : "in";
			studentPanel.setConfirmText("Sign " + status + " as: " + s.getName());
			studentPanel.setConfirmVisibility(true);
		}else{
			studentPanel.setConfirmVisibility(false);
		}
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		new Log(LogType.INFO, "ChangedUpdate Event fired");
	}
	
	public String zeroTrim(String str){
		if(str == null || str.length() == 0)
			return "";
		if(str.charAt(0) != '0')
			return str;
		else
			return zeroTrim(str.substring(1));
	}

}

package com.grantbroadwater.signInAssistant.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.InquirePanel;
import com.grantbroadwater.util.Log;
import com.grantbroadwater.util.Log.LogType;

public class InquireDocumentListener implements DocumentListener {

	private Model model;
	private InquirePanel inquirePanel;
	
	
	public InquireDocumentListener(Model model, InquirePanel inquirePanel) {
		this.model = model;
		this.inquirePanel = inquirePanel;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		respond();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		respond();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		new Log(LogType.INFO, "ChangedUpdate event fired");
	}
	
	private void respond(){
		String pin = inquirePanel.getPin();
		String firstName = inquirePanel.getFirstName();
		String lastName = inquirePanel.getLastName();
		
		Student[] results = model.getStudentsMatchingCriteria(pin, firstName, lastName);
		inquirePanel.setResultsFromInquiry(new ArrayList<Student>(Arrays.asList(results)));
	}

}

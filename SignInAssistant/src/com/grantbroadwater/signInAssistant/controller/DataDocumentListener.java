package com.grantbroadwater.signInAssistant.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.grantbroadwater.school.Person;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.DataPanel;
import com.grantbroadwater.util.Log;
import com.grantbroadwater.util.Log.LogType;

public class DataDocumentListener implements DocumentListener {

	private Model model;
	private DataPanel dataPanel;
	
	public DataDocumentListener(Model model, DataPanel dataPanel) {
		this.model = model;
		this.dataPanel = dataPanel;
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
		String pin = dataPanel.getPin();
		String first = dataPanel.getFirstName();
		String last = dataPanel.getLastName();
		
		ArrayList<Person> students = new ArrayList<Person>(Arrays.asList(model.getStudentsMatchingCriteria(pin, first, last)));
		ArrayList<Person> admins = new ArrayList<Person>(Arrays.asList(model.getAdministratorsMatchingCriteria(pin, first, last)));
		
		dataPanel.setStudentsResult(students);
		dataPanel.setAdminsResult(admins);
	}

}

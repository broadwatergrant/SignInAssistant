package com.grantbroadwater.signInAssistant.controller;

import java.awt.Color;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.InquirePanel;

public class InquireListSelectionListener implements ListSelectionListener {

	Model model;
	InquirePanel inquirePanel;

	public InquireListSelectionListener(Model model, InquirePanel inquirePanel) {
		this.model = model;
		this.inquirePanel = inquirePanel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Student s = inquirePanel.getSelectedStudent();
		if (s == null)
			return;
		Student validStudent = model.getStudentBody().get(s.getPin());
		String text = s.getName()
				+ " is "
				+ ((validStudent.getStatus() == Status.IN) ? "in the"
						: "out of the") + " library";
		Color color = (validStudent.getStatus() == Status.IN) ? new Color(0,
				153, 51) : Color.RED;
		inquirePanel.setStatusLabelView(text, color);

		inquirePanel.clearHistoryTable();
		Student[] students = model.getSignInSheet().getSignInSheet();
		boolean entry = false;
		for (Student student : students) {
			if (student.essentialyEquals(s)) {
				inquirePanel.addSingleHistoryEntry(student);
				entry = true;
			}
		}
		if (!entry)
			inquirePanel.addSingleEntry("No punches today", "", false);

	}

}

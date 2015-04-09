package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.StudentPanel;

public class StudentSignInActionListener implements ActionListener {

	private Model model;
	private StudentPanel studentPanel;
	private Controller controller;

	public StudentSignInActionListener(Model model, StudentPanel studentPanel,
			Controller controller) {
		this.model = model;
		this.studentPanel = studentPanel;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pin = zeroTrim(studentPanel.getPin());
		if (pin.equals(""))
			return;
		if (model.studentIsValid(pin)) {
			studentPanel.removeIncorrectPinNotification();
			controller.punchStudent(model.getStudentBody().get(pin));
			studentPanel.clearPinText();
		} else {
			studentPanel.notifyPinIsIncorrect();
			studentPanel.clearPinText();
		}
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

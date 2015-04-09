package com.grantbroadwater.signInAssistant.view;

import javax.swing.JFrame;

public class StudentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private StudentPanel studentPanel;

	public StudentFrame(){
		super("Sign In Assistant");
		studentPanel = new StudentPanel();
		this.setContentPane(studentPanel);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
		this.setMinimumSize(studentPanel.getPreferredSize());
		this.setMaximumSize(studentPanel.getPreferredSize());
	}
	
	public StudentPanel getStudentPanel(){
		return this.studentPanel;
	}
	
}

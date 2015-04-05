package com.grantbroadwater.signInAssistant.view;

import javax.swing.JFrame;

public class StudentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static StudentPanel studentPanel = new StudentPanel();

	public StudentFrame(){
		super("Sign In Assistant");
		this.setContentPane(studentPanel);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // TODO: Better Closing Procedure
		this.setMinimumSize(studentPanel.getPreferredSize());
		this.setMaximumSize(studentPanel.getPreferredSize());
	}
	
}

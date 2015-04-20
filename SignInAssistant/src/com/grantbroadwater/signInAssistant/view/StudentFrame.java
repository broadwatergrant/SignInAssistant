package com.grantbroadwater.signInAssistant.view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;

import com.grantbroadwater.util.Log;

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
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		
		if(gd.length >= 0){
			setLocation(getCenterOf(gd[gd.length - 1]));
		}else{
			new Log(Log.LogType.ERROR, "No Screens found");
		}
		
	}
	
	public Point getCenterOf(GraphicsDevice screen){
		int x = screen.getDefaultConfiguration().getBounds().x + screen.getDefaultConfiguration().getBounds().width / 2 - this.getWidth() / 2;
		int y = screen.getDefaultConfiguration().getBounds().height / 2 - this.getHeight() / 2;
		return new Point(x, y);
	}
	
	public StudentPanel getStudentPanel(){
		return this.studentPanel;
	}
	
}

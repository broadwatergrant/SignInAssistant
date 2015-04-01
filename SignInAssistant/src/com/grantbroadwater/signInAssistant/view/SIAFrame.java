package com.grantbroadwater.signInAssistant.view;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class SIAFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800, HEIGHT = 600;
	
	public SIAFrame() throws HeadlessException {
		super("Sign In Assistant");
		setSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}

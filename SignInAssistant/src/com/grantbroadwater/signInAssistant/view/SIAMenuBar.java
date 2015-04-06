package com.grantbroadwater.signInAssistant.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SIAMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu signInAssistantMenu, showMenu;
	private JMenuItem showInquire, showSignInSheet, showData;
	private JMenuItem signOut, startStopSignIn, reselectPinTextField, close;
	
	public SIAMenuBar() {
		super();
		setBackground(Color.GRAY);
		
		signInAssistantMenu = new JMenu("Sign In Assistant");
		signInAssistantMenu.setBackground(Color.GRAY);
		signInAssistantMenu.setMnemonic(KeyEvent.VK_A);
		signInAssistantMenu.getAccessibleContext().setAccessibleDescription(
				"Main actions of the Sign In Assistant");
		
		startStopSignIn = new JMenuItem("Start Sign In");
		signInAssistantMenu.add(startStopSignIn);
		
		signOut = new JMenuItem("Sign Out");
		signInAssistantMenu.add(signOut);
		
		reselectPinTextField = new JMenuItem("Reselect Pin");
		signInAssistantMenu.add(reselectPinTextField);
		
		signInAssistantMenu.addSeparator();
		
		close = new JMenuItem("Close Sign In Assistant");
		signInAssistantMenu.add(close);
		
		add(signInAssistantMenu);
		
		showMenu = new JMenu("Show");
		showMenu.setBackground(Color.GRAY);
		showMenu.setMnemonic(KeyEvent.VK_S);
		showMenu.getAccessibleContext().setAccessibleDescription(
				"Select which Screen to show");
		
		showInquire = new JMenuItem("Inquire");
		showMenu.add(showInquire);
		
		showSignInSheet = new JMenuItem("Sign In Sheet");
		showMenu.add(showSignInSheet);
		
		showData = new JMenuItem("Data");
		showMenu.add(showData);
		
		add(showMenu);
	}

	public void setStartStopSignInText(String text){
		startStopSignIn.setText(text);
	}
	
	public void addStartStopSignInSelectedFromMenuBarListener(ActionListener listener){
		startStopSignIn.addActionListener(listener);
	}
	
	public void addSignOutSelectedFromMenuBarListener(ActionListener listener){
		signOut.addActionListener(listener);
	}
	
	public void addReselectPinSelectedFromMenuBarListener(ActionListener listener){
		reselectPinTextField.addActionListener(listener);
	}
	
	public void addCloseSelectedFromMenuBarListener(ActionListener listener){
		close.addActionListener(listener);
	}
	
	public void addInquireSelectedFromMenuBarListener(ActionListener listener){
		showInquire.addActionListener(listener);
	}
	
	public void addSignInSheetSelectedFromMenuBarListener(ActionListener listener){
		showSignInSheet.addActionListener(listener);
	}
	
	public void addDataSelectedFromMenuBarListener(ActionListener listener){
		showData.addActionListener(listener);
	}
	
}

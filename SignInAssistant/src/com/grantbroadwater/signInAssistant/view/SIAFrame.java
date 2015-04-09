package com.grantbroadwater.signInAssistant.view;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SIAFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private CardLayout cardLayout;
	private String[] panelNames;
	private JPanel[] panels;
	private SIAMenuBar siaMenuBar;
	
	public SIAFrame(){
		super("Sign In Assistant");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		this.getContentPane().add(contentPane);
		
		siaMenuBar = new SIAMenuBar();
		
	}
	
	public SIAFrame(JPanel[] panels, String[] panelNames) throws HeadlessException {
		this();
		setPanels(panels, panelNames);
	}
	
	public void setPanels(JPanel[] panels, String[] panelNames){
		this.panels = panels;
		this.panelNames = panelNames;
		
		for (int i = 0; i < panels.length; i++) {
			contentPane.add(panels[i], panelNames[i]);
		}
	}
	
	public void showPanel(int panelNameIndex){
		showPanel(panelNames[panelNameIndex]);
	}
	
	public void showPanel(String panelName){
		JPanel newPanel = panels[getIndexOf(panelName)];
		this.setMinimumSize(newPanel.getPreferredSize());
		this.setMaximumSize(newPanel.getPreferredSize());
		
		cardLayout.show(contentPane, panelName);
	}
	
	private int getIndexOf(String name){
		for(int i=0; i<panelNames.length; i++){
			if(panelNames[i].equalsIgnoreCase(name))
				return i;
		}
		return -1;
	}
	
	public SIAMenuBar getSIAMenuBar(){
		return siaMenuBar;
	}
	
	public void setSIAMenuBarVisibility(boolean value){
		if(value)
			setJMenuBar(siaMenuBar);
		else
			setJMenuBar(null);
	}
	
	public void setSIAFrameWindowAdapter(WindowAdapter windowAdapter){
		this.addWindowListener(windowAdapter);
	}
	
	public int promptUserBeforeClose(){
		String[] buttonText = {"Close", "Dont Close"};
		int promptResult = JOptionPane.showOptionDialog(this, 
				"Are you sure you want to exit Sign In Assistant",
				"Sign In Assistant", 
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, 
				null,
				buttonText,
				buttonText[1]);
		return promptResult;
	}
	
	public int promptUserForSave(){
		String[] buttonText = {"Save", "Dont Save"};
		int promptResult = JOptionPane.showOptionDialog(this,
				"There is unsaved information in the sign in sheet." 
				+"\nWould you like to save now?",
				"Sign In Assistant",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null, buttonText,
				buttonText[0]);
		return promptResult;
	}
}

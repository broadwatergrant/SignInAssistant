package com.grantbroadwater.signInAssistant.view;

import java.awt.CardLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
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
	
	public SIAFrame(){
		super("Sign In Assistant");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		this.getContentPane().add(contentPane);
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
}

package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AdministratorPanel extends GPanel {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 600;

	@SuppressWarnings("unused")
	private BorderLayout borderLayout;
	private Font font;
	@SuppressWarnings("unused")
	private JComboBox<String> cbSchedule;
	private JButton btnSave;
	private JButton btnStart;
//	private SignInSheetTable signInSheetTable;
	
	public AdministratorPanel() {
		super(new BorderLayout());
		borderLayout = (BorderLayout) this.getLayout();
		
		font = new Font(this.getFont().getFontName(), this.getFont().getStyle(), 20);
		
		
		JPanel schedulePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		schedulePanel.setBackground(Color.WHITE);
		schedulePanel.setPreferredSize(new Dimension(WIDTH, 100));
		JPanel fillTop = new JPanel();
		fillTop.setPreferredSize(new Dimension(WIDTH, 25));
		fillTop.setBackground(Color.WHITE);
		schedulePanel.add(fillTop);
		JLabel lblToday = new JLabel("Today is a ");
		lblToday.setFont(font);
		schedulePanel.add(lblToday);
		this.add(schedulePanel, BorderLayout.NORTH);
		
		JPanel signInSheetPanel = new JPanel(new BorderLayout());
		signInSheetPanel.setBackground(Color.GREEN);
		
		JPanel signInSheetBarPanel = new JPanel(new BorderLayout());
		signInSheetBarPanel.setBackground(Color.PINK);
		
		btnSave = new JButton("Save");
		signInSheetBarPanel.add(btnSave, BorderLayout.WEST);
		
		JLabel lblSignInSheet = new JLabel("Sign In Sheet");
		lblSignInSheet.setFont(font);
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(lblSignInSheet);
		signInSheetBarPanel.add(textPanel, BorderLayout.CENTER);
		
		
		btnStart = new JButton("Start");
		signInSheetBarPanel.add(btnStart, BorderLayout.EAST);
		
		signInSheetPanel.add(signInSheetBarPanel, BorderLayout.NORTH);
		
//		signInSheetTable = new SignInSheetTable();
//		JScrollPane scrollPane = new JScrollPane(signInSheetTable);
//		
		//signInSheetPanel.add(scrollPane, BorderLayout.CENTER);
		
		this.add(signInSheetPanel, BorderLayout.CENTER);
		
		setBackground(Color.WHITE);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

//	public SignInSheetTable getSignInSheetTable(){
//		return signInSheetTable;
//	}
//	
}

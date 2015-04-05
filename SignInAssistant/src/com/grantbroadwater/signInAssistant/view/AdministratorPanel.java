package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Student;

public class AdministratorPanel extends GPanel {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 600;

	@SuppressWarnings("unused")
	private BorderLayout borderLayout;
	private Font font;
	private JComboBox<String> cbSchedule;
	private JButton btnSave;
	private JButton btnStart;
	private SignInSheetTable signInSheet;
	private SignInSheetTableModel model;
	private boolean stayAtBottom;
	
	public AdministratorPanel(ArrayList<BellSchedule> schedules) {
		super(new BorderLayout());
		borderLayout = (BorderLayout) this.getLayout();
		
		font = new Font(this.getFont().getFontName(), this.getFont().getStyle(), 20);
		stayAtBottom = true;
		
		JPanel schedulePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		schedulePanel.setBackground(Color.WHITE);
		schedulePanel.setPreferredSize(new Dimension(WIDTH, 100));
		JPanel fillTop = new JPanel();
		fillTop.setPreferredSize(new Dimension(WIDTH, 25));
		fillTop.setBackground(Color.WHITE);
		schedulePanel.add(fillTop);
		JLabel lblToday = new JLabel("  Today's schedule: ");
		lblToday.setFont(font);
		schedulePanel.add(lblToday);
		
		String[] scheduleArray = new String[schedules.size()];
		for(int i=0; i<scheduleArray.length; i++)
			scheduleArray[i] = schedules.get(i).getName();
			
		cbSchedule = new JComboBox<String>(scheduleArray);
		schedulePanel.add(cbSchedule);
		
		this.add(schedulePanel, BorderLayout.NORTH);
		
		JPanel signInSheetPanel = new JPanel(new BorderLayout());
		signInSheetPanel.setBackground(Color.WHITE);
		
		JPanel signInSheetBarPanel = new JPanel(new BorderLayout());
		signInSheetBarPanel.setBackground(Color.WHITE);
		
		btnSave = new JButton("Save");
		signInSheetBarPanel.add(btnSave, BorderLayout.WEST);
		
		JLabel lblSignInSheet = new JLabel("Sign In Sheet");
		lblSignInSheet.setFont(font);
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(lblSignInSheet);
		textPanel.setBackground(Color.WHITE);
		signInSheetBarPanel.add(textPanel, BorderLayout.CENTER);
		
		
		btnStart = new JButton("Start");
		signInSheetBarPanel.add(btnStart, BorderLayout.EAST);
		
		signInSheetPanel.add(signInSheetBarPanel, BorderLayout.NORTH);
		
		signInSheet = new SignInSheetTable();
		model = (SignInSheetTableModel) signInSheet.getModel();
		JScrollPane scrollPane = new JScrollPane(signInSheet);
		
		signInSheetPanel.add(scrollPane, BorderLayout.CENTER);
		
		this.add(signInSheetPanel, BorderLayout.CENTER);
		
		setBackground(Color.WHITE);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

//	public SignInSheetTableModel getSignInSheetTableModel(){
//		return model;
//	}
	
	public void addStudent(Student s){
		signInSheet.addStudent(s);
		if(stayAtBottom){
			signInSheet.scrollRectToVisible(signInSheet.getCellRect(signInSheet.getRowCount() - 1, 0, true));
		}
	}
	
	public String getSelectedScheduleName(){
		return (String)cbSchedule.getSelectedItem();
	}
	
	public void addStartActionListener(ActionListener listener){
		btnStart.addActionListener(listener);
	}
	
	public void addSaveActionListener(ActionListener listener){
		btnSave.addActionListener(listener);
	}
	
	public void ShowMessageDialog(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	
}

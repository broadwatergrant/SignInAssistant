package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.junit.Test;

import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;

public class SignInSheetTableTest implements ActionListener{
	
	static SignInSheetTableModel model;
	static JTextField tfFirst, tfLast;
	static SignInSheetTable table;
	
	public SignInSheetTableTest() {}
	
	private static JPanel createContentPanel(){
		JPanel panel = new JPanel(new GridLayout(1, 0));
		
		table = new SignInSheetTable();
		model = (SignInSheetTableModel)table.getModel();
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("Table Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = SignInSheetTableTest.createContentPanel();
		panel.setOpaque(true);
		
		JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tfFirst = new JTextField(8);
		tfLast = new JTextField(8);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new SignInSheetTableTest());
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		entryPanel.add(tfFirst);
		entryPanel.add(tfLast);
		entryPanel.add(btnAdd);
		entryPanel.add(btnDelete);
		
		JPanel housingPanel = new JPanel(new BorderLayout());
		housingPanel.add(entryPanel, BorderLayout.NORTH);
		housingPanel.add(panel, BorderLayout.CENTER);
		
		frame.setContentPane(housingPanel);
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String first = tfFirst.getText();
		String last = tfLast.getText();
		
		Student s = new Student(first, last, "11111");
		s.setStatus(Status.IN);
		s.setTimeIn(new GregorianCalendar());
		
		table.signStudentIn(s);
		
		table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
	}
	
	@Test
	public void test(){
		main(null);
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {}
		
	}
}

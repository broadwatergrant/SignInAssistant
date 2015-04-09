package com.grantbroadwater.signInAssistant.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextField;

import org.junit.Test;

import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;

public class SignInSheetTableModelTest {

	static SignInSheetTableModel model;
	static JTextField tfFirst, tfLast;
	static JTable table;

	@Test
	public void test() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {}
		
		GregorianCalendar gci = new GregorianCalendar();
		gci.set(Calendar.HOUR_OF_DAY, 7);
		gci.set(Calendar.MINUTE, 30);
		
		GregorianCalendar gco = new GregorianCalendar();
		gco.set(Calendar.HOUR_OF_DAY, 8);
		gco.set(Calendar.MINUTE, 15);
		
		Student s = new Student("Grant", "Broadwater", "10101", 12);
		s.setTimeIn(gci);
		s.setStatus(Status.IN);
		
		model.signStudentIn(new Student("Chris", "Rood", "20202"));
		model.signStudentIn(s);
		
		s.setTimeOut(gco);
		s.setStatus(Status.OUT);
		s.setAutoSignedOut(true);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {}
		
		model.signStudentOut(s);
		System.out.println("signed out");
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {}
	}

	private void createAndShowGUI() {
		JFrame frame = new JFrame("Table Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = createContentPanel();
		panel.setOpaque(true);

		JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tfFirst = new JTextField(8);
		tfLast = new JTextField(8);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String first = tfFirst.getText();
				String last = tfLast.getText();
				model.signStudentIn(new Student(first, last, "11111"));

				table.scrollRectToVisible(table.getCellRect(
						table.getRowCount() - 1, 0, true));
			}
		});
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

	private JPanel createContentPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 0));

		table = new JTable(new SignInSheetTableModel());
		model = (SignInSheetTableModel) table.getModel();
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);

		panel.add(scrollPane);

		return panel;
	}

}

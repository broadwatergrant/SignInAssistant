package com.grantbroadwater.signInAssistant.view;

import javax.swing.JTable;

import com.grantbroadwater.school.Student;

public class SignInSheetTable extends JTable {

	private static final long serialVersionUID = 1L;

	private SignInSheetTableModel model;
	
	public SignInSheetTable() {
		model = new SignInSheetTableModel();
		this.setFillsViewportHeight(true);
	}

	public SignInSheetTableModel getModel() {
		return model;
	}

	public void addStudent(Student student) {
		model.addStudent(student);
	}

	public void deleteRow(int rowIndex) {
		model.deleteRow(rowIndex);
	}

}

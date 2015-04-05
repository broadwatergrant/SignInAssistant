package com.grantbroadwater.signInAssistant.view;

import javax.swing.JTable;

import com.grantbroadwater.school.Student;
import com.grantbroadwater.util.Log;
import com.grantbroadwater.util.Log.LogType;

public class SignInSheetTable extends JTable{

	private static final long serialVersionUID = 1L;
	private SignInSheetTableModel model;
	
	public SignInSheetTable() {
		super(new SignInSheetTableModel());
		model = (SignInSheetTableModel) super.getModel();
		
		this.setFillsViewportHeight(true);
		new Log(LogType.DEBUG, "new Sign In sheet");
	}

	public void addStudent(Student student) {
		model.addStudent(student);
	}

	public void deleteRow(int rowIndex) {
		model.deleteRow(rowIndex);
	}
	
}

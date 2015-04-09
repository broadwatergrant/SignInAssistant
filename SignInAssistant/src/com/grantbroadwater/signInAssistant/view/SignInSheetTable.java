package com.grantbroadwater.signInAssistant.view;

import javax.swing.JTable;

import com.grantbroadwater.school.Student;

public class SignInSheetTable extends JTable{

	private static final long serialVersionUID = 1L;
	private SignInSheetTableModel model;
	
	public SignInSheetTable() {
		super(new SignInSheetTableModel());
		model = (SignInSheetTableModel) super.getModel();
		
		this.setFillsViewportHeight(true);
	}

	public void clear(){
		model.clear();
	}
	
	public void signStudentIn(Student student) {
		model.signStudentIn(student);
	}

	public void signStudentOut(Student student){
		model.signStudentOut(student);
	}
	
}

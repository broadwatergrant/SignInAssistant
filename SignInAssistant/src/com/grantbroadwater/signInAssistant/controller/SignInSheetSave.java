package com.grantbroadwater.signInAssistant.controller;

import java.io.File;

import javax.swing.JFileChooser;

import com.grantbroadwater.school.Student;

public class SignInSheetSave {

	private SignInSheetExcelWriter writer;
	
	public SignInSheetSave(Student[] students, Integer[] parrallelClasses) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		File userInput = null;
		
		do{
			int returnValue = fileChooser.showSaveDialog(null);
			
			if(returnValue == JFileChooser.APPROVE_OPTION){
				userInput = fileChooser.getSelectedFile();
			}else{
				return;
			}
			
			String fileName = userInput.getAbsolutePath();
			if(!fileName.substring(fileName.length() - 5).equalsIgnoreCase(".xlsx")){
				fileName += ".xlsx";
				userInput = new File(fileName);
			}
			
		}while(userInput == null);
		
		writer = new SignInSheetExcelWriter();
		writer.write(userInput, students, parrallelClasses);
		
	}

}

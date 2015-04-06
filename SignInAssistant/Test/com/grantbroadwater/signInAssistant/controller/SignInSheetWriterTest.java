package com.grantbroadwater.signInAssistant.controller;

import java.util.GregorianCalendar;

import org.junit.Test;

import com.grantbroadwater.school.Student;

public class SignInSheetWriterTest {

	@Test
	public void test() {
		SignInSheetExcelWriter writer = new  SignInSheetExcelWriter();
		
		Student[] students = {
				new Student("Grant", "Broadwater", "10101", 12),
				new Student("Chris", "Rood", "20202", 11),
				new Student("Sean", "Fox", "30303", 10),
				new Student("Nick", "Baczewski", "40404", 9)
		};
		
		students[0].setTimeIn(new GregorianCalendar());
		
		writer.write(System.clearProperty("user.home")+"/Documents/ExcelWorkbooks/WriteTest1.xlsx", students);
		
	}

}

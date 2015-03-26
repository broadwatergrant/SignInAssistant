package com.grantbroadwater.signInAssistant.model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.grantbroadwater.school.Student;

public class StudentExcelReaderTest {

	File f;
	StudentExcelReader reader;
	Student grant, chris, sean, nick;
	
	@Before
	public void before(){
		reader = new StudentExcelReader();
		grant = new Student("Grant", "Broadwater", "10101", 12);
		chris = new Student("chris", "rood", "20202", 11);
		sean = new Student("sEAN", "foX", "30303", 10);
		nick = new Student("NicK", "BACZEWskI", "40404", 9);
	}
	
	@Test
	public void test() {
		f = new File("/Users/GrantBroadwater/Documents/StudentTest1.xlsx");
		ArrayList<Object> result = reader.readAndReturn(f, 0);
		
		assertEquals(grant, result.get(0));
		assertEquals(chris, result.get(1));
		assertEquals(sean, result.get(2));
		assertEquals(nick, result.get(3));
		
	}

	@Test
	public void testTwo(){
		f = new File("/Users/GrantBroadwater/Documents/StudentTest1.xlsx");
		ArrayList<Object> result = reader.readAndReturn(f, 1);
		
		assertEquals(grant, result.get(0));
		assertEquals(chris, result.get(1));
		assertEquals(sean, result.get(2));
		assertEquals(nick, result.get(3));
	}
	
}

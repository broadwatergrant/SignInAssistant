package com.grantbroadwater.signInAssistant.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ExcelReaderTest {

	File f;
	ExcelReader reader;
	
	@Before
	public void before(){
		reader = new ExcelReader();
	}
	
	@Test
	public void testOne() {
		f = new File("/Users/GrantBroadwater/Documents/Workbook1.xlsx");
		String[][] result = reader.readAndReturn(f);
		
		assertEquals("Cell A1", result[0][0]);
		assertEquals("Cell B1", result[0][1]);
		assertEquals("Cell A2", result[1][0]);
		assertEquals("Cell B2", result[1][1]);
	}
	
	@Test
	public void testTwo(){
		f = new File("/Users/GrantBroadwater/Documents/Workbook2.xlsx");
		String[][] result = reader.readAndReturn(f);
		
		assertEquals("Grant", result[0][0]);
		assertEquals("true", result[1][0]);
		assertEquals("12345", result[2][0]);
		assertEquals("12345.2", result[3][0]);
	}
	

}

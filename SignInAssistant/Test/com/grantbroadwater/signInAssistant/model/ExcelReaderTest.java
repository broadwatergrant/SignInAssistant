package com.grantbroadwater.signInAssistant.model;


import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class ExcelReaderTest {

//	File f;
//	ExcelReader reader;
//	
//	@Before
//	public void before(){
//		reader = new ExcelReader();
//	}
//	
//	@Test
//	public void testOne() {
//		f = new File("/Users/GrantBroadwater/Documents/Workbook1.xlsx");
//		ArrayList<Object> result = reader.readAndReturn(f);
//		
//		assertEquals("Cell A1", result.get(0));
//		assertEquals("Cell B1", result.get(1));
//		assertEquals("Cell A2", result.get(2));
//		assertEquals("Cell B2", result.get(3));
//	}
//
//	@Test
//	public void testTwo(){
//		f = new File("/Users/GrantBroadwater/Documents/Workbook2.xlsx");
//		ArrayList<Object> result = reader.readAndReturn(f);
//
//		assertEquals("Grant", result.get(0));
//		assertEquals(true, result.get(1));
//		assertEquals(12345, result.get(2));
//		assertEquals(12345.2, result.get(3));
//		System.out.println(result.get(4));
//	}
	
	@Test
	public void testEmptyAdmin(){
		File f = new File("/Users/GrantBroadwater/Documents/ExcelWorkbooks/SampleData.xlsx");
		ExcelReader reader = new StudentExcelReader();
		ArrayList<Object> result = reader.readAndReturn(f, 0);
		
		System.out.println(result.size());
		for (Object object : result) {
			System.out.println(object);
		}
	}

	
}

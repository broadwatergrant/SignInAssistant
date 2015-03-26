package com.grantbroadwater.signInAssistant.model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.grantbroadwater.school.Administrator;

public class AdminExcelReaderTest {

	AdminExcelReader reader;
	Administrator grant, chris, sean, nick;
	
	@Before
	public void before(){
		reader = new AdminExcelReader();
		grant = new Administrator("grant", "broadwater", "10101");
		chris = new Administrator("CHRIS", "ROOD", "20202");
		sean = new Administrator("Sean", "Fox", "30303");
		nick = new Administrator("Nick", "Baczewski", "40404");
	}
	
	@Test
	public void test() {
		File f = new File("/Users/GrantBroadwater/Documents/AdminTest1.xlsx");
		ArrayList<Object> result = reader.readAndReturn(f, 0);
		
		assertEquals(grant, result.get(0));
		assertEquals(chris, result.get(1));
		assertEquals(sean, result.get(2));
		assertEquals(nick, result.get(3));
	}

}

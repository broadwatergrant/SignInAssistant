package com.grantbroadwater.signInAssistant.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ModelTest {

	@Test
	public void test() {
		Model model = new Model();
		File f = model.getExcelFileLocationFromUser();
		System.out.println(f.getAbsolutePath());
	}

}

package com.grantbroadwater.signInAssistant.model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.Test;

import com.grantbroadwater.school.ClassPeriod;

public class ModelTest {

	@Test
	public void test() {
		Model model = new Model();

		model.loadData();
		
		assertEquals("Chris", model.getStudentBody().get("20202").getFirstName());
		assertEquals("Grant", model.getAdministration().get("10101").getFirstName());
		System.out.println(model.getSchedules()[0]);
	}

}

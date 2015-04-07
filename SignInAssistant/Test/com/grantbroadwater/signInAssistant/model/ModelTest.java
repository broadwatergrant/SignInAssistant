package com.grantbroadwater.signInAssistant.model;

import org.junit.Test;

import com.grantbroadwater.school.BellSchedule;

public class ModelTest {

	Model model;
	
//	@Test
//	public void test() {
//		model = new Model();
//
//		model.loadData();
//		
//		assertEquals("Chris", model.getStudentBody().get("20202").getFirstName());
//		assertEquals("Grant", model.getAdministration().get("10101").getFirstName());
//		System.out.println(model.getSchedules()[0]);
//	}
//	
//	@Test
//	public void testStudentChooser(){
//		model = new Model();
//		
//		Student s1 = new Student("Grant", "Broadwater", "10101", 12);
//		Student s2 = new Student("Chris", "Rood", "10101", 12);
//		Student result = model.determineWhichStudentToSave(s1, s2);
//		System.out.println(result.getFirstName());
//	}
//	
//	@Test
//	public void testAdminChooser(){
//		model = new Model();
//		
//		Administrator s1 = new Administrator("Grant", "Broadwater", "10101");
//		Administrator s2 = new Administrator("Chris", "Rood", "10101");
//		Administrator result = model.determineWhichAdministratorToSave(s1, s2);
//		System.out.println(result.getFirstName());
//		
//	}
	
	@Test
	public void testCurrentHour(){
		model = new Model();
		model.loadData();
		
		BellSchedule schedule = model.getScheduleWithName("Custom Day");
		model.setSelectedSchedule(schedule);
		int result = model.getCurrentHour();
		
		System.out.println(result);
	}

}

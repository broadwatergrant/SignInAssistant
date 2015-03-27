package com.grantbroadwater.signInAssistant.model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.ClassPeriod;

public class ScheduleExcelReaderTest {

	ScheduleExcelReader reader;
	File f;
	ArrayList<ClassPeriod> periods;
	
	@Test
	public void test() {
		reader = new ScheduleExcelReader();
		periods = new ArrayList<ClassPeriod>();
		
		f = new File("/Users/GrantBroadwater/Documents/ScheduleTest1.xlsx");
		ArrayList<Object> result = reader.readAndReturn(f, 0);
		
		GregorianCalendar start = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 7);
		start.set(Calendar.MINUTE, 25);
		GregorianCalendar stop = new GregorianCalendar();
		stop.set(Calendar.HOUR_OF_DAY, 8);
		stop.set(Calendar.MINUTE, 15);
		periods.add(new ClassPeriod(start, stop));
		
		start = new GregorianCalendar();
		stop = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 8);
		start.set(Calendar.MINUTE, 21);
		stop.set(Calendar.HOUR_OF_DAY, 9);
		stop.set(Calendar.MINUTE, 11);
		periods.add(new ClassPeriod(start, stop));
		
		start = new GregorianCalendar();
		stop = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 9);
		start.set(Calendar.MINUTE, 17);
		stop.set(Calendar.HOUR_OF_DAY, 10);
		stop.set(Calendar.MINUTE, 07);
		periods.add(new ClassPeriod(start, stop));

		start = new GregorianCalendar();
		stop = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 10);
		start.set(Calendar.MINUTE, 13);
		stop.set(Calendar.HOUR_OF_DAY, 11);
		stop.set(Calendar.MINUTE, 03);
		periods.add(new ClassPeriod(start, stop));
		
		BellSchedule expected1 = new BellSchedule("A Day", periods);
		BellSchedule result1 = (BellSchedule) result.get(0);
		
		assertEquals(expected1, result1);
		
		periods = new ArrayList<ClassPeriod>();

		start = new GregorianCalendar();
		stop = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 7);
		start.set(Calendar.MINUTE, 25);
		stop.set(Calendar.HOUR_OF_DAY, 9);
		stop.set(Calendar.MINUTE, 00);
		periods.add(new ClassPeriod(start, stop));

		start = new GregorianCalendar();
		stop = new GregorianCalendar();
		start.set(Calendar.HOUR_OF_DAY, 9);
		start.set(Calendar.MINUTE, 06);
		stop.set(Calendar.HOUR_OF_DAY, 10);
		stop.set(Calendar.MINUTE, 41);
		periods.add(new ClassPeriod(start, stop));
		
		BellSchedule expected2 = new BellSchedule("B Day", periods);
		
		assertEquals(expected2, result.get(1));
		
	}

}

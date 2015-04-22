package com.grantbroadwater.signInAssistant.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.grantbroadwater.school.Student;
import com.grantbroadwater.util.Log;

public class SignInSheetExcelWriter {

	private File outputFile;
	private Student[] students;
	private Integer[] parallelClasses;
	
	public SignInSheetExcelWriter() {
		
	}
	
	public void setOutputFile(File outputFile){
		this.outputFile = outputFile;
	}

	public void setOutputFile(String fileName){
		this.setOutputFile(new File(fileName));
	}
	
	public void setStudents(Student[] students){
		this.students = students;
	}
	
	public Integer[] getParallelClasses() {
		return parallelClasses;
	}

	public void setParallelClasses(Integer[] parallelClasses) {
		this.parallelClasses = parallelClasses;
	}

	public void write(File outputFile){
		setOutputFile(outputFile);
		write();
	}
	
	public void write(String fileName){
		setOutputFile(fileName);
		write();
	}
	
	public void write(Student[] students){
		setStudents(students);
	}
	
	public void write(File outputFile, Student[] students){
		setOutputFile(outputFile);
		setStudents(students);
		write();
	}
	
	public void write(String fileName, Student[] students){
		setOutputFile(fileName);
		setStudents(students);
		write();
	}
	
	public void write(File outputFile, Student[] students, Integer[] parallelClasses){
		setOutputFile(outputFile);
		setStudents(students);
		setParallelClasses(parallelClasses);
		write();
	}
	
	public void write(String fileName, Student[] students, Integer[] parallelClasses){
		setOutputFile(fileName);
		setStudents(students);
		setParallelClasses(parallelClasses);
		write();
	}
	
	public boolean write(){
		if(outputFile == null || students == null || students.length == 0)
			return false;
		
		try {
			Workbook wb = new XSSFWorkbook();
			
			/* ----- Sign In Sheet ----- */
			Sheet signInSheet = wb.createSheet("Sign In Sheet");
			
			Row titleRow = signInSheet.createRow(0);
			
			titleRow.createCell(0).setCellValue("Pin");
			titleRow.createCell(1).setCellValue("First Name");
			titleRow.createCell(2).setCellValue("Last Name");
			titleRow.createCell(3).setCellValue("Grade");
			titleRow.createCell(4).setCellValue("Time In");
			titleRow.createCell(5).setCellValue("Time Out");
			titleRow.createCell(6).setCellValue("Auto Signed Out");
			titleRow.createCell(7).setCellValue("Hour");
			
			for(short r=0; r<students.length; r++){
				Row row = signInSheet.createRow(r + 1);
				Student s = students[r];
				
				row.createCell(0).setCellValue(s.getPin());
				row.createCell(1).setCellValue(s.getFirstName());
				row.createCell(2).setCellValue(s.getLastName());
				row.createCell(3).setCellValue(s.getGradeLevel());
				row.createCell(4).setCellValue(formatTime(s.getTimeIn()));
				row.createCell(5).setCellValue(formatTime(s.getTimeOut()));
				row.createCell(6).setCellValue(s.isAutoSignedOut());
				row.createCell(7).setCellValue(parallelClasses[r]);
				
			}
			
			/* ----- Stat Sheet ----- */
			Sheet statSheet = wb.createSheet("Stats");
			int rowCount = 0;
			
			Row totalCount = statSheet.createRow(rowCount++);
			totalCount.createCell(0).setCellValue("Total Count:");
			totalCount.createCell(1).setCellValue(students.length);
			
			Integer[] gradeLevels = getDifferentGradeLevels();
			
			for(int gl=0; gl<gradeLevels.length; gl++){
				Row row = statSheet.createRow(rowCount++);
				
				row.createCell(0).setCellValue("Grade " + gradeLevels[gl] + " count:");
				row.createCell(1).setCellValue(getGradeLevelCount(gradeLevels[gl]));
				
			}
			
			Integer[] hours = getDifferentHours();
			
			for(int cl=0; cl<hours.length; cl++){
				Row row = statSheet.createRow(rowCount++);
				
				row.createCell(0).setCellValue("Hour " + hours[cl] + " count:");
				row.createCell(1).setCellValue(getHourCount(hours[cl]));
			}
			
			System.out.println(outputFile.getAbsolutePath());
			outputFile.createNewFile();
			FileOutputStream output = new FileOutputStream(outputFile);
			wb.write(output);
			wb.close();
			output.close();
			
			return true;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
			return false;
		}
		
	}
	
	private String formatTime(GregorianCalendar gc){
		if(gc == null){
			return "NA";
		}else{
			int min = gc.get(Calendar.MINUTE);
			String strMin = (min < 10) ? "0" + min : "" + min;
			int hour = gc.get(Calendar.HOUR_OF_DAY);
			hour = (hour > 12) ? hour - 12 : hour;
			return hour + ":" + strMin;
		}
	}
	
	private Integer[] getDifferentGradeLevels(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i=0; i<students.length; i++){
			if(!containsValue(result, students[i].getGradeLevel()))
				result.add(students[i].getGradeLevel());
		}
		
		return result.toArray(new Integer[result.size()]);
	}
	
	private boolean containsValue(ArrayList<Integer> list, Integer value){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).equals(value))
				return true;
		}
		return false;
	}
	
	private int getGradeLevelCount(int gradeLevel){
		int count = 0;
		
		for(Student s : students)
			if(s.getGradeLevel() == gradeLevel)
				count++;
		
		return count;
	}
	
	private Integer[] getDifferentHours(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i=0; i<parallelClasses.length; i++){
			if(!containsValue(result, parallelClasses[i]))
				result.add(parallelClasses[i]);
		}
		
		return result.toArray(new Integer[result.size()]);
	}
	
	private int getHourCount(int hour){
		int count = 0;
		
		for(Integer i : parallelClasses)
			if(i == hour)
				count++;
		
		return count;
	}
	
}

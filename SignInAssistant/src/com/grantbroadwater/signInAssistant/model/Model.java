package com.grantbroadwater.signInAssistant.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.school.Administrators;
import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.school.Students;
import com.grantbroadwater.util.Log;

public class Model {

	private Students studentBody;
	private Administrators administration;
	private BellSchedule[] schedules;
	
	private File excelFile, excelFileLocation;
	private ExcelReader reader;
	
	public static final String HOME = System.getProperty("user.home"),
			SEP = File.separator,
			HUB = HOME + SEP + "Broadwater Software" + SEP + "Sign In Assistant" + SEP,
			REF = HUB + SEP + "ref" + SEP;
	
	public Model() {
		studentBody = new Students();
		administration = new Administrators();
	}
	
	public void loadData(){
		loadReferenceData();
		loadApplicationData();
	}
	
	public void loadReferenceData(){
		File ref = new File(REF);
		if(!ref.exists())
			ref.mkdirs();
		ref = null;
		
		excelFileLocation = new File(REF + "excelFileLocation.data");
		try {
			excelFileLocation.createNewFile();
			String location = readFile(excelFileLocation);
			excelFile = (new File(location).exists()) ? new File(location) : getExcelFileLocationFromUser();
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
		}
	}
	
	public void loadApplicationData(){
		reader = new StudentExcelReader();
		ArrayList<Object> loadedStudents = reader.readAndReturn(excelFile, 0);
		studentBody.setStudents(loadedStudents.toArray(new Student[loadedStudents.size()]));
	
		reader = new AdminExcelReader();
		ArrayList<Object> loadedAdmin = reader.readAndReturn(excelFile, 1);
		administration.setAdministrators(loadedAdmin.toArray(new Administrator[loadedAdmin.size()]));
	
		reader = new ScheduleExcelReader();
		ArrayList<Object> loadedSchedules = reader.readAndReturn(excelFile, 2);
		schedules = loadedSchedules.toArray(new BellSchedule[loadedSchedules.size()]);
	}
	
	private String readFile(File f){
		try {
			FileInputStream input = new FileInputStream(f);
			String result = "";
			
			while(input.available() > 0){
				result += (char)input.read();
			}
			
			input.close();
			return result;
		} catch (FileNotFoundException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
			return null;
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
			return null;
		}
	}
	
	private File getExcelFileLocationFromUser(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new ExcelFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		File result = null;
		
		do{
			int returnValue = fileChooser.showDialog(null, "Select Excel File");
				
			if(returnValue == JFileChooser.APPROVE_OPTION){
				result = fileChooser.getSelectedFile();
			}else{
				result = null;
			}
			
			if(ExcelFileFilter.getExtension(result) == null)
				result = new File(result.getAbsolutePath() + ".xlsx"); // TODO: accept more extensions
			
		}while(result == null || !result.exists());
		
		save(excelFileLocation, result.getAbsolutePath());
		return result;
	}
	
	private void save(File f, String s){
		try {
			FileOutputStream output = new FileOutputStream(f);
			
			for(char c : s.toCharArray())
				output.write((byte)c);
			
			output.close();
		} catch (FileNotFoundException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Students getStudentBody(){
		return this.studentBody;
	}
	
	public Administrators getAdministration() {
		return administration;
	}
	
	public BellSchedule[] getSchedules() {
		return schedules;
	}
}

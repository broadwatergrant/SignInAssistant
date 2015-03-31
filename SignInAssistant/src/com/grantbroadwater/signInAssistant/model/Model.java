package com.grantbroadwater.signInAssistant.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import javax.swing.JFileChooser;

import com.grantbroadwater.school.Administrators;
import com.grantbroadwater.school.BellSchedule;
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
	
	protected File getExcelFileLocationFromUser(){
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
			
		}while(result == null || !result.exists());
		
		return result;
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

	public ExcelReader getReader() {
		return reader;
	}

}

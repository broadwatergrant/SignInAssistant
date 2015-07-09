package com.grantbroadwater.signInAssistant.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.school.Administrators;
import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.ClassPeriod;
import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.school.Students;
import com.grantbroadwater.util.Log;

public class Model {

	private Students studentBody;
	private Administrators administration;
	private BellSchedule[] schedules;
	private BellSchedule selectedSchedule;
	private SignInSheet signInSheet;
	
	private File excelFile, excelFileLocation;
	private ExcelReader reader;

	public static final String HOME = System.getProperty("user.home"),
			SEP = File.separator, HUB = HOME + SEP + "Broadwater Software"
					+ SEP + "Sign In Assistant" + SEP, REF = HUB + SEP + "ref"
					+ SEP;

	public Model() {
		studentBody = new Students();
		administration = new Administrators();
		signInSheet = new SignInSheet(this);
		this.markInformationSavedAs(true);
	}

	public BellSchedule getSelectedSchedule() {
		return selectedSchedule;
	}

	public void setSelectedSchedule(BellSchedule selectedSchedule) {
		this.selectedSchedule = selectedSchedule;
	}

	public BellSchedule getScheduleWithName(String scheduleName){
		for(BellSchedule schedule : schedules)
			if(schedule.getName().equalsIgnoreCase(scheduleName))
				return schedule;
		return null;
	}
	
	public String getExcelFileLocation(){
		return readFile(excelFileLocation);
	}
	
	public String getExcelFileName(){
		File f = new File(readFile(excelFileLocation));
		return f.getName();
	}
	
	public void loadData() {
		loadReferenceData();
		loadApplicationData();
	}

	public void loadReferenceData() {
		File ref = new File(REF);
		if (!ref.exists())
			ref.mkdirs();
		ref = null;

		excelFileLocation = new File(REF + "excelFileLocation.data");
		try {
			excelFileLocation.createNewFile();
			String location = readFile(excelFileLocation);
			excelFile = (new File(location).exists()) ? new File(location)
					: getExcelFileLocationFromUser();
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
		}
	}

	public void loadApplicationData() {
		// Read students
		reader = new StudentExcelReader();
		ArrayList<Object> loadedStudents = reader.readAndReturn(excelFile, 0);
		for (Object o : loadedStudents) {
			Student s = (Student) o;
			if (studentBody.containsKey(s.getPin()))
				s = determineWhichStudentToSave(s, studentBody.get(s.getPin()));
			if(s != null)
				studentBody.put(s.getPin(), s);
		}
		
		// Read Administrators
		reader = new AdminExcelReader();
		ArrayList<Object> loadedAdmin = reader.readAndReturn(excelFile, 1);
		for (Object o : loadedAdmin) {
			Administrator a = (Administrator) o;
			if (administration.containsKey(a.getPin()))
				a = determineWhichAdministratorToSave(a,
						administration.get(a.getPin()));
			if(a != null)
				administration.put(a.getPin(), a);
		}

		// Read schedules
		reader = new ScheduleExcelReader();
		ArrayList<Object> loadedSchedules = reader.readAndReturn(excelFile, 2);
		schedules = loadedSchedules.toArray(new BellSchedule[loadedSchedules
				.size()]);
	}

	private String readFile(File f) {
		try {
			if(!f.exists()){
				f.createNewFile();
				return null;
			}
			FileInputStream input = new FileInputStream(f);
			String result = "";

			while (input.available() > 0) {
				result += (char) input.read();
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

	public File getExcelFileLocationFromUser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new ExcelFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);

		File result = null;

		do {
			int returnValue = fileChooser.showDialog(null, "Select Excel File");

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				result = fileChooser.getSelectedFile();
			} else {
				result = null;
			}
			
			if (ExcelFileFilter.getExtension(result) == null)
				// TODO: accept more extensions
				result = new File(result.getAbsolutePath() + ".xlsx");

		} while (result == null || !result.exists());

		save(excelFileLocation, result.getAbsolutePath());
		return result;
	}

	private void save(File f, String s) {
		try {
			FileOutputStream output = new FileOutputStream(f);

			for (char c : s.toCharArray())
				output.write((byte) c);

			output.close();
		} catch (FileNotFoundException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
			e.printStackTrace();
		}
	}

	protected Student determineWhichStudentToSave(Student s1, Student s2) {
		if (s1.essentialyEquals(s2))
			return s1; // Both are same person, just duplicate entry
		Object[] options = { s1.getName(), s2.getName() };
		int result = JOptionPane.showOptionDialog(null, s1.getName() + " and "
				+ s2.getName() + " both have the same pin.\n"
				+ "Please select the correct student to add",
				"Student Confilct", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == JOptionPane.YES_OPTION)
			return s1;
		else if (result == JOptionPane.NO_OPTION)
			return s2;
		return null;
	}

	protected Administrator determineWhichAdministratorToSave(Administrator a1,
			Administrator a2) {
		if (a1.equals(a2))
			return a1; // Both are same, irrelevant which to add
		Object[] options = { a1.getName(), a2.getName() };
		int result = JOptionPane.showOptionDialog(null, a1.getName() + " and "
				+ a2.getName() + " both have the same pin.\n"
				+ "Please select the correct administrator to add.",
				"Administrator Conflict", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == JOptionPane.YES_OPTION)
			return a1;
		else if (result == JOptionPane.NO_OPTION)
			return a2;
		return null;
	}

	/* ---------- Accessors ---------- */
	
	public Students getStudentBody() {
		return this.studentBody;
	}

	public Administrators getAdministration() {
		return administration;
	}

	public BellSchedule[] getSchedules() {
		return schedules;
	}
	
	public SignInSheet getSignInSheet() {
		return signInSheet;
	}
	
	/* ---------- Data needed for views ---------- */

	public Student[] getStudentsMatchingCriteria(String pin, String firstName, String lastName){
		if(pin == null)
			pin = "";
		if(firstName == null)
			firstName = "";
		if(lastName == null)
			lastName = "";
		
		firstName = firstName.toLowerCase();
		lastName = lastName.toLowerCase();
		
		// TODO: Better define results
		
		if(!pin.equals("") && studentBody.containsKey(pin)){
			Student[] result = new Student[1];
			result[0] = studentBody.get(pin);
			return result;
		}
		if(firstName.equals("")){
			ArrayList<Student> list = new ArrayList<Student>();
			for(String  k: studentBody.keySet()){
				Student s = studentBody.get(k);
				if(s.getLastName().toLowerCase().indexOf(lastName) != -1)
					list.add(s);
			}
			return list.toArray(new Student[list.size()]);
		}else if(lastName.equals("")){
			ArrayList<Student> list = new ArrayList<Student>();
			for(String  k: studentBody.keySet()){
				Student s = studentBody.get(k);
				if(s.getFirstName().toLowerCase().indexOf(firstName) != -1)
					list.add(s);
			}
			return list.toArray(new Student[list.size()]);
		}else if(!firstName.equals("") && !lastName.equals("")){
			ArrayList<Student> list = new ArrayList<Student>();
			for(String  k: studentBody.keySet()){
				Student s = studentBody.get(k);
				if(s.getLastName().toLowerCase().indexOf(lastName) != -1 && s.getFirstName().toLowerCase().indexOf(firstName) != -1)
					list.add(s);
			}
			return list.toArray(new Student[list.size()]);
		}else{
			return new Student[0];
		}
	}
	
	public Administrator[] getAdministratorsMatchingCriteria(String pin, String firstName, String lastName){
		if(pin == null)
			pin = "";
		if(firstName == null)
			firstName = "";
		if(lastName == null)
			lastName = "";
		
		firstName = firstName.toLowerCase();
		lastName = lastName.toLowerCase();
		
		if(!pin.equals("") && administration.containsKey(pin)){
			Administrator[] result = new Administrator[1];
			result[0] = administration.get(pin);
			return result;
		}
		
		if(firstName.equals("")){
			ArrayList<Administrator> list = new ArrayList<Administrator>();
			for(String  k: administration.keySet()){
				Administrator s = administration.get(k);
				if(s.getLastName().toLowerCase().indexOf(lastName) != -1)
					list.add(s);
			}
			return list.toArray(new Administrator[list.size()]);
		}else if(lastName.equals("")){
			ArrayList<Administrator> list = new ArrayList<Administrator>();
			for(String  k: administration.keySet()){
				Administrator s = administration.get(k);
				if(s.getFirstName().toLowerCase().indexOf(firstName) != -1)
					list.add(s);
			}
			return list.toArray(new Administrator[list.size()]);
		}else if(!firstName.equals("") && !lastName.equals("")){
			ArrayList<Administrator> list = new ArrayList<Administrator>();
			for(String  k: administration.keySet()){
				Administrator s = administration.get(k);
				if(s.getLastName().toLowerCase().indexOf(lastName) != -1 && s.getFirstName().toLowerCase().indexOf(firstName) != -1)
					list.add(s);
			}
			return list.toArray(new Administrator[list.size()]);
		}else{
			return new Administrator[0];
		}
	}
	
	public boolean adminIsValid(String pin, String name){
		return administration.containsKey(pin)
				&& (administration.get(pin).getFirstName().equalsIgnoreCase(name)
						|| administration.get(pin).getLastName().equalsIgnoreCase(name));

	}
	
	public boolean studentIsValid(String pin){
		return studentBody.containsKey(pin);
	}
	
	public int getCurrentHour(){
		GregorianCalendar now = new GregorianCalendar();
		
		if (selectedSchedule.get(0).getStart().compareTo(now) > 0){
			return 0;
		}
		
		for(int i=0; i<selectedSchedule.size(); i++){
			ClassPeriod cp = selectedSchedule.get(i);
			if(compare(cp.getStop(), now) > 0)
				return i + 1;
		}
		
		return selectedSchedule.size() + 1;
		
	}
	
	public int compare(GregorianCalendar arg0, GregorianCalendar arg1){
		if(arg0.get(Calendar.HOUR_OF_DAY) > arg1.get(Calendar.HOUR_OF_DAY)){
			return 1;
		}else if(arg0.get(Calendar.HOUR_OF_DAY) == arg1.get(Calendar.HOUR_OF_DAY)){
			if(arg0.get(Calendar.MINUTE) > arg1.get(Calendar.MINUTE)){
				return 1;
			}else if(arg0.get(Calendar.MINUTE) == arg1.get(Calendar.MINUTE)){
				return 0;
			}else if(arg0.get(Calendar.MINUTE) < arg1.get(Calendar.MINUTE)){
				return -1;
			}
			return 0;
		}else if(arg0.get(Calendar.HOUR_OF_DAY) < arg1.get(Calendar.HOUR_OF_DAY)){
			return -1;
		}
		return 0;
	}
	
	public boolean allInformationSaved(){
		String result = readFile(new File(REF + "dataIsSaved.value"));
		if(result == null)
			return false;
		try {
			return Boolean.parseBoolean(result);
		} catch (Exception e) {
			return false;
		}
	}
	
	public void markInformationSavedAs(boolean value){
		save(new File(REF + "dataIsSaved.value"), Boolean.toString(value));
	}
	
	public void resetStudentBody(){
		for(String pin : studentBody.keySet()){
			Student student = studentBody.get(pin);
			student.setTimeIn(null);
			student.setTimeOut(null);
			student.setStatus(Status.OUT);
			student.setAutoSignedOut(false);
		}
	}
	
}

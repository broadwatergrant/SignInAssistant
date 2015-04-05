package com.grantbroadwater.signInAssistant.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

import com.grantbroadwater.school.Student;

public class SignInSheetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"First Name", "Last Name", "Status", "Time in", "Time Out", "Auto Signed Out"};
	private ArrayList<ArrayList<Object>> data;
	
	public SignInSheetTableModel() {
		data = new ArrayList<ArrayList<Object>>();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex).get(columnIndex);
	}
	
	@Override
	public String getColumnName(int columnIndex){
		return columnNames[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return false;
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		data.get(rowIndex).set(columnIndex, value);
	}
	
	public void addStudent(Student student){
		ArrayList<Object> newEntry = new ArrayList<Object>();
		
		newEntry.add(student.getFirstName());
		newEntry.add(student.getLastName());
		newEntry.add(student.getStatus());
		newEntry.add((student.getTimeIn() == null) ? "" : formatTime(student.getTimeIn()));
		newEntry.add((student.getTimeOut() == null) ? "" : formatTime(student.getTimeOut()));
		newEntry.add(student.isAutoSignedOut());
		
		data.add(newEntry);
		
		fireTableRowsInserted(data.size() - 1, data.size());
	}
	
	public void deleteRow(int rowIndex){
		data.remove(rowIndex);
		fireTableRowsDeleted(rowIndex - 1, rowIndex);
	}
	
	private String formatTime(GregorianCalendar gc){
		return gc.get(Calendar.HOUR) + ":" + gc.get(Calendar.MINUTE);
	}
}

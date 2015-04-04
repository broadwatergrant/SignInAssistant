package com.grantbroadwater.signInAssistant.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

import com.grantbroadwater.school.Student;

public class SignInSheetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] columnNames = {"First Name", "Last Name", "Status", "Time in", "Time Out", "Auto Signed Out"};
	private ArrayList<Student> data;
	
	public SignInSheetTableModel() {
		data = new ArrayList<Student>();
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
		Student s = data.get(rowIndex);
		switch(columnIndex){
		case 0:
			return s.getFirstName();
		case 1:
			return s.getLastName();
		case 2:
			return s.getStatus();
		case 3:
			GregorianCalendar gci = s.getTimeIn();
			return gci.get(Calendar.HOUR) + ":" + gci.get(Calendar.MINUTE);
		case 4:
			GregorianCalendar gco = s.getTimeOut();
			return gco.get(Calendar.HOUR) + ":" + gco.get(Calendar.MINUTE);
		case 5:
			return new Boolean(s.isAutoSignedOut());
		default:
			return "! - ERR - !";
		}
	}
	
	@Override
	public Class<? extends Object> getColumnClass(int columnIndex){
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return false;
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		Student s = data.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			s.setFirstName((String)value);
			break;
		case 1:
			s.setLastName((String)value);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			s.setAutoSignedOut((boolean)value);
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void addStudent(Student student){
		data.add(student);
		fireTableRowsInserted(data.size() - 1, data.size());
	}
	
	public void deleteRow(int rowIndex){
		data.remove(rowIndex);
		fireTableRowsDeleted(rowIndex - 1, rowIndex);
	}
	
}

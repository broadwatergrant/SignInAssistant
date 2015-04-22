package com.grantbroadwater.signInAssistant.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

import com.grantbroadwater.school.Student;

public class SignInSheetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = { "First Name", "Last Name", "Status",
			"Time in", "Time Out", "Auto Signed Out" };
	private ArrayList<ArrayList<Object>> data;
	private ArrayList<Student> studentList;

	public SignInSheetTableModel() {
		data = new ArrayList<ArrayList<Object>>();
		studentList = new ArrayList<Student>();
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
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data.get(rowIndex).set(columnIndex, value);
	}

	public void signStudentIn(Student student) {
		ArrayList<Object> newEntry = new ArrayList<Object>();

		newEntry.add(student.getFirstName());
		newEntry.add(student.getLastName());
		newEntry.add(student.getStatus());
		newEntry.add((student.getTimeIn() == null) ? "" : formatTime(student
				.getTimeIn()));
		newEntry.add((student.getTimeOut() == null) ? "" : formatTime(student
				.getTimeOut()));
		newEntry.add(student.isAutoSignedOut());

		data.add(newEntry);
		studentList.add(student);

		fireTableRowsInserted(data.size() - 1, data.size());
	}

	public int signStudentOut(Student s) {
		for (int i = studentList.size() - 1; i >= 0; i--) {
			if (studentList.get(i).essentialyEquals(s)) {
				setValueAt(s.getStatus(), i, 2);
				fireTableCellUpdated(i, 2);
				setValueAt(formatTime(s.getTimeOut()), i, 4);
				fireTableCellUpdated(i, 4);
				if (s.isAutoSignedOut()) {
					setValueAt(true, i, 5);
					fireTableCellUpdated(i, 5);
				}
				return i;
			}
		}
		return -1;
	}

	public void clear() {
		data.clear();
		studentList.clear();
		fireTableDataChanged();
	}

	private String formatTime(GregorianCalendar gc) {
		int min = gc.get(Calendar.MINUTE);
		String strMin = (min < 10) ? "0" + min : "" + min;
		int hour = gc.get(Calendar.HOUR_OF_DAY);
		hour = (hour > 12) ? hour - 12 : hour;
		return hour + ":" + strMin;
	}
}

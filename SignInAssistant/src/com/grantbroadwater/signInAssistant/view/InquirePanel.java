package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.grantbroadwater.school.Student;
import com.grantbroadwater.util.Log;

public class InquirePanel extends GPanel {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 600, HEIGHT = 400;

	private JTextField tfPin;
	private JTextField tfFirst;
	private JTextField tfLast;
	private JLabel lblStatus;
	private InquireResultsTableModel resultsTableModel;
	private JTable resultsTable;
	private StudentHistoryTableModel historyTableModel;

	private JPanel entryPanel, mainPanel;

	public InquirePanel() {
		super(new BorderLayout());

		entryPanel = createEntryPanel();
		mainPanel = createMainPanel();

		add(entryPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private JPanel createEntryPanel() {
		JPanel housingPanel = new JPanel();
		housingPanel.setLayout(new BoxLayout(housingPanel, BoxLayout.Y_AXIS));
		housingPanel.setBackground(Color.WHITE);

		JPanel lblPanel = new JPanel(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.add(new JLabel(" Pin"));
		lblPanel.add(p1, BorderLayout.WEST);
		JPanel cPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cPanel.setBackground(Color.WHITE);
		cPanel.add(new JLabel("First Name"));
		lblPanel.add(cPanel, BorderLayout.CENTER);
		JPanel p2 = new JPanel();
		p2.add(new JLabel("Last Name "));
		p2.setBackground(Color.WHITE);
		lblPanel.add(p2, BorderLayout.EAST);
		lblPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPanel.setBackground(Color.WHITE);

		JPanel tfPanel = new JPanel(new BorderLayout());
		tfPin = new JTextField(9);
		JPanel pnlP = new JPanel();
		pnlP.add(tfPin);
		pnlP.setBackground(Color.WHITE);
		tfPanel.add(pnlP, BorderLayout.WEST);
		JPanel c1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tfFirst = new JTextField(13);
		c1Panel.add(tfFirst);
		c1Panel.setBackground(Color.WHITE);
		tfPanel.add(c1Panel, BorderLayout.CENTER);
		tfLast = new JTextField(13);
		JPanel pnlL = new JPanel();
		pnlL.add(tfLast);
		pnlL.setBackground(Color.WHITE);
		tfPanel.add(pnlL, BorderLayout.EAST);
		tfPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		tfPanel.setBackground(Color.WHITE);

		housingPanel.add(lblPanel);
		housingPanel.add(tfPanel);

		return housingPanel;
	}

	private JPanel createMainPanel() {
		JPanel housingPanel = new JPanel();
		housingPanel.setLayout(new BorderLayout());
		housingPanel.setBackground(Color.WHITE);

		// Results Panel
		JPanel resultsPanel = new JPanel(new BorderLayout());
		JPanel lblStudentPanel = new JPanel();
		lblStudentPanel.setBackground(Color.WHITE);
		lblStudentPanel.add(new JLabel("Students:"));
		resultsPanel.add(lblStudentPanel, BorderLayout.NORTH);
		resultsTableModel = new InquireResultsTableModel();
		resultsTable = new JTable(resultsTableModel);
		resultsTable
				.setPreferredScrollableViewportSize(new Dimension(200, 200));
		resultsTable.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		housingPanel.add(resultsPanel, BorderLayout.WEST);

		// Specific Panel
		JPanel specificPanel = new JPanel(new BorderLayout());
		specificPanel.setBackground(Color.WHITE);
		JPanel lblPanel = new JPanel(); /* ---\/--- Label Panel ---\/--- */
		lblPanel.setBackground(Color.WHITE);
		lblStatus = new JLabel("Student is in/out of the library");
		lblPanel.add(lblStatus);
		specificPanel.add(lblPanel, BorderLayout.NORTH);
		historyTableModel = new StudentHistoryTableModel(); /*
															 * ---\/--- History
															 * Table ---\/---
															 */
		JTable historyTable = new JTable(historyTableModel);
		historyTable.setFillsViewportHeight(true);
		JScrollPane historyScrollPane = new JScrollPane(historyTable);
		specificPanel.add(historyScrollPane, BorderLayout.CENTER);
		housingPanel.add(specificPanel, BorderLayout.CENTER);

		return housingPanel;
	}

	public String getPin() {
		return tfPin.getText();
	}

	public String getFirstName() {
		return tfFirst.getText();
	}

	public String getLastName() {
		return tfLast.getText();
	}

	public void setStatusLabelText(String text) {
		lblStatus.setText(text);
	}

	public void setStatusLabelColor(Color color) {
		lblStatus.setForeground(color);
	}

	public void setStatusLabelView(String text, Color color) {
		setStatusLabelText(text);
		setStatusLabelColor(color);
	}

	public Student getSelectedStudent() {
		int rowIndex = resultsTable.getSelectedRow();
		if (rowIndex != -1)
			return resultsTableModel.getStudentAt(rowIndex);
		else
			return null;
	}

	public void setResultsFromInquiry(ArrayList<Student> students) {
		resultsTableModel.setResults(students);
	}

	public StudentHistoryTableModel getStudentHistoryTableModel() {
		return this.historyTableModel;
	}

	public void addSingleHistoryEntry(Student s) {
		historyTableModel.addSingleHistoryEntry(s.getTimeIn(), s.getTimeOut(),
				s.isAutoSignedOut());
	}

	public void addSingleEntry(String arg0, String arg1, Boolean arg2) {
		historyTableModel.addSingleHistoryEntry(arg0, arg1, arg2);
	}

	public void clearHistoryTable() {
		historyTableModel.clearTable();
	}

	public void addInquirePanelDocumentListener(DocumentListener listener) {
		tfPin.getDocument().addDocumentListener(listener);
		tfFirst.getDocument().addDocumentListener(listener);
		tfLast.getDocument().addDocumentListener(listener);
	}

	public void addInquirePanelListSelectionListener(
			ListSelectionListener listener) {
		resultsTable.getSelectionModel().addListSelectionListener(listener);
	}

}

class InquireResultsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = { "Student" };
	private ArrayList<Student> data;

	public InquireResultsTableModel() {
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
		return data.get(rowIndex).getName() + " ("
				+ data.get(rowIndex).getPin() + ")";
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

	public void setResults(ArrayList<Student> results) {
		data.clear();
		data.addAll(results);
		fireTableDataChanged();
	}

	public Student getStudentAt(int rowIndex) {
		return data.get(rowIndex);
	}

}

class StudentHistoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = { "Time In", "Time Out", "Auto Signed Out" };
	private ArrayList<ArrayList<Object>> data;

	public StudentHistoryTableModel() {
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
		try {
			if (rowIndex >= data.size() || columnIndex > data.get(0).size()) {
				return null;
			}
			return data.get(rowIndex).get(columnIndex);
		} catch (Exception e) {
			new Log(Log.LogType.ERROR, "Exception caught");
			return null;
		}
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

	public void addSingleHistoryEntry(GregorianCalendar timeIn,
			GregorianCalendar timeOut, Boolean autoSignedOut) {
		addSingleHistoryEntry(formatTime(timeIn), formatTime(timeOut),
				autoSignedOut);
	}

	public void addSingleHistoryEntry(String arg0, String arg1, Boolean arg2) {
		ArrayList<Object> newEntry = new ArrayList<Object>();

		newEntry.add(arg0);
		newEntry.add(arg1);
		newEntry.add(arg2);

		data.add(newEntry);
		fireTableRowsInserted(data.size() - 1, data.size());
	}

	public void setEntireHistory(ArrayList<ArrayList<Object>> history) {
		data.clear();
		fireTableDataChanged();

		for (ArrayList<Object> singleEntry : history) {
			addSingleHistoryEntry((GregorianCalendar) singleEntry.get(0),
					(GregorianCalendar) singleEntry.get(1),
					(Boolean) singleEntry.get(2));
		}
	}

	public void clearTable() {
		data.clear();
		fireTableDataChanged();
	}

	private String formatTime(GregorianCalendar gc) {
		if (gc == null)
			return "";
		return gc.get(Calendar.HOUR_OF_DAY) + ":" + gc.get(Calendar.MINUTE);
	}
}

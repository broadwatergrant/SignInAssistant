package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import com.grantbroadwater.school.Person;

public class DataPanel extends GPanel {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 475, HEIGHT = 500;

	private JTextField tfPin, tfFirst, tfLast;
	private ResultsTableModel studentResultsModel, adminResultsModel;
	private JButton btnChangeExcelFileLocation;
	private JLabel lblExcelFilePath;

	public DataPanel() {
		super(new BorderLayout());
		setBackground(Color.WHITE);

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.WHITE);
		headerPanel.add(createHeaderPanel(), BorderLayout.NORTH);
		headerPanel.add(createEntryPanel(), BorderLayout.CENTER);

		add(headerPanel, BorderLayout.NORTH);
		add(createBodyPanel(), BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private JPanel createHeaderPanel() {
		JPanel housingPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		housingPanel.setBackground(Color.WHITE);

		btnChangeExcelFileLocation = new JButton("Change Excel File");
		housingPanel.add(btnChangeExcelFileLocation);

		lblExcelFilePath = new JLabel("Path");
		lblExcelFilePath.setToolTipText("Path");
		housingPanel.add(lblExcelFilePath);

		return housingPanel;
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
		tfPin = new JTextField(8);
		JPanel pnlP = new JPanel();
		pnlP.add(tfPin);
		pnlP.setBackground(Color.WHITE);
		tfPanel.add(pnlP, BorderLayout.WEST);
		JPanel c1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tfFirst = new JTextField(12);
		c1Panel.add(tfFirst);
		c1Panel.setBackground(Color.WHITE);
		tfPanel.add(c1Panel, BorderLayout.CENTER);
		tfLast = new JTextField(12);
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

	private JPanel createBodyPanel() {
		JPanel housingPanel = new JPanel();
		housingPanel.setLayout(new BoxLayout(housingPanel, BoxLayout.Y_AXIS));

		housingPanel.add(createAdminResultsPanel(), BorderLayout.NORTH);
		housingPanel.add(createStudentResultsPanel(), BorderLayout.CENTER);

		return housingPanel;
	}

	private JPanel createStudentResultsPanel() {
		JPanel housingPanel = new JPanel();

		JTable table = new JTable(new ResultsTableModel());
		studentResultsModel = (ResultsTableModel) table.getModel();
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);

		housingPanel.add(scrollPane);

		return housingPanel;
	}

	private JPanel createAdminResultsPanel() {
		JPanel housingPanel = new JPanel();

		JTable table = new JTable(new ResultsTableModel());
		adminResultsModel = (ResultsTableModel) table.getModel();
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);

		housingPanel.add(scrollPane);

		return housingPanel;
	}

	public void setStudentsResult(ArrayList<Person> results) {
		studentResultsModel.setResults(results);
	}

	public void setAdminsResult(ArrayList<Person> results) {
		adminResultsModel.setResults(results);
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

	public void displayMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void addDataPanelDocumentListener(DocumentListener listener) {
		tfPin.getDocument().addDocumentListener(listener);
		tfFirst.getDocument().addDocumentListener(listener);
		tfLast.getDocument().addDocumentListener(listener);
	}

	public void addExcelFileChangedActionListener(ActionListener listener) {
		btnChangeExcelFileLocation.addActionListener(listener);
	}

	public void changeExcelFilePathText(String text, String toolTipText) {
		lblExcelFilePath.setText(text);
		lblExcelFilePath.setToolTipText(toolTipText);
	}

}

class ResultsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Pin", "First Name", "Last Name" };
	private ArrayList<ArrayList<Object>> data;

	public ResultsTableModel() {
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
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int ColumnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data.get(rowIndex).set(columnIndex, value);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void setResults(ArrayList<Person> results) {
		data.clear();

		for (Person person : results)
			data.add(getRow(person));

		fireTableDataChanged();
	}

	private ArrayList<Object> getRow(Person person) {
		ArrayList<Object> result = new ArrayList<Object>();

		result.add(person.getPin());
		result.add(person.getFirstName());
		result.add(person.getLastName());

		return result;
	}

}
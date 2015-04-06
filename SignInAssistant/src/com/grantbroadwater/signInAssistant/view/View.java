package com.grantbroadwater.signInAssistant.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import com.grantbroadwater.school.BellSchedule;

public class View {

	private AdministratorPanel administratorPanel;
	private AdministratorSignInPanel administratorSignInPanel;
	private DataPanel dataPanel;
	private InquirePanel inquirePanel;
	private SIAFrame siaFrame;
	private StudentFrame studentFrame;
	private StudentPanel studentPanel;
	
	public View(ArrayList<BellSchedule> schedules) {
		administratorPanel = new AdministratorPanel(schedules);
		administratorSignInPanel = new AdministratorSignInPanel();
		dataPanel = new DataPanel();
		inquirePanel = new InquirePanel();
		studentFrame = new StudentFrame();
		studentPanel = studentFrame.getStudentPanel();
	}

	public AdministratorPanel getAdministratorPanel() {
		return administratorPanel;
	}

	public AdministratorSignInPanel getAdministratorSignInPanel() {
		return administratorSignInPanel;
	}

	public DataPanel getDataPanel() {
		return dataPanel;
	}

	public InquirePanel getInquirePanel() {
		return inquirePanel;
	}

	public SIAFrame getSiaFrame() {
		return siaFrame;
	}

	public StudentFrame getStudentFrame() {
		return studentFrame;
	}

	public StudentPanel getStudentPanel() {
		return studentPanel;
	}
	
	
	/* ----- Delegate Methods ----- */
	
	public void addStartActionListener(ActionListener listener) {
		administratorPanel.addStartActionListener(listener);
	}

	public void addSaveActionListener(ActionListener listener) {
		administratorPanel.addSaveActionListener(listener);
	}

	public void addSignInActionListener(ActionListener listener) {
		administratorSignInPanel.addSignInActionListener(listener);
	}

	public void addDataPanelDocumentListener(DocumentListener listener) {
		dataPanel.addDataPanelDocumentListener(listener);
	}

	public void addInquirePanelDocumentListener(DocumentListener listener) {
		inquirePanel.addInquirePanelDocumentListener(listener);
	}

	public void addInquirePanelListSelectionListener(
			ListSelectionListener listener) {
		inquirePanel.addInquirePanelListSelectionListener(listener);
	}

	public void addStudentPanelConfirmListener(ActionListener listener) {
		studentPanel.addStudentPanelConfirmListener(listener);
	}

	public void addStudentPanelDocumentListener(DocumentListener listener) {
		studentPanel.addStudentPanelDocumentListener(listener);
	}
	
}

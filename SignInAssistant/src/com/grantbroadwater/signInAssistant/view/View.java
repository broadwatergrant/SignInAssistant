package com.grantbroadwater.signInAssistant.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import com.grantbroadwater.school.BellSchedule;

public class View {

	private AdministratorPanel administratorPanel;
	private AdministratorSignInPanel administratorSignInPanel;
	private DataPanel dataPanel;
	private InquirePanel inquirePanel;
	private SIAFrame siaFrame;
	private SIAMenuBar siaMenuBar;
	private StudentFrame studentFrame;
	private StudentPanel studentPanel;
	
	public View(ArrayList<BellSchedule> schedules) {
		administratorPanel = new AdministratorPanel(schedules);
		administratorSignInPanel = new AdministratorSignInPanel();
		dataPanel = new DataPanel();
		inquirePanel = new InquirePanel();
		studentFrame = new StudentFrame();
		studentPanel = studentFrame.getStudentPanel();
		
		JPanel[] panels = {
				administratorPanel, administratorSignInPanel,
				dataPanel, inquirePanel
		};
		String[] panelNames = {
				administratorPanel.getCardLayoutName(),
				administratorSignInPanel.getCardLayoutName(),
				dataPanel.getCardLayoutName(),
				inquirePanel.getCardLayoutName()
		};
		
		siaFrame = new SIAFrame();
		siaFrame.setPanels(panels, panelNames);
		siaMenuBar = siaFrame.getSIAMenuBar();
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

	public void showPanel(String panelName) {
		siaFrame.showPanel(panelName);
	}
	
	public void setSIAFrameVisible(boolean value){
		siaFrame.setVisible(value);
	}

	public void setStudentFrameVisible(boolean value){
		studentFrame.setVisible(value);
	}
	
	public SIAMenuBar getSIAMenuBar(){
		return siaMenuBar;
	}
	
}

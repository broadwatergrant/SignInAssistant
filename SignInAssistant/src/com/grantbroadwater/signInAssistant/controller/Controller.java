package com.grantbroadwater.signInAssistant.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.Status;
import com.grantbroadwater.school.Student;
import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.signInAssistant.view.AdministratorPanel;
import com.grantbroadwater.signInAssistant.view.SIAMenuBar;
import com.grantbroadwater.signInAssistant.view.View;

public class Controller {

	Model model;
	View view;
	
	AdminSaveActionListener adminSaveActionListern;
	AdminSignInListener adminSignInListener;
	AdminStartSignInListener adminStartSignInListener;
	DataDocumentListener dataDocumentListener;
	InquireDocumentListener inquireDocumentListener;
	InquireListSelectionListener inquireListSelectionListener;
	StudentPinDocumentListener studentPinDocumnetListener;
	StudentSignInActionListener studentSignInActionListener;
	
	SignInMenuBarListener signInMenuBarListener;
	SignOutMenuBarListener signOutMenuBarListener;
	ReselectPinMenuBarListener reselectPinMenuBarListener;
	CloseMenuBarListener closeMenuBarListener;
	InquireMenuBarListener inquireMenuBarListener;
	SignInSheetMenuBarListener signInSheetMenuBarListener;
	DataMenuBarListener dataMenuBarListener;
	
	SIAKeyListener siaKeyListener;
	
	ScheduleWatcher scheduleWatcher;
	
	public Controller(){
		
	}

	public void createModelAndView(){
		model = new Model();
		model.loadData();
		
		view = new View(new ArrayList<BellSchedule>(Arrays.asList(model.getSchedules())));
		
		adminSaveActionListern = new AdminSaveActionListener(model, this);
		adminSignInListener = new AdminSignInListener(model, view.getAdministratorSignInPanel(), this);
		adminStartSignInListener = new AdminStartSignInListener(this);
		dataDocumentListener = new DataDocumentListener(model, view.getDataPanel());
		inquireDocumentListener = new InquireDocumentListener(model, view.getInquirePanel());
		inquireListSelectionListener = new InquireListSelectionListener(model, view.getInquirePanel());
		studentPinDocumnetListener = new StudentPinDocumentListener(model, view.getStudentPanel());
		studentSignInActionListener = new StudentSignInActionListener(model, view.getStudentPanel(), this);
		
		view.addSaveActionListener(adminSaveActionListern);
		view.addSignInActionListener(adminSignInListener);
		view.addStartActionListener(adminStartSignInListener);
		view.addDataPanelDocumentListener(dataDocumentListener);
		view.addInquirePanelDocumentListener(inquireDocumentListener);
		view.addInquirePanelListSelectionListener(inquireListSelectionListener);
		view.addStudentPanelDocumentListener(studentPinDocumnetListener);
		view.addStudentPanelConfirmListener(studentSignInActionListener);
		
		siaKeyListener = new SIAKeyListener(this);
		view.getSiaFrame().addKeyListener(siaKeyListener);
		view.getAdministratorPanel().addKeyListener(siaKeyListener);
		
		scheduleWatcher = new ScheduleWatcher(model, this);
		
		/* ----- Menu Bar Listeners ----- */
		
		signInMenuBarListener = new SignInMenuBarListener(model, view, this);
		signOutMenuBarListener = new SignOutMenuBarListener(view);
		reselectPinMenuBarListener = new ReselectPinMenuBarListener(view);
		closeMenuBarListener = new CloseMenuBarListener(this);
		inquireMenuBarListener = new InquireMenuBarListener(view);
		signInSheetMenuBarListener = new SignInSheetMenuBarListener(view);
		dataMenuBarListener = new DataMenuBarListener(view);
		
		SIAMenuBar mb = view.getSIAMenuBar();
		mb.addStartStopSignInSelectedFromMenuBarListener(signInMenuBarListener);
		mb.addSignOutSelectedFromMenuBarListener(signOutMenuBarListener);
		mb.addReselectPinSelectedFromMenuBarListener(reselectPinMenuBarListener);
		mb.addCloseSelectedFromMenuBarListener(closeMenuBarListener);
		mb.addInquireSelectedFromMenuBarListener(inquireMenuBarListener);
		mb.addSignInSheetSelectedFromMenuBarListener(signInSheetMenuBarListener);
		mb.addDataSelectedFromMenuBarListener(dataMenuBarListener);
	}
	
	public void startApplication(){
		view.showPanel(view.getAdministratorSignInPanel().getCardLayoutName());
		view.setSIAFrameVisible(true);
	}
	
	public Model getModel(){
		return model;
	}
	
	public View getView(){
		return this.view;
	}
	
	
	/* ----- Behaviors too large to be handled by listeners ----- */
	
	protected void signInAdmin(Administrator admin){
		view.getAdministratorSignInPanel().clearTextFields();
		view.showPanel(view.getAdministratorPanel().getCardLayoutName());
		view.getSiaFrame().setSIAMenuBarVisibility(true);
	}
	
	protected void punchStudent(Student student){
		model.getSignInSheet().punchStudent(student);
		view.getAdministratorPanel().updateSignInSheet(student);
	}
	
	protected void adminClickedStartStop(){
		AdministratorPanel administratorPanel = view.getAdministratorPanel();
		if(!view.getStudentFrame().isVisible()){
			administratorPanel.setScheduleComboBoxEnabled(false);
			this.startStudentSignIn(model.getScheduleWithName(administratorPanel.getSelectedScheduleName()));	
		}else{
			administratorPanel.setScheduleComboBoxEnabled(true);
			this.stopStudentSignIn();
		}
	}
	
	protected void startStudentSignIn(BellSchedule schedule){
		view.setStudentFrameVisible(true);
		view.getSIAMenuBar().setStartStopSignInText("Stop Sign In");
		view.getAdministratorPanel().setStartStopButtonText("Stop");
		model.setSelectedSchedule(
				model.getScheduleWithName(
						view.getAdministratorPanel().getSelectedScheduleName()));
		scheduleWatcher.startWatchingSchedule();
	}
	
	protected void stopStudentSignIn(){
		view.setStudentFrameVisible(false);
		view.getSIAMenuBar().setStartStopSignInText("Start Sign In");
		view.getAdministratorPanel().setStartStopButtonText("Start");
		model.setSelectedSchedule(null);
		scheduleWatcher.cancel();
		scheduleWatcher = new ScheduleWatcher(model, this);
	}
	
	protected void saveSignInSheet(Student[] students){
		new SignInSheetSave(students);
	}
	
	protected void reselectPin(){
		view.getStudentPanel().refocus();
	}
	
	protected void autoSignOutStudents(){
		for(Student s : model.getSignInSheet().getSignInSheet())
			if(s.getStatus() == Status.IN)
				this.autoSignOutStudent(model.getStudentBody().get(s.getPin()));
	}
	
	private void autoSignOutStudent(Student s){
		model.getSignInSheet().autoSignStudentOut(s);
		view.getAdministratorPanel().updateSignInSheet(s);
	}
	
	protected void closeApplication(){
		
	}
	
}

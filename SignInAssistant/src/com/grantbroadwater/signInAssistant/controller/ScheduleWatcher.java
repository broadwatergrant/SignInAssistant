package com.grantbroadwater.signInAssistant.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.grantbroadwater.signInAssistant.model.Model;
import com.grantbroadwater.util.Log;

public class ScheduleWatcher extends Timer {

	private Model model;
	private Controller controller;
	private WatchScheduleTask watchScheduleTask;
	
	public ScheduleWatcher(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}

	
	public void startWatchingSchedule(){
		watchScheduleTask = new WatchScheduleTask(model, controller);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.MINUTE, gc.get(Calendar.MINUTE) + 1);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		Date date = gc.getTime();
		
		schedule(watchScheduleTask, date, 60000);
	}
	
}

class WatchScheduleTask extends TimerTask{

	private Model model;
	private Controller controller;
	private int currentHour;
	
	public WatchScheduleTask(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		new Log(Log.LogType.DEBUG, "Here: "+currentHour+" model: "+model.getCurrentHour());
		if(currentHour != model.getCurrentHour()){
			controller.autoSignOutStudents();
			currentHour = model.getCurrentHour();
		}
	}
	
}


package com.grantbroadwater.signInAssistant.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.grantbroadwater.signInAssistant.model.Model;

public class ScheduleWatcher extends Timer {

	private Model model;
	private Controller controller;
	private WatchScheduleTask watchScheduleTask;

	public ScheduleWatcher(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}

	public void startWatchingSchedule() {
		watchScheduleTask = new WatchScheduleTask(model, controller,
				model.getCurrentHour());

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.MINUTE, gc.get(Calendar.MINUTE) + 1);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		Date date = gc.getTime();

		schedule(watchScheduleTask, date, 60000);
	}

}

class WatchScheduleTask extends TimerTask {

	private Model model;
	private Controller controller;
	private int currentHour;

	public WatchScheduleTask(Model model, Controller controller, int currentHour) {
		this.model = model;
		this.controller = controller;
		this.currentHour = currentHour;
	}

	@Override
	public void run() {
		if (currentHour != model.getCurrentHour()) {
			controller.autoSignOutStudents();
			currentHour = model.getCurrentHour();
		}
	}

}

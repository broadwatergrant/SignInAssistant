package com.grantbroadwater.school;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClassPeriod {

	private GregorianCalendar start, stop;
	
	public ClassPeriod() {
		this.setStart(null);
		this.setStop(null);
	}

	public ClassPeriod(GregorianCalendar start, GregorianCalendar stop) {
		super();
		this.start = start;
		this.stop = stop;
	}

	public GregorianCalendar getStart() {
		return start;
	}

	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	public GregorianCalendar getStartTime() {
		return this.getStart();
	}

	public void setStartTime(GregorianCalendar startTime) {
		this.setStart(startTime);
	}
	
	public GregorianCalendar getStop() {
		return stop;
	}

	public void setStop(GregorianCalendar stop) {
		this.stop = stop;
	}
	
	public GregorianCalendar getStopTime(){
		return this.getStop();
	}
	
	public void setStopTime(GregorianCalendar stopTime){
		this.setStop(stopTime);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((stop == null) ? 0 : stop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		ClassPeriod other = null;
		if(obj instanceof ClassPeriod)
			other = (ClassPeriod)obj;
		else
			return false;
		
		if(XOR(this.start == null, other.start == null))
			return false;
		else if(XOR(this.stop == null, other.stop == null))
			return false;
		else
			return timeEquals(other);
	}
	
	public static boolean XOR(boolean x, boolean y) {
	    return ( ( x || y ) && ! ( x && y ) );
	}
	
	private boolean timeEquals(ClassPeriod other){
		boolean b1 = this.start.get(Calendar.HOUR_OF_DAY) == other.start.get(Calendar.HOUR_OF_DAY);
		boolean b2 = this.start.get(Calendar.MINUTE) == other.start.get(Calendar.MINUTE);
		boolean b3 = this.stop.get(Calendar.HOUR_OF_DAY) == other.stop.get(Calendar.HOUR_OF_DAY);
		boolean b4 = this.stop.get(Calendar.MINUTE) == other.stop.get(Calendar.MINUTE);
		return b1 && b2 && b3 && b4;
	}

	@Override
	public String toString() {
		return "ClassPeriod [start=" + start.get(Calendar.HOUR_OF_DAY) + ":" + start.get(Calendar.MINUTE) 
				+ ", stop=" + stop.get(Calendar.HOUR_OF_DAY) + ":" + stop.get(Calendar.MINUTE) + "]";
	}

}

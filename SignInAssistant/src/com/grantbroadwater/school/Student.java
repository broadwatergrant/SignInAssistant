package com.grantbroadwater.school;

import java.util.GregorianCalendar;

public class Student extends Person {

	private int gradeLevel;
	private GregorianCalendar timeIn, timeOut;
	private Status status;
	
	public Student() {
		super();
		this.setTimeIn(null);
		this.setTimeOut(null);
		this.setStatus(Status.OUT);
	}

	public Student(String firstName, String lastName, String pin) {
		super(firstName, lastName, pin);
		this.setTimeIn(null);
		this.setTimeOut(null);
		this.setStatus(Status.OUT);
	}

	public Student(String firstName, String lastName, String pin, Status status) {
		super(firstName, lastName, pin);
		this.setTimeIn(null);
		this.setTimeOut(null);
		this.setStatus(status);
	}
	
	public Student(String firstName, String lastName, String pin, GregorianCalendar timeIn, GregorianCalendar timeOut, Status status) {
		super(firstName, lastName, pin);
		this.setTimeIn(timeIn);
		this.setTimeOut(timeOut);
		this.setStatus(status);
	}

	public GregorianCalendar getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(GregorianCalendar timeIn) {
		this.timeIn = timeIn;
	}

	public GregorianCalendar getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(GregorianCalendar timeOut) {
		this.timeOut = timeOut;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((timeIn == null) ? 0 : timeIn.hashCode());
		result = prime * result + ((timeOut == null) ? 0 : timeOut.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (status != other.status)
			return false;
		if (timeIn == null) {
			if (other.timeIn != null)
				return false;
		} else if (!timeIn.equals(other.timeIn))
			return false;
		if (timeOut == null) {
			if (other.timeOut != null)
				return false;
		} else if (!timeOut.equals(other.timeOut))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " Student [timeIn=" + timeIn + ", timeOut=" + timeOut + ", status=" + status + "]";
	}
	
	

}

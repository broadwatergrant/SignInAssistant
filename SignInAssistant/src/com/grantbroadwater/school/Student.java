package com.grantbroadwater.school;

import java.util.GregorianCalendar;

public class Student extends Person {

	private int gradeLevel;
	private GregorianCalendar timeIn, timeOut;
	private Status status;
	private boolean autoSignedOut;

	public Student() {
		super();
		this.setGradeLevel(0);
		this.setTimeIn(null);
		this.setTimeOut(null);
		this.setStatus(Status.OUT);
	}

	public Student(Student copy){
		super(copy.getFirstName(), copy.getLastName(), copy.getPin());
		this.setGradeLevel(copy.getGradeLevel());
		this.setStatus(copy.getStatus());
		this.setTimeIn(copy.getTimeIn());
		this.setTimeOut(copy.getTimeOut());
		this.setAutoSignedOut(copy.isAutoSignedOut());
	}
	
	public Student(String firstName, String lastName, String pin) {
		super(firstName, lastName, pin);
		this.setGradeLevel(0);
		this.setTimeIn(null);
		this.setTimeOut(null);
		this.setStatus(Status.OUT);
	}

	public Student(String firstName, String lastName, String pin, int gradeLevel) {
		super(firstName, lastName, pin);
		this.setGradeLevel(gradeLevel);
		this.setTimeIn(null);
		this.setTimeOut(null);
		this.setStatus(Status.OUT);
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

	public boolean isAutoSignedOut() {
		return autoSignedOut;
	}

	public void setAutoSignedOut(boolean autoSignedOut) {
		this.autoSignedOut = autoSignedOut;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + gradeLevel;
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
		if (gradeLevel != other.gradeLevel)
			return false;
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

	public boolean essentialyEquals(Object obj) {
		Student s = null;
		if (obj instanceof Student)
			s = (Student) obj;
		else
			return false;

		if (!super.equals(obj))
			return false;

		return this.getGradeLevel() == s.getGradeLevel();
	}

	@Override
	public String toString() {
		return "Student [name=" + this.getName() + " pin=" + this.getPin()
				+ " grade=" + this.getGradeLevel() + "]";
	}

}

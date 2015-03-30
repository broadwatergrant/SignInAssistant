package com.grantbroadwater.school;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Students {

	private HashMap<String, Student> studentList;

	public Students() {
		super();
		this.setStudentList(new HashMap<String, Student>());
	}
	
	public Students(Student[] students){
		this();
		this.setStudents(students);
	}
	
	public Students(HashMap<String, Student> students){
		this();
		this.setStudentList(students);
	}
	
	/* ----- Accessors / Mutators ----- */
	
	public HashMap<String, Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(HashMap<String, Student> studentList) {
		this.studentList = studentList;
	}

	/* ----- Specific Behaviors ----- */
	
	public void setStudents(Student[] students){
		studentList.clear();
		for(Student s : students)
			studentList.put(s.getPin(), s);
	}
	
	public Student add(Student s){
		return studentList.put(s.getPin(), s);
	}
	
	
	/* ----- Delegate Methods ----- */
	
	public int size() {
		return studentList.size();
	}

	public boolean isEmpty() {
		return studentList.isEmpty();
	}

	public Student get(Object key) {
		return studentList.get(key);
	}

	public boolean containsKey(Object key) {
		return studentList.containsKey(key);
	}

	public Student put(String key, Student value) {
		return studentList.put(key, value);
	}

	public Student remove(Object key) {
		return studentList.remove(key);
	}

	public void clear() {
		studentList.clear();
	}

	public boolean containsValue(Object value) {
		return studentList.containsValue(value);
	}

	public Set<String> keySet() {
		return studentList.keySet();
	}

	public Collection<Student> values() {
		return studentList.values();
	}

	public boolean remove(Object key, Object value) {
		return studentList.remove(key, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((studentList == null) ? 0 : studentList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Students))
			return false;
		Students other = (Students) obj;
		if (studentList == null) {
			if (other.studentList != null)
				return false;
		} else if (!studentList.equals(other.studentList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Students [studentList=" + studentList + "]";
	}
	
}

package com.grantbroadwater.school;

public abstract class Person {

	private String firstName, lastName, pin;
	
	public Person() {
		super();
		setFirstName(null);
		setLastName(null);
		setPin(null);
	}

	public Person(String firstName, String lastName, String pin) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		this.setPin(pin);
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = capitalize(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = capitalize(lastName);
	}

	public String getName(){
		return this.getFirstName() + " " + this.getLastName();
	}
	
	public void setName(String firstName, String lastName){
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String capitalize(String str){
		if(str == null || str.length() == 0)
			return "";
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", pin=" + pin + "]";
	}

	

}

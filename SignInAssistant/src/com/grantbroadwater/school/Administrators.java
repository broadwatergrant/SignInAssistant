package com.grantbroadwater.school;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Administrators {

	private HashMap<String, Administrator> administratorList;
	
	public Administrators() {
		super();
		this.setAdministratorList(new HashMap<String, Administrator>());
	}
	
	public Administrators(Administrator[] administrators){
		this();
		this.setAdministrators(administrators);
	}
	
	public Administrators(HashMap<String, Administrator> administratorList){
		this();
		this.setAdministratorList(administratorList);
	}

	/* ----- Accessors / Mutators ----- */
	
	public HashMap<String, Administrator> getAdministratorList() {
		return administratorList;
	}

	public void setAdministratorList(HashMap<String, Administrator> administratorList) {
		this.administratorList = administratorList;
	}

	/* ----- Specific Behaviors ----- */
	
	public void setAdministrators(Administrator[] administrators){
		administratorList.clear();
		for(Administrator a : administrators)
			administratorList.put(a.getPin(), a);
	}
	
	public Administrator add(Administrator a){
		return administratorList.put(a.getPin(), a);
	}
	
	/* ----- Delegate Methods ----- */
	
	public int size() {
		return administratorList.size();
	}

	public boolean isEmpty() {
		return administratorList.isEmpty();
	}

	public Administrator get(Object key) {
		return administratorList.get(key);
	}

	public boolean containsKey(Object key) {
		return administratorList.containsKey(key);
	}

	public Administrator put(String key, Administrator value) {
		return administratorList.put(key, value);
	}

	public Administrator remove(Object key) {
		return administratorList.remove(key);
	}

	public void clear() {
		administratorList.clear();
	}

	public boolean containsValue(Object value) {
		return administratorList.containsValue(value);
	}

	public Set<String> keySet() {
		return administratorList.keySet();
	}

	public Collection<Administrator> values() {
		return administratorList.values();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((administratorList == null) ? 0 : administratorList
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Administrators))
			return false;
		Administrators other = (Administrators) obj;
		if (administratorList == null) {
			if (other.administratorList != null)
				return false;
		} else if (!administratorList.equals(other.administratorList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrators [administratorList=" + administratorList + "]";
	}

	
	
}

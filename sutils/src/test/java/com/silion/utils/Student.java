package com.silion.utils;

import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = -7717877192152413826L;
	private String name;
	private String hoppy;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHoppy() {
		return hoppy;
	}
	public void setHoppy(String hoppy) {
		this.hoppy = hoppy;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", hoppy=" + hoppy + "]";
	}
	public Student(String name, String hoppy) {
		super();
		this.name = name;
		this.hoppy = hoppy;
	}
	public Student() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoppy == null) ? 0 : hoppy.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (hoppy == null) {
			if (other.hoppy != null)
				return false;
		} else if (!hoppy.equals(other.hoppy))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
	
}

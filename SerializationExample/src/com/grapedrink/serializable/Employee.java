package com.grapedrink.serializable;

public class Employee implements java.io.Serializable {

	/*
	 * serialVersionUID is essentially the "serialization version" of the class.
	 * Serialization and deserialization are about converting objects to bytes.
	 * If I make a change to this Employee class (ie, adding/renaming a field),
	 * and a consumer of my code has an older version of my class (without field),
	 * then their deserializer might not work when trying to convert the bean I sent.
	 * 
	 * By comparing serialVersionUIDs, users can tell if their software is compatible
	 * with newer versions of the bean.
	 * 
	 */
	private static final long serialVersionUID = -687991492884005033L;

	private String name;
	private String address;
	private int age;
	private transient int ssn; // transients are NOT serializable
	
	public Employee() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getSsn() {
		return ssn;
	}
	
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	public String getInfo() {
		char nl = '\n';
		return "Name:     " + name + nl
				+ "Address:  " + address + nl
				+ "Age:      " + age + nl
				+ "SSN:      " + ssn + nl;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

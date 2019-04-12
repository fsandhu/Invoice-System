package com.cinco;

import java.util.ArrayList;

/**
 * @author fsandhu
 * @author wchoi
 *
 *         This is person class that holds appropriate data for a person.
 *
 */

public class Person {

	private String personCode;
	private String firstname;
	private String lastname;
	private Address address;
	private ArrayList<String> emails;

	public Person(String personCode, String lastname, String firstname, Address address, ArrayList<String> emails) {
		this.personCode = personCode;
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
		this.emails = emails;
	}

	public String toString() {
		return String.format(
				"Code: %s\n" + "Name: %s %s\n" + "Address: " + this.address + "\n" + "Email: " + this.emails + "\n",
				this.personCode, this.firstname, this.lastname);
	}

	public final ArrayList<String> getEmails() {
		return this.emails;
	}

	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}

	public final String getPersonCode() {
		return this.personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public final String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public final String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public final Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String printPersonName() {
		return String.format("%s, %s", this.lastname, this.firstname);
	}
}

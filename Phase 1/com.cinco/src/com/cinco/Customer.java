package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 * This is an abstract class for Customer type, which can be exteded to Government Customer and Company
 * Customer.
 *
 */
public abstract class Customer {

	private String customerCode;
	private String name;
	private Person primaryContact;
	private Address mailingAddress;

	public Customer(String customerCode, String name, Person primaryContact, Address mailingAddress) {
		this.customerCode = customerCode;
		this.primaryContact = primaryContact;
		this.name = name;
		this.mailingAddress = mailingAddress;
	}

	public final String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public final Person getPrimaryContact() {
		return this.primaryContact;
	}

	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}

	public final String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public final Address getMailingAddress() {
		return this.mailingAddress;
	}

	public void setMailingAddress(Address mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public abstract String getType();

}

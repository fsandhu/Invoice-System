package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is Government Customer class extends a customer class if the
 *         type is G.
 *
 */

public class GovernmentCustomer extends Customer {

	public GovernmentCustomer(String customerCode, String name, Person primaryContact, Address mailingAddress) {
		super(customerCode, name, primaryContact, mailingAddress);
	}

	public String toString() {
		return String.format(
				"Customer Code: %s (%s)\n" + "Name: %s\n" + "Primary Contact: "
						+ this.getPrimaryContact().getFirstname() + " " + this.getPrimaryContact().getLastname() + "\n"
						+ "Mailing Address: " + this.getMailingAddress() + "\n",
				this.getCustomerCode(), this.getType(), this.getName());
	}

	public final String getType() {
		return "GovernmentCustomer";
	}

}

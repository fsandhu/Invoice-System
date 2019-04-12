package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 *
 *         This is a Address class that holds various attributes of an address.
 *
 */

public class Address {

	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;

	public Address(String street, String city, String state, String zip, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String toString() {
		// return an actual address delimited by commas.
		return String.format("%s, %s, %s, %s, %s", this.street.trim(), this.city.trim(), this.state.trim(),
				this.zip.trim(), this.country.trim());
	}

	public final String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public final String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public final String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public final String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public final String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}

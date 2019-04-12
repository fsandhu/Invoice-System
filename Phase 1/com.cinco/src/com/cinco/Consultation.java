package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is Consultation Product class extends a product class if the
 *         type is C.
 *
 */

public class Consultation extends Product {

	private Person consultant;
	private double hourlyFee;

	public Consultation(String productCode, String name, Person consultant, double hourlyFee) {
		super(productCode, name);
		this.consultant = consultant;
		this.hourlyFee = hourlyFee;
	}

	public String toString() {
		return String.format(
				"Type: Consultation\n" + "Product Code: %s\n" + "Name: %s\n" + "Consultant: %s %s\n"
						+ "Hourly Fee: %.2f\n",
				this.getProductCode(), this.getName(), this.consultant.getFirstname(), this.consultant.getLastname(),
				this.hourlyFee);
	}

	public Person getConsultant() {
		return this.consultant;
	}

	public void setConsultationPersonCode(Person consultant) {
		this.consultant = consultant;
	}

	public double getHourlyFee() {
		return this.hourlyFee;
	}

	public void setHourlyFee(double hourlyFee) {
		this.hourlyFee = hourlyFee;
	}

	public final String getType() {
		return "Consultation";
	}

}

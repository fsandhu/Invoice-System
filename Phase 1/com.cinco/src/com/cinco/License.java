package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is License Product class extends a product class if the type is
 *         L.
 *
 */

public class License extends Product {

	private double serviceFee;
	private double annualLicenseFee;

	public License(String productCode, String name, double serviceFee, double annualLicenseFee) {
		super(productCode, name);
		this.annualLicenseFee = annualLicenseFee;
		this.serviceFee = serviceFee;
	}

	public String toString() {
		return String.format(
				"Type: License\n" + "Product Code: %s\n" + "Name: %s\n" + "Annual License Fee: %.2f\n"
						+ "Service Fee: %.2f\n",
				this.getProductCode(), this.getName(), this.annualLicenseFee, this.serviceFee);
	}

	public double getServiceFee() {
		return this.serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getAnnualLicenseFee() {
		return this.annualLicenseFee;
	}

	public void setAnnualLicenseFee(double annualLicenseFee) {
		this.annualLicenseFee = annualLicenseFee;
	}

	public final String getType() {
		return "License";
	}

}

package com.cinco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is sale class for a license. Extends a product sale class.
 *
 */

public class LicenseSale extends ProductSale {

	private final static double taxRate = 0.0425; // hardcode tax rate

	private double daysBetween;

	public LicenseSale(Product product, String startDate, String endDate) {
		super(product, ((License) product).getServiceFee(), taxRate);

		// create a new date formatter and find days between

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-M-d");
		LocalDate startDateLocal = LocalDate.parse(startDate, dateFormat);
		LocalDate endDateLocal = LocalDate.parse(endDate, dateFormat);
		daysBetween = ChronoUnit.DAYS.between(startDateLocal, endDateLocal);
	}

	public String toString() {
		return String.format("%-10s \t %-40s(%-5.2f days @ $%-5.2f/yr) \t $ %-10.2f \t $ %-10.2f \n",
				this.product.getProductCode(), this.product.getName(), this.daysBetween,
				((License) this.product).getAnnualLicenseFee(), this.getFees(), this.getGrossCost());
	}

	@Override
	public double getGrossCost() {
		return (double) ((License) product).getAnnualLicenseFee() * (this.daysBetween / 365);
	}

	public double getDaysBetween() {
		return daysBetween;
	}

	public void setDaysBetween(double daysBetween) {
		this.daysBetween = daysBetween;
	}

	public double getTaxRate() {
		return taxRate;
	}

}

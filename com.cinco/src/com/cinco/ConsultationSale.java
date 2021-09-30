package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is sale class for a consultation. Extends a product sale class.
 *
 */

public class ConsultationSale extends ProductSale {

	private final static double fees = 150; // hardcode fees
	private final static double taxRate = 0.0425; // hardcode tax rate
	private double noOfBillableHours;

	public ConsultationSale(Product product, double noOfBillableHours) {
		super(product, fees, taxRate);
		this.noOfBillableHours = noOfBillableHours;
	}

	public String toString() {
		return String.format("%-10s \t %-40s(%-5.2f hrs @ $%-5.2f/hr) \t $ %-10.2f \t $ %-10.2f \n",
				this.product.getProductCode(), this.product.getName(), this.noOfBillableHours,
				((Consultation) this.product).getHourlyFee(), this.getFees(), this.getGrossCost());
	}

	public double getNoOfBillableHours() {
		return noOfBillableHours;
	}

	public void setNoOfBillableHours(double noOfBillableHours) {
		this.noOfBillableHours = noOfBillableHours;
	}

	public double getFees() {
		return fees;
	}

	public double getTaxRate() {
		return taxRate;
	}

	@Override
	public double getGrossCost() {
		return (double) ((Consultation) product).getHourlyFee() * noOfBillableHours;
	}

}

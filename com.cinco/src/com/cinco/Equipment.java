package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is Equipment Product class extends a product class if the type
 *         is E.
 *
 */

public class Equipment extends Product {

	private double pricePerUnit;

	public Equipment(String productCode, String name, double pricePerUnit) {
		super(productCode, name);
		this.pricePerUnit = pricePerUnit;
	}

	public String toString() {
		return String.format("Type: Equipment\n" + "Product Code: %s\n" + "Name: %s\n" + "Price Per Unit: %.2f\n",
				this.getProductCode(), this.getName(), this.pricePerUnit);
	}

	public double getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public final String getType() {
		return "Equipment";
	}

}

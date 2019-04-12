package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 * This is sale class for a equipment. Extends a product sale class.
 *
 */

public class EquipmentSale extends ProductSale {

	private int noOfUnits;
	private final static double fee = 0; //hardcode fees
	private final static double taxRate = 0.07; //hardcode tax rate

	public EquipmentSale(Product product, int noOfUnits) {
		super(product, fee, taxRate);
		this.noOfUnits = noOfUnits;
	}

	public int getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public static double getFee() {
		return fee;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public String toString() {
		return String.format("%-10s \t %-40s(%-5d units @ $%-5.2f/unit) \t $ %-10.2f \t $ %-10.2f \n",
				this.product.getProductCode(), this.product.getName(), this.noOfUnits,
				((Equipment) this.product).getPricePerUnit(), this.getFees(), this.getGrossCost());
	}

	@Override
	public double getGrossCost() {
		return (double)((Equipment)product).getPricePerUnit()*noOfUnits;
	}

}

package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is a sale class that creates a new object for each type of sale.
 *         It is abstract and only subclasses may be created as no generic sale
 *         can be made. 
 *
 */

public abstract class ProductSale {

	protected Product product;
	private double fees;
	private double taxRate;

	public ProductSale(Product product, double fees, double taxRate) {
		this.product = product;
		this.fees = fees;
		this.taxRate = taxRate;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getFees() {
		return fees;
	}

	public abstract double getGrossCost();

	public double getSalesTax() {
		return this.getGrossCost() * this.taxRate;
	}

	public double getTotal() {
		return this.getGrossCost() + this.getFees();
	}

}

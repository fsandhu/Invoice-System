package com.cinco;

import java.util.List;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is an invoice class.
 *
 */

public class Invoice {

	private String invoiceCode;
	private Customer customer;
	private Person salesperson;
	private List<ProductSale> products;

	public Invoice(String invoiceCode, Customer customer, Person salesperson, List<ProductSale> products) {
		this.invoiceCode = invoiceCode;
		this.customer = customer;
		this.salesperson = salesperson;
		this.products = products;
	}

	public String toString() {

		String str = "";

		str += String.format("INVOICE %s\n", this.invoiceCode);
		str += "==========================\n";
		str += String.format("Salesperson: %s \n" + "Customer Information: \n", this.salesperson.printPersonName());
		str += this.customer;
		str += "-------------------------------------------------------------\n";
		str += String.format("%-10s \t %-62s \t  %-10s \t %-10s \n", "Code", "Item", "Fees", "Total");
		for (int i = 0; i < this.products.size(); i++) {
			str += this.products.get(i);
		}
		str += String.format("%118s", "============================\n");
		str += String.format("%-80s \t $ %-5.2f \t $ %-10.2f\n", "SUBTOTALS:", InvoiceUtils.getTotalFee(this),
				InvoiceUtils.getGrossTotal(this));
		str += String.format("%-80s \t\t\t $ %-5.2f\n", "COMPLIANCE FEE:", InvoiceUtils.getComplianceFee(this));
		str += String.format("%-80s \t\t\t $ %-5.2f\n", "TAXES:", InvoiceUtils.getTotalTax(this));
		str += String.format("%-80s \t\t\t $ %-5.2f\n\n", "TOTAL:", InvoiceUtils.getSubtotal(this));

		return str;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Person getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(Person salesperson) {
		this.salesperson = salesperson;
	}

	public List<ProductSale> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSale> products) {
		this.products = products;
	}

}

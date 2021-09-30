package com.cinco;

import java.util.List;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This class has methods to process a invoce object and calculate
 *         various things such as fees, taxes, etc.
 *
 */

public class InvoiceUtils {

	public static double getComplianceFee(Invoice invoice) {

		double complianceFee = 0;

		// check what type is the customer and add compliance fees

		if (invoice.getCustomer() instanceof GovernmentCustomer) {
			complianceFee = 125.0;
		} else if (invoice.getCustomer() instanceof CompanyCustomer) {
			complianceFee = 0;
		}

		return complianceFee;
	}

	public static double getTotalFee(Invoice invoice) {

		List<ProductSale> productSaleList = invoice.getProducts();
		double totalServiceFee = 0;

		for (int i = 0; i < productSaleList.size(); i++) {
			totalServiceFee += productSaleList.get(i).getFees();
		}

		double complianceFee = getComplianceFee(invoice);

		return complianceFee + totalServiceFee;

	}

	public static double getTotalTax(Invoice invoice) {

		List<ProductSale> productSaleList = invoice.getProducts();
		double totalTax = 0;

		// check what type of customer and add approprite taxes

		if (invoice.getCustomer() instanceof GovernmentCustomer) {
			totalTax = 0.0;
		} else if (invoice.getCustomer() instanceof CompanyCustomer) {
			for (int i = 0; i < productSaleList.size(); i++) {
				totalTax += productSaleList.get(i).getSalesTax();
			}
		}

		return totalTax;
	}

	public static double getGrossTotal(Invoice invoice) {

		List<ProductSale> productSaleList = invoice.getProducts();
		double total = 0;

		for (int i = 0; i < productSaleList.size(); i++) {
			total += productSaleList.get(i).getGrossCost();
		}

		return total;
	}

	public static double getSubtotal(Invoice invoice) {
		return getTotalTax(invoice) + getTotalFee(invoice) + getGrossTotal(invoice);
	}

}

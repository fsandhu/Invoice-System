package com.cinco;

import java.util.Map;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This class contains methods to produce a executive summary from a map
 *         of invoices.
 *
 */
public class ExecutiveSummary {

	public static double getExecutiveGross(Map<String, Invoice> invoice) {

		double executiveGross = 0.0;

		for (Map.Entry<String, Invoice> entry : invoice.entrySet()) {
			// calculate total gross
			executiveGross += InvoiceUtils.getGrossTotal(entry.getValue());
		}

		return executiveGross;
	}

	public static double getExecutiveFees(Map<String, Invoice> invoice) {

		double executiveFees = 0.0;

		for (Map.Entry<String, Invoice> entry : invoice.entrySet()) {
			// calculate total fees
			executiveFees += InvoiceUtils.getTotalFee(entry.getValue());
		}

		return executiveFees;
	}

	public static double getExecutiveTaxes(Map<String, Invoice> invoice) {

		double executiveTax = 0.0;

		for (Map.Entry<String, Invoice> entry : invoice.entrySet()) {
			// calculate total tax
			executiveTax += InvoiceUtils.getTotalTax(entry.getValue());
		}

		return executiveTax;
	}

	public static double getExecutiveSubtotal(Map<String, Invoice> invoice) {

		double executiveSubtotal = 0.0;

		for (Map.Entry<String, Invoice> entry : invoice.entrySet()) {
			// calculate subtotal
			executiveSubtotal += InvoiceUtils.getSubtotal(entry.getValue());
		}

		return executiveSubtotal;
	}

	public static void printExecutiveReport(Map<String, Invoice> invoice) {

		String str = "";
		str += "Executive Summary Report\n";
		str += "=========================\n";
		str += String.format("%-10s %-40s %-30s %-13s %-13s %-13s %-13s\n", "Invoice", "Customer", "Salesperson",
				"Subtotal", "Fees", "Taxes", "Total");

		for (Map.Entry<String, Invoice> entry : invoice.entrySet()) {
			str += String.format("%-10s %-40s %-30s $ %-11.2f $ %-11.2f $ %-11.2f $ %-11.2f\n",
					entry.getValue().getInvoiceCode(), entry.getValue().getCustomer().getName(),
					entry.getValue().getSalesperson().printPersonName(), InvoiceUtils.getGrossTotal(entry.getValue()),
					InvoiceUtils.getTotalFee(entry.getValue()), InvoiceUtils.getTotalTax(entry.getValue()),
					InvoiceUtils.getSubtotal(entry.getValue()));
		}

		str += "=========================================================================================================================================\n";
		str += String.format("%-82s $ %-11.2f $ %-11.2f $ %-11.2f $ %-11.2f", "TOTAL:",
				ExecutiveSummary.getExecutiveGross(invoice), ExecutiveSummary.getExecutiveFees(invoice),
				ExecutiveSummary.getExecutiveTaxes(invoice), ExecutiveSummary.getExecutiveSubtotal(invoice));

		str += "\n\n";

		System.out.println(str);

	}

}

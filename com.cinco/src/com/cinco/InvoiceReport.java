package com.cinco;

import java.util.Map;

/**
 * @author fsandhu
 * @author wchoi
 * 
 * This class is the main class to produce the invoice.
 *
 */
public class InvoiceReport {
	
	public static void main (String args[]) {

		//Construct Maps for main objects, then store data loaded in from SQL database.
		
		Map<String, Person> personMap =  DataUtilsSQL.loadPersonData();
		Map<String, Customer> customerMap =  DataUtilsSQL.loadCustomerData(personMap);
		Map<String, Product> productMap =  DataUtilsSQL.loadProductData(personMap);
        Map<String, Invoice> invoiceMap = DataUtilsSQL.loadInvoiceData(personMap, customerMap, productMap);

        //Print the invoice.
        
        ExecutiveSummary.printExecutiveReport(invoiceMap);
        
        for (Map.Entry<String, Invoice> entry : invoiceMap.entrySet()) {
        	System.out.println(entry.getValue());
        }

	}

}

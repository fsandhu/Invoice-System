package com.cinco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author fsandhu
 * @author wchoi
 *
 *         This class contains all function to load, trim and parse data.
 */

public class DataUtils {

	public static Map<String, Person> loadPersonData(String filePath) {

		Scanner personScanner = null;
		try {
			personScanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // scanner for the person data file

		int numberOfRecords = Integer.parseInt(personScanner.nextLine()); // get
																			// number
																			// of
		// records in
		// the file

		HashMap<String, Person> personMap = new HashMap<String, Person>(); // create
																			// a
																			// new
																			// map
																			// for
																			// person
																			// with
																			// key
																			// mapping
																			// to
																			// person
																			// code

		String dataToken[] = null;

		for (int i = 0; i < numberOfRecords; i++) {

			dataToken = personScanner.nextLine().split(";", -1);
			String personCode = null;
			String nameToken[] = null;
			String lastName = null;
			String firstName = null;
			String addressToken[] = null;
			Address address = null;
			String emailTokens[] = null;

			// check for empty strings after split and also trim whitespace

			if (!dataToken[0].equals("")) {
				personCode = dataToken[0].trim();
			}
			if (!dataToken[1].equals("")) {
				nameToken = dataToken[1].split(",");
				lastName = nameToken[0].trim();
				firstName = nameToken[1].trim();
			}
			if (!dataToken[2].equals("")) {
				addressToken = dataToken[2].split(",", -1);
				address = new Address(addressToken[0].trim(), addressToken[1].trim(), addressToken[2].trim(),
						addressToken[3].trim(), addressToken[4].trim());
			}

			ArrayList<String> emails = new ArrayList<String>(); // array list to
																// hold emails

			if (dataToken.length == 4 && !dataToken[3].equals("")) {
				emailTokens = dataToken[3].split(",", -1);

				for (int j = 0; j < emailTokens.length; j++) {
					emails.add(emailTokens[j].trim());
				}
			}

			Person p = new Person(personCode, lastName, firstName, address, emails);
			personMap.put(personCode, p); // add to list
		}

		return personMap;
	}

	public static Map<String, Customer> loadCustomerData(String filePath, Map<String, Person> personMap) {

		HashMap<String, Customer> customerMap = new HashMap<String, Customer>(); // array
																					// list
																					// for
																					// customers

		Scanner customerScanner = null;
		try {
			customerScanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // scanner for the person data file

		int numberOfRecords = Integer.parseInt(customerScanner.nextLine()); // get
																			// number
		// of
		// records
		// in the
		// file

		for (int i = 0; i < numberOfRecords; i++) {

			String dataToken[] = null;
			String customerCode = null;
			char customerType = 0;
			String primaryContactCode = null;
			Person primaryContact = null;
			String name = null;
			String addressToken[] = null;
			Address address = null;

			dataToken = customerScanner.nextLine().split(";", -1);

			if (!dataToken[0].equals("")) {
				customerCode = dataToken[0].trim();
			}
			if (!dataToken[1].equals("")) {
				customerType = dataToken[1].charAt(0);
			}
			if (!dataToken[2].equals("")) {
				primaryContactCode = dataToken[2].trim();
				primaryContact = personMap.get(primaryContactCode);
				// check for person that matches the primaryContact code
			}
			if (!dataToken[3].equals("")) {
				name = dataToken[3].trim();
			}
			if (!dataToken[4].equals("")) {
				addressToken = dataToken[4].split(",");
				address = new Address(addressToken[0].trim(), addressToken[1].trim(), addressToken[2].trim(),
						addressToken[3].trim(), addressToken[4].trim());
			}

			GovernmentCustomer g = null;
			CompanyCustomer c = null;

			if (customerType == 'G') {
				g = new GovernmentCustomer(customerCode, name, primaryContact, address);
				customerMap.put(customerCode, g); // add customer to list
			}

			if (customerType == 'C') {
				c = new CompanyCustomer(customerCode, name, primaryContact, address);
				customerMap.put(customerCode, c); // add customer to list
			}
		}

		customerScanner.close();

		return customerMap;
	}

	public static Map<String, Product> loadProductData(String filePath, Map<String, Person> personMap) {

		HashMap<String, Product> productMap = new HashMap<String, Product>(); // new
																				// product
																				// list

		Scanner productScanner = null;
		try {
			productScanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // scanner for the person data file

		int numberOfRecords = Integer.parseInt(productScanner.nextLine());

		for (int i = 0; i < numberOfRecords; i++) {

			String dataToken[] = null;
			String productCode = null;
			char type = 0;
			String name = null;
			double pricePerUnit = 0.0;
			double serviceFee = 0.0;
			double annualLicenseFee = 0.0;
			String personCode = null;
			Person consultant = null;
			double hourlyFee = 0.0;

			dataToken = productScanner.nextLine().split(";", -1);

			if (!dataToken[0].equals("")) {
				productCode = dataToken[0].trim();
			}
			if (!dataToken[1].equals("")) {
				type = dataToken[1].charAt(0);
			}
			if (!dataToken[2].equals("")) {
				name = dataToken[2].trim();
			}

			// check what type of product is being processed and then add to
			// appropriate
			// subclass

			if (type == 'E') {
				if (!dataToken[3].equals("")) {
					pricePerUnit = Double.parseDouble(dataToken[3]);
				}
				Equipment eq = new Equipment(productCode, name, pricePerUnit);
				productMap.put(productCode, eq);
			}

			if (type == 'L') {
				if (!dataToken[3].equals("")) {
					serviceFee = Double.parseDouble(dataToken[3]);
				}
				if (!dataToken[4].equals("")) {
					annualLicenseFee = Double.parseDouble(dataToken[4]);
				}
				License li = new License(productCode, name, serviceFee, annualLicenseFee);
				productMap.put(productCode, li);
			}

			if (type == 'C') {
				if (!dataToken[3].equals("")) {
					personCode = dataToken[3];
					consultant = personMap.get(personCode);
				}
				if (!dataToken[4].equals("")) {
					hourlyFee = Double.parseDouble(dataToken[4]);
				}
				Consultation co = new Consultation(productCode, name, consultant, hourlyFee);
				productMap.put(productCode, co);
			}
		}

		productScanner.close(); // close scanner

		return productMap;
	}

	public static Map<String, Invoice> loadInvoiceData(String filePath, Map<String, Person> personMap,
			Map<String, Customer> customerMap, Map<String, Product> productMap) {

		HashMap<String, Invoice> invoiceMap = new HashMap<String, Invoice>(); // new
																				// product
																				// list

		Scanner invoiceScanner = null;
		try {
			invoiceScanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // scanner for the person data file

		int numberOfRecords = Integer.parseInt(invoiceScanner.nextLine());

		for (int i = 0; i < numberOfRecords; i++) {

			String dataToken[] = null;
			String invoiceCode = null;
			String customerCode = null;
			Customer customer = null;
			String salespersonCode = null;
			Person salesperson = null;
			String productToken[] = null;
			List<ProductSale> products = new ArrayList<>();

			dataToken = invoiceScanner.nextLine().split(";");

			if (!dataToken[0].equals("")) {
				invoiceCode = dataToken[0].trim();
			}
			if (!dataToken[1].equals("")) {
				customerCode = dataToken[1].trim();
				customer = customerMap.get(customerCode);
			}
			if (!dataToken[2].equals("")) {
				salespersonCode = dataToken[2].trim();
				salesperson = personMap.get(salespersonCode);
			}
			if (!dataToken[3].equals("")) {

				productToken = dataToken[3].split(",");

				for (String token : productToken) {
					String productSaleToken[] = token.split(":");
					Product p = productMap.get(productSaleToken[0].trim());

					// check what product is sold and make a new sale object

					if (p instanceof Equipment) {
						EquipmentSale eq = new EquipmentSale(p, Integer.parseInt(productSaleToken[1].trim()));
						products.add(eq);
					} else if (p instanceof Consultation) {
						ConsultationSale co = new ConsultationSale(p, Integer.parseInt(productSaleToken[1].trim()));
						products.add(co);
					} else if (p instanceof License) {
						LicenseSale li = new LicenseSale(p, productSaleToken[1].trim(), productSaleToken[2].trim());
						products.add(li);
					}

				}

			}

			Invoice invoice = new Invoice(invoiceCode, customer, salesperson, products);
			invoiceMap.put(invoiceCode, invoice);
		}

		invoiceScanner.close(); // close scanner

		return invoiceMap;
	}

	public static void outputToXML(Map<String, ?> x, String outputPath) {

		PrintWriter pw1 = null;
		try {
			pw1 = new PrintWriter(new File(outputPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // print to output file

		XStream xstream = new XStream();

		for (String s : x.keySet()) {

			String xml = null;
			if (x.get(s) instanceof Person) {
				Person p = (Person) x.get(s);
				xml = xstream.toXML(p); // conversion to XML using
										// xstream
			} else if (x.get(s) instanceof Customer) {
				Customer p = (Customer) x.get(s);
				xml = xstream.toXML(p); // conversion to XML using
										// xstream
			} else if (x.get(s) instanceof Product) {
				Product p = (Product) x.get(s);
				xml = xstream.toXML(p); // conversion to XML using
										// xstream
			}

			pw1.println(xml);
		}

		pw1.close(); // close printwriter
	}

}

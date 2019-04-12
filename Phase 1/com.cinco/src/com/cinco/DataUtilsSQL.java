package com.cinco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cinco.DatabaseInfo;

/**
 * @author fsandhu
 * @author wchoi
 * 
 * This reads in data from the SQL database.
 *
 */
public class DataUtilsSQL {

	public static Map<String, Person> loadPersonData() {

		Map<String, Person> personMap = new HashMap<String, Person>();
		
		//Get connected to Database.

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		//Write query to call a String of necessary data from SQL Database, then store them in objects.

		String query = "SELECT * FROM Person p JOIN Address a ON p.addressId = a.addressId JOIN Email e on e.personId = p.personId";

		try {
			PreparedStatement psPersonData = conn.prepareStatement(query);
			ResultSet rsPersonData = psPersonData.executeQuery();

			while (rsPersonData.next()) {
				String personCode = rsPersonData.getString("personCode");
				String personFirstName = rsPersonData.getString("firstName");
				String personLastName = rsPersonData.getString("lastName");
				String street = rsPersonData.getString("street");
				String city = rsPersonData.getString("city");
				String state = rsPersonData.getString("state");
				String country = rsPersonData.getString("country");
				String zip = rsPersonData.getString("zip");
				Address a = new Address(street, city, state, zip, country);

				//check persons already in database and update emails
				
				if (personMap.containsKey(personCode)) {
					String email = rsPersonData.getString("email_address");
					Person p = personMap.get(personCode);
					ArrayList<String> tempEmailList = p.getEmails();
					tempEmailList.add(email);
					personMap.put(personCode, p);
				} else {
					String email = rsPersonData.getString("email_address");
					ArrayList<String> Emails = new ArrayList<String>();
					Emails.add(email);
					Person p = new Person(personCode, personLastName, personFirstName, a, Emails);
					personMap.put(personCode, p);
				}
			}
			
			//Close all the connections.
			
			if(rsPersonData != null) {
				rsPersonData.close();
			}
			if(psPersonData != null) {
				psPersonData.close();
			}
			if(conn != null) {
			conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personMap;
	}

	public static Map<String, Customer> loadCustomerData(Map<String, Person> personMap) {

		Map<String, Customer> customerMap = new HashMap<String, Customer>();

		//Get connected to Database.
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//Write query to call a String of necessary data from SQL Database, then store them in objects.
		
		String query = "SELECT * FROM Customer c JOIN Person p on p.personId = c.primaryPersonId JOIN Address a ON c.addressId = a.addressId";

		try {
			PreparedStatement psCustomerData = conn.prepareStatement(query);
			ResultSet rsCustomerData = psCustomerData.executeQuery();

			while (rsCustomerData.next()) {

				String customerCode = rsCustomerData.getString("customerCode");
				String customerName = rsCustomerData.getString("customerName");
				String primaryPersonId = rsCustomerData.getString("personCode");
				String customerType = rsCustomerData.getString("customerType");
				String street = rsCustomerData.getString("street");
				String city = rsCustomerData.getString("city");
				String state = rsCustomerData.getString("state");
				String country = rsCustomerData.getString("country");
				String zip = rsCustomerData.getString("zip");
				Address a = new Address(street, city, state, zip, country);
				Person primaryContact = personMap.get(primaryPersonId);

				if (customerType.charAt(0) == 'C') {
					CompanyCustomer c = new CompanyCustomer(customerCode, customerName, primaryContact, a);
					customerMap.put(customerCode, c);
				}

				if (customerType.charAt(0) == 'G') {
					GovernmentCustomer g = new GovernmentCustomer(customerCode, customerName, primaryContact, a);
					customerMap.put(customerCode, g);
				}
			}

			//Close all the connections.
			
			if(rsCustomerData != null) {
				rsCustomerData.close();
			}
			if(psCustomerData != null) {
				psCustomerData.close();
			}
			if(conn != null) {
			conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerMap;

	}

	public static Map<String, Product> loadProductData(Map<String, Person> personMap) {

		Map<String, Product> productMap = new HashMap<String, Product>();
		
		//Get connected to Database.

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		//Write query to call a String of necessary data from SQL Database, then store them in objects.

		String query = "SELECT * FROM Product pr LEFT JOIN Person p ON pr.consultantId = p.personId";

		try {
			PreparedStatement psProductData = conn.prepareStatement(query);
			ResultSet rsProductData = psProductData.executeQuery();

			while (rsProductData.next()) {

				String productCode = rsProductData.getString("productCode");
				String productName = rsProductData.getString("name");
				String productType = rsProductData.getString("type");

				if (productType.charAt(0) == 'E') {
					double price = rsProductData.getFloat("pricePerUnit");
					Equipment e = new Equipment(productCode, productName, price);
					productMap.put(productCode, e);
				}

				if (productType.charAt(0) == 'C') {
					String consultantCode = rsProductData.getString("personCode");
					Person consultant = personMap.get(consultantCode);
					double hourlyFee = rsProductData.getFloat("hourlyFee");
					Consultation c = new Consultation(productCode, productName, consultant, hourlyFee);
					productMap.put(productCode, c);
				}

				if (productType.charAt(0) == 'L') {
					double serviceFee = rsProductData.getFloat("serviceFee");
					double annualFee = rsProductData.getFloat("annualFee");
					License l = new License(productCode, productName, serviceFee, annualFee);
					productMap.put(productCode, l);
				}
			}

			//Close all the connections.
			
			if(rsProductData != null) {
				rsProductData.close();
			}
			if(psProductData != null) {
				psProductData.close();
			}
			if(conn != null) {
			conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productMap;
	}

	public static Map<String, Invoice> loadInvoiceData(Map<String, Person> personMap, Map<String, Customer> customerMap,
			Map<String, Product> productMap) {

		Map<String, Invoice> invoiceMap = new HashMap<String, Invoice>();
		
		//Get connected to Database.

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = "SELECT i.invoiceCode, i.salesPersonCode, c.customerCode, p.productCode, p.type, ip.noOfUnits,"
				+ "ip.noOfBillableHours, ip.startDate, ip.endDate, "
				+ "pr.personCode AS consultantCode FROM Invoice i "
				+ "JOIN Customer c ON c.customerId = i.customerId "
				+ "JOIN InvoiceProduct ip ON i.invoiceId = ip.invoiceId "
				+ "JOIN Product p ON ip.productId = p.productId "
				+ "LEFT JOIN Person pr ON pr.personId = p.consultantId;";

		try {
			PreparedStatement psInvoiceData = conn.prepareStatement(query);
			ResultSet rsInvoiceData = psInvoiceData.executeQuery();

			while (rsInvoiceData.next()) {

				String invoiceCode = rsInvoiceData.getString("invoiceCode");
				String customerCode = rsInvoiceData.getString("customerCode");
				String salesPersonCode = rsInvoiceData.getString("salesPersonCode");
				Customer customer = customerMap.get(customerCode);
				Person salesperson = personMap.get(salesPersonCode);

				
				//check the map for invoices already saved
				
				if (invoiceMap.containsKey(invoiceCode)) {
					Invoice i = invoiceMap.get(invoiceCode);
					List<ProductSale> products = i.getProducts();
					
					String productCode = rsInvoiceData.getString("productCode");
					Product p = productMap.get(productCode);
					String saleType = rsInvoiceData.getString("type");
					
					if(saleType.charAt(0) == 'E') {
						int noOfUnits = rsInvoiceData.getInt("noOfUnits");
						EquipmentSale eqSale = new EquipmentSale(p, noOfUnits);
						products.add(eqSale);
					}
					
					if(saleType.charAt(0) == 'C') {
						int noOfBillableHours = rsInvoiceData.getInt("noOfBillableHours");
						ConsultationSale coSale = new ConsultationSale(p, noOfBillableHours);
						products.add(coSale);
					}
					
					if(saleType.charAt(0) == 'L') {
						String startDate = rsInvoiceData.getString("startDate");
						String endDate = rsInvoiceData.getString("endDate");
						LicenseSale liSale = new LicenseSale(p, startDate, endDate);
						products.add(liSale);
					}
					
					invoiceMap.put(invoiceCode, i);	
					
				} else {
					
					List<ProductSale> products = new ArrayList<ProductSale>();
					String productCode = rsInvoiceData.getString("productCode");
					Product p = productMap.get(productCode);
					String saleType = rsInvoiceData.getString("type");
					
					if(saleType.charAt(0) == 'E') {
						int noOfUnits = rsInvoiceData.getInt("noOfUnits");
						EquipmentSale eqSale = new EquipmentSale(p, noOfUnits);
						products.add(eqSale);
					}
					
					if(saleType.charAt(0) == 'C') {
						int noOfBillableHours = rsInvoiceData.getInt("noOfBillableHours");
						ConsultationSale coSale = new ConsultationSale(p, noOfBillableHours);
						products.add(coSale);
					}
					
					if(saleType.charAt(0) == 'L') {
						String startDate = rsInvoiceData.getString("startDate");
						String endDate = rsInvoiceData.getString("endDate");
						LicenseSale liSale = new LicenseSale(p, startDate, endDate);
						products.add(liSale);
					}
					
					Invoice i = new Invoice (invoiceCode, customer, salesperson, products);
					invoiceMap.put(invoiceCode, i);	
					
				}
			}

			//Close all the connections.
			
			if(rsInvoiceData != null) {
				rsInvoiceData.close();
			}
			if(psInvoiceData != null) {
				psInvoiceData.close();
			}
			if(conn != null) {
			conn.close();
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invoiceMap;
	}

}

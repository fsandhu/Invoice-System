package com.cinco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {

		Connection conn = ConnectionUtils.newConnection();

		String query = "UPDATE Invoice SET salesPersonId = null;";
		String query1 = "UPDATE Customer SET primaryPersonId = null;";
		String query2 = "UPDATE Email SET personId = null;";
		String query3 = "UPDATE Product SET consultantId = null;";
		String query4 = "DELETE from Person;";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.executeQuery();
			ps = conn.prepareStatement(query2);
			ps.executeQuery();
			ps = conn.prepareStatement(query3);
			ps.executeQuery();
			ps = conn.prepareStatement(query4);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);
	}

	/**
	 * Removes the person record from the database corresponding to the provided
	 * <code>personCode</code>
	 * 
	 * @param personCode
	 */
	public static void removePerson(String personCode) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "UPDATE Invoice SET salesPersonId = null WHERE personCode = '?';";
		String query1 = "UPDATE Customer SET primaryPersonId = null WHERE personCode '?';";
		String query2 = "UPDATE Email SET personId = null WHERE personCode = '?';";
		String query3 = "UPDATE Product SET consultantId = null WHERE personCode = '?';";
		String query4 = "DELETE from Person = null WHERE personCode = '?';";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.setString(1, personCode);
			ps.executeQuery();
			ps = conn.prepareStatement(query2);
			ps.setString(1, personCode);
			ps.executeQuery();
			ps = conn.prepareStatement(query3);
			ps.setString(1, personCode);
			ps.executeQuery();
			ps = conn.prepareStatement(query4);
			ps.setString(1, personCode);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);
	}

	/**
	 * Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "INSERT INTO Address (street, city, state, country, zip) VALUES (?, ?, ?, ?, ?);";
		String query1 = "INSERT INTO Person (firstName, lastName, personCode, addressId) VALUES (?, ?, ?, "
				+ "SELECT addressId FROM Address WHERE street = '?');";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, country);
			ps.setString(5, zip);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, personCode);
			ps.setString(4, street);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "INSERT INTO Email (personCode, email_address) VALUES (?, ?);";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, email);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);
	}

	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {

		Connection conn = ConnectionUtils.newConnection();

		String query = "UPDATE Invoice SET customerId = null;";
		String query1 = "DELETE FROM Customer;";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addCustomer(String customerCode, String type, String primaryContactPersonCode, String name,
			String street, String city, String state, String zip, String country) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "INSERT INTO Address (street, city, state, country, zip) VALUES (?, ?, ?, ?, ?);";
		String primaryPersonId = InvoiceData.getPersonId(primaryContactPersonCode);
		String query1 = "INSERT INTO Customer (customerCode, customerType, customerName, addressId, primaryPersonId)"
				+ "VALUES (?, ?, ?, (SELECT addressId FROM Address WHERE street = '?'), ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, country);
			ps.setString(5, zip);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.setString(1, customerCode);
			ps.setString(2, type);
			ps.setString(3, name);
			ps.setString(4, street);
			ps.setString(5, primaryPersonId);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {

		Connection conn = ConnectionUtils.newConnection();

		String query = "UPDATE InvoiceProduct SET productId = null;";
		String query1 = "DELETE FROM Product;";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Removes a particular product record from the database corresponding to the
	 * provided <code>productCode</code>
	 * 
	 * @param assetCode
	 */
	public static void removeProduct(String productCode) {

		Connection conn = ConnectionUtils.newConnection();

		String productId = InvoiceData.getProductId(productCode);
		String query = "UPDATE InvoiceProduct SET productId = null WHERE productId = '?';";
		String query1 = "DELETE FROM Product WHERE productId = '?';";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			ps.executeQuery();
			ps = conn.prepareStatement(query1);
			ps.setString(1, productId);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Adds an equipment record to the database with the provided data.
	 */
	public static void addEquipment(String productCode, String name, Double pricePerUnit) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "INSERT INTO Product (productCode, name, pricePerUnit, type) VALUES (?, ?, ?, 'E');";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setFloat(3, pricePerUnit);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Adds an license record to the database with the provided data.
	 */
	public static void addLicense(String productCode, String name, double serviceFee, double annualFee) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "INSERT INTO Product (productCode, name, serviceFee, annualFee, type) VALUES (?, ?, ?, ?, 'L');";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setFloat(3, serviceFee);
			ps.setFloat(4, annualFee);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Adds an consultation record to the database with the provided data.
	 */
	public static void addConsultation(String productCode, String name, String consultantPersonCode, Double hourlyFee) {

		Connection conn = ConnectionUtils.newConnection();
		
		String consultantId = InvoiceData.getPersonId(consultantPersonCode);
		String query = "INSERT INTO Product (productCode, name, consultantId, hourlyFee, type) VALUES (?, ?, ?, ?, 'C');";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setFloat(3, consultantId);
			ps.setFloat(4, hourlyFee);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

	}

	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
	}

	/**
	 * Removes the invoice record from the database corresponding to the provided
	 * <code>invoiceCode</code>
	 * 
	 * @param invoiceCode
	 */
	public static void removeInvoice(String invoiceCode) {
	}

	/**
	 * Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode) {
	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of units
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String productCode, int numUnits) {
	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * begin/end dates
	 */
	public static void addLicenseToInvoice(String invoiceCode, String productCode, String startDate, String endDate) {
	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of billable hours.
	 */
	public static void addConsultationToInvoice(String invoiceCode, String productCode, double numHours) {
	}

	public static String getPersonId(String personCode) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "SELECT personId FROM Person WHERE personCode = ?";
		String personId = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ResultSet rs = ps.executeQuery();
			personId = rs.getString("personId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

		return personId;
	}

	public static String getProductId(String productCode) {

		Connection conn = ConnectionUtils.newConnection();

		String query = "SELECT productId FROM Product WHERE productCode = ?";
		String productId = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ResultSet rs = ps.executeQuery();
			productId = rs.getString("productId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionUtils.closeConnection(conn);

		return productId;
	}
}

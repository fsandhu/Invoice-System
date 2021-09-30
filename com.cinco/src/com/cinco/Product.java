package com.cinco;

/**
 * @author fsandhu
 * @author wchoi
 * 
 *         This is product class that holds appropriate data for a product.
 *         Class is abstract.
 *
 */

public abstract class Product {

	private String productCode;
	private String name;

	public Product(String productCode, String name) {
		this.productCode = productCode;
		this.name = name;
	}

	public final String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public final String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getType();
	
}

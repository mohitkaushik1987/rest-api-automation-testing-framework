package com.automation.data;

public class MultiplicationData {

	String number1;
	String number2;
	String product;

	// Extras
	String id;
	String createdAt;

	// getters and setters

	// Extras - to be deleted
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public MultiplicationData() {

	}

	public MultiplicationData(String number1, String number2) {
		this.number1 = number1;
		this.number2 = number2;

	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber2() {
		return number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

}

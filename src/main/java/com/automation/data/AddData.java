package com.automation.data;

public class AddData {
	
	String number1;
	String number2;
	String sum;
	String product;
	String quotient;

	
	
	

	//Extras
	String id;
	String createdAt;
	
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

	
	
	
	
	//getters and setters
	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getQuotient() {
		return quotient;
	}

	public void setQuotient(String quotient) {
		this.quotient = quotient;
	}

	
	
	
	
	public AddData() {
		
	}
	
	public AddData(String number1, String number2) {
		this.number1 = number1;
		this.number2 = number2;
		
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

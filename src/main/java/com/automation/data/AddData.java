package com.automation.data;

public class AddData {
	
	String augend;
	String addend;
	
	//default constructor
	public AddData() {
		
	}
	
	public AddData(String augend, String addend) {
		this.augend = augend;
		this.addend = addend;
	}
	
	/**
	 * @return the augend
	 */
	public String getAugend() {
		return augend;
	}

	/**
	 * @param augend the augend to set
	 */
	public void setAugend(String augend) {
		this.augend = augend;
	}

	/**
	 * @return the addend
	 */
	public String getAddend() {
		return addend;
	}

	/**
	 * @param addend the addend to set
	 */
	public void setAddend(String addend) {
		this.addend = addend;
	}
}

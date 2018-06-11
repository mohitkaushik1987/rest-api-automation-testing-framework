package com.automation.data;

public class MultiplyData {
	
	String multiplicand;
	String multiplier;
	
	//default constructor
	public MultiplyData() {
		
	}
	
	public MultiplyData(String multiplicand, String multiplier) {
		this.multiplicand = multiplicand;
		this.multiplier = multiplier;
	}

	/**
	 * @return the multiplicand
	 */
	public String getMultiplicand() {
		return multiplicand;
	}

	/**
	 * @param multiplicand the multiplicand to set
	 */
	public void setMultiplicand(String multiplicand) {
		this.multiplicand = multiplicand;
	}

	/**
	 * @return the multiplier
	 */
	public String getMultiplier() {
		return multiplier;
	}

	/**
	 * @param multiplier the multiplier to set
	 */
	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}
	
}

package com.automation.data;

public class DivisionData {
	
	String dividend;
	String divisor;
	
	//default constructor
	public DivisionData() {
		
	}
	
	public DivisionData(String dividend, String divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}

	/**
	 * @return the dividend
	 */
	public String getDividend() {
		return dividend;
	}

	/**
	 * @param dividend the dividend to set
	 */
	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	/**
	 * @return the divisor
	 */
	public String getDivisor() {
		return divisor;
	}

	/**
	 * @param divisor the divisor to set
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
	}

}

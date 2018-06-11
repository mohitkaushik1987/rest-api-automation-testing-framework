package com.automation.data;

import java.util.HashSet;
import java.util.Set;

public class ResponseData {

	String sum;
	String product;
	String quotient;
	String max;
	String min;
	Set<String> union;
	Set<String> intersection;

	/**
	 * @return the sum
	 */
	public String getSum() {
		return sum;
	}

	/**
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(String sum) {
		this.sum = sum;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the quotient
	 */
	public String getQuotient() {
		return quotient;
	}

	/**
	 * @param quotient
	 *            the quotient to set
	 */
	public void setQuotient(String quotient) {
		this.quotient = quotient;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(String min) {
		this.min = min;
	}

	/**
	 * @return the union
	 */
	public Set<String> getUnion() {
		return union;
	}

	/**
	 * @param union
	 *            the union to set
	 */
	public void setUnion(HashSet<String> union) {
		this.union = union;
	}

	/**
	 * @return the intersection
	 */
	public Set<String> getIntersection() {
		return intersection;
	}

	/**
	 * @param intersection
	 *            the intersection to set
	 */
	public void setIntersection(Set<String> intersection) {
		this.intersection = intersection;
	}

}

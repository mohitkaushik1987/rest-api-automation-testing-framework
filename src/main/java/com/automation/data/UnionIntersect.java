package com.automation.data;

import java.util.ArrayList;
import java.util.List;

public class UnionIntersect {

	List<String> left = null;
	List<String> right = null;

	public UnionIntersect() {
		left = new ArrayList<String>();
		right = new ArrayList<String>();
	}

	public UnionIntersect(List<String> left, List<String> right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public List<String> getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(List<String> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public List<String> getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(List<String> right) {
		this.right = right;
	}

}

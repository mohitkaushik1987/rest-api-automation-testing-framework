package com.automation.data;

import java.util.ArrayList;
import java.util.List;

public class MaxMinData {

	List<String> numbers = null;

	public MaxMinData() {
		numbers = new ArrayList<String>();
	}

	public MaxMinData(List<String> numbers) {
		this.numbers = numbers;
	}

	// getters and setters

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumSet(List<String> numbers) {
		this.numbers = numbers;
	}

}

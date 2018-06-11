package com.automation.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.client.RestClient;
import com.automation.data.AddData;
import com.automation.data.MaxMinData;
import com.automation.data.UnionIntersect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorCodesTest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;

	// Define URLs
	String endpoint_addition;
	String url_addition;
	String endpoint_multiply;
	String url_multiply;
	String endpoint_division;
	String url_division;
	String endpoint_union;
	String url_union;
	String endpoint_intersect;
	String url_intersect;
	String endpoint_max;
	String url_max;
	String endpoint_min;
	String url_min;

	String url_404;
	String url_400;
	String url_500;
	String url_401;

	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		endpoint_addition = prop.getProperty("ENDPOINT_ADDITION");
		endpoint_multiply = prop.getProperty("ENDPOINT_MULTIPLY");
		endpoint_division = prop.getProperty("ENDPOINT_DIVISION");
		endpoint_intersect = prop.getProperty("ENDPOINT_INTERSECT");
		endpoint_union = prop.getProperty("ENDPOINT_UNION");
		endpoint_max = prop.getProperty("ENDPOINT_MAX");
		endpoint_min = prop.getProperty("ENDPOINT_MIN");

		// https://reqres.in/api/users

		url = serviceUrl + apiUrl;
		url_404 = serviceUrl + "/add/2";
		url_400 = serviceUrl + "/api/users/32222";
		url_500 = serviceUrl + "/error";
		url_401 = serviceUrl + "/helloThere";

		// https://reqres.in/api/users

		url_addition = serviceUrl + endpoint_addition;
		url_multiply = serviceUrl + endpoint_multiply;
		url_division = serviceUrl + endpoint_division;
		url_intersect = serviceUrl + endpoint_intersect;
		url_union = serviceUrl + endpoint_union;
		url_max = serviceUrl + endpoint_max;
		url_min = serviceUrl + endpoint_min;

	}

	// **************************
	// 500 Internal Server Error
	// **************************
	// @Test(priority = 1)
	@Test
	public void statusCode_500_InternalServerError() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@amazon.com");
		// headerMap.put("password", "test213");
		// headerMap.put("Auth Token", "12345");

		closebaleHttpResponse = restClient.get(url_500, headerMap);

		// Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		// Assertion
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_500, "Status code is not 500");

	}

	// ************************************************************************
	// HTTP CODE 404 - NOT FOUND
	// ************************************************************************

	@Test(priority = 2)
	public void statusCode_404_NOT_FOUND() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("a", "5t");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_404, addDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_404);

	}

	// ************************************************************************
	// HTTP CODE 400 - BAD REQUEST - Testing - Checking for invalid data types
	// ************************************************************************

	// Addition API Test - BAD DATA for HTTP 400
	@Test(priority = 3)
	public void additionTest_400_Bad_Request_non_integer()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("a", "4");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}

	// Max - Endpoint - Bad Data

	@Test
	public void maxTest1_NonIntegersInSet_400BadRequest()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> numbers = new ArrayList<String>();
		numbers.add("xyz");
		numbers.add("34");
		numbers.add("78");
		numbers.add("1");
		MaxMinData maxData = new MaxMinData(numbers);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\auto\\data\\maxData.json"), maxData);

		// object to json in string
		String maxDataJsonString = mapper.writeValueAsString(maxData);
		System.out.println("This is maxDataJsonString:" + maxDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_max, maxDataJsonString, headerMap);

		// System.out.println(restClient.post(url_addition, maxDataJsonString,
		// headerMap));

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}

	// Union -Endpoint - Bad Data
	@Test
	public void unionTest1_NonIntegersInLeftSet_400BadRequest()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("ab");
		left.add("2");
		left.add("3");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("5");
		right.add("6");

		UnionIntersect unionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\auto\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}

	// ************************************
	// HTTP 405 - Method Not Allowed (GET)
	// ************************************

	@Test(priority = 4)
	public void statusCode_405_MethodNotAllowed() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// GET METHOD- Will return 415 on /add path
		closebaleHttpResponse = restClient.get(url_addition, headerMap);

		// Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		// Assertion
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_405, "Status code is not 405");

	}
	// *********************************************************
	// HTTP 415 - Unsupported Media Type - using application/xml
	// *********************************************************

	@Test(priority = 5)
	public void statusCode_415_UnsupportedMediaType()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/xml"); // UNSUPPORTED MEDIA TYPE

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("3", "4");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_415);

	}

	// ************************************************************************
	// HTTP CODE 401 - UNAUTHORIZED - Checking for Authentication
	// ************************************************************************

	// ERROR CODE - HTTP 401 - Unauthorized (/addition) - Similar test cases can be
	// added for other resources (/multiply, etc)
	@Test(priority = 6)
	public void statusCode_401_UNAUTHORIZED_addition_path()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// This is important. Auth-token will define the security for API. Service
		// should be configured to take only defined tokens.
		headerMap.put("Auth-Token", "12345"); // CAN USED FOR AUTHENTICATION

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("7", "-2");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
	}

}

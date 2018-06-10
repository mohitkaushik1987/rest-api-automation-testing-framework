package com.automation.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.client.RestClient;
import com.automation.data.AddData;
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
		url_404 = serviceUrl + "/helloThere";
		url_400 = serviceUrl + "/api/users/32222";
		url_500 = serviceUrl + "/helloThere";
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

	// @Test(priority = 1)
	public void statusCode_200_OK() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@amazon.com");
		// headerMap.put("password", "test213");
		// headerMap.put("Auth Token", "12345");

		closebaleHttpResponse = restClient.get(url, headerMap);

		// a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

	}

	// @Test(priority = 3)
	public void statusCode404_NotFound() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@amazon.com");
		// headerMap.put("password", "test213");
		// headerMap.put("Auth Token", "12345");

		closebaleHttpResponse = restClient.get(url_404, headerMap);

		// a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		// Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not
		// 200");
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_404, "Status code is not 404");

	}

	// @Test(priority=4)
	public void statusCode_400_Bad_Request() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@amazon.com");
		// headerMap.put("password", "test213");
		// headerMap.put("Auth Token", "12345");

		closebaleHttpResponse = restClient.get(url_400, headerMap);

		// a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		// Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not
		// 200");
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_400, "Status code is not 400");

	}

	// @Test(priority = 2)
	public void statusCode_401_Unauthorized() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		// Provide Headers
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		closebaleHttpResponse = restClient.get(url_401, headerMap);

		// Get status code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		// Assert
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_401, "Status code is not 401");

	}

	// @Test(priority = 3)
	public void statusCode_500_InternalServerError() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "test@amazon.com");
		// headerMap.put("password", "test213");
		// headerMap.put("Auth Token", "12345");

		closebaleHttpResponse = restClient.get(url_500, headerMap);

		// a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		// Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not
		// 200");
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_500, "Status code is not 500");

	}

	// ************************************************************************
	// HTTP CODE 401 - UNAUTHORIZED - Checking for Authentication
	// ************************************************************************
	// ERROR CODE - HTTP 401 - Unauthorized (/addition)

	@Test(priority = 1)
	public void statusCode_401_UNAUTHORIZED_addition_path()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// This is important. Auth-token will define the security for API. Service
		// should be configured to take only defined tokens.
		headerMap.put("Auth-Token", "12345");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("b", "5y");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
	}

	// ERROR CODE - HTTP 401 - Unauthorized (/multiply)

	@Test(priority = 2)
	public void statusCode_401_UNAUTHORIZED_multiply_path()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// This is important. Auth-token will define the security for API. Service
		// should be configured to take only defined tokens.
		headerMap.put("Auth-Token", "12345");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("a", "5t");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, addDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
	}

	// ERROR CODE - HTTP 401 - Unauthorized (/division)

	@Test(priority = 3)
	public void statusCode_401_UNAUTHORIZED_division_path()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// This is important. Auth-token will define the security for API. Service
		// should be configured to take only defined tokens.
		// Additional tokens could be defined for the service.
		headerMap.put("Auth-Token", "12345");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("a", "5t");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_division, addDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
	}

	// ************************************************************************
	// HTTP CODE 404 - NOT FOUND
	// ************************************************************************

	@Test(priority = 4)
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
	@Test(priority = 5)
	public void additionTest_400_Bad_Request() throws JsonGenerationException, JsonMappingException, IOException {
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

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

	}

	// Multiplication Test - BAD DATA for HTTP 400
	@Test(priority = 6)
	public void multiplytest_400_Bad_Request() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("xyf", "813");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\MultiplicationData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

	}

	// Division Test - BAD DATA for HTTP 400
	@Test(priority = 7)
	public void divisionTest_400_Bad_Request() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("xwqw", "hgh37");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\DivisionData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_division, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

	}

}

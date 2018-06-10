package com.automation.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.client.RestClient;
import com.automation.data.AddData;
import com.automation.data.MultiplicationData;
import com.automation.data.Users;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIIntegrationTest extends TestBase {
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
		//
		url_addition = serviceUrl + endpoint_addition;
		url_multiply = serviceUrl + endpoint_multiply;
		url_division = serviceUrl + endpoint_division;
		url_intersect = serviceUrl + endpoint_intersect;
		url_union = serviceUrl + endpoint_union;
		url_max = serviceUrl + endpoint_max;
		url_min = serviceUrl + endpoint_min;

	}

	// Addition API Test
	@Test
	public void additionTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("10", "776");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		AddData addDataResObj = mapper.readValue(responseString, AddData.class); // actual users object
		System.out.println(addDataResObj);

		Assert.assertTrue(addData.getNumber1().equals(addDataResObj.getNumber1()));

		Assert.assertTrue(addData.getNumber2().equals(addDataResObj.getNumber2()));

		String expectedSum = "786";
		// Assert.assertTrue(expectedSum.equals(addDataResObj.getSum()));

		String expectedId = "906";
		// Assert.assertTrue(expectedId.equals(addDataResObj.getId()));

		System.out.println(addDataResObj.getSum());
		// System.out.println(addDataResObj.getCreatedAt());

	}

	// Ignore this test
	//@Test
	public void multiplicationTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplicationData multiplicationData = new MultiplicationData("776", "10");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\MultiplicationData.json"), multiplicationData);

		// object to json in string
		String multiplicationDataJsonString = mapper.writeValueAsString(multiplicationData);
		System.out.println(multiplicationDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multiplicationDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		MultiplicationData multiplicationDataResObj = mapper.readValue(responseString, MultiplicationData.class); // actual
																													// users
																													// object
		System.out.println(multiplicationDataResObj);

		Assert.assertTrue(multiplicationData.getNumber1().equals(multiplicationDataResObj.getNumber1()));

		Assert.assertTrue(multiplicationData.getNumber2().equals(multiplicationDataResObj.getNumber2()));

		String expectedProduct = "7760";
		// Assert.assertTrue(expectedProduct.equals(multiplicationDataResObj.getProduct()));

		String expectedId = "906";
		// Assert.assertTrue(expectedId.equals(addDataResObj.getId()));

		System.out.println(multiplicationDataResObj.getProduct());
		// System.out.println(addDataResObj.getCreatedAt());

	}

	// Multiplication Test
	@Test
	public void multiplytest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("10", "776");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\MultiplicationData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		AddData addDataResObj = mapper.readValue(responseString, AddData.class); // actual users object
		System.out.println(addDataResObj);

		Assert.assertTrue(addData.getNumber1().equals(addDataResObj.getNumber1()));

		Assert.assertTrue(addData.getNumber2().equals(addDataResObj.getNumber2()));

		String expectedProduct = "7760";
		// Assert.assertTrue(expectedProduct.equals(addDataResObj.getProduct()));

		String expectedId = "906";
		// Assert.assertTrue(expectedId.equals(addDataResObj.getId()));

		System.out.println(addDataResObj.getProduct());
		// System.out.println(addDataResObj.getCreatedAt());

	}

	// Division Test
	@Test
	public void divisionTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("10", "776");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\DivisionData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_division, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		AddData addDataResObj = mapper.readValue(responseString, AddData.class); // actual users object
		System.out.println(addDataResObj);

		Assert.assertTrue(addData.getNumber1().equals(addDataResObj.getNumber1()));

		Assert.assertTrue(addData.getNumber2().equals(addDataResObj.getNumber2()));

		String expectedQuotient = "78";
		// Assert.assertTrue(expectedQuotient.equals(addDataResObj.getQuotient()));

		String expectedId = "906";
		// Assert.assertTrue(expectedId.equals(addDataResObj.getId()));

		System.out.println("Actual Quotient is: " + addDataResObj.getQuotient());
		// System.out.println(addDataResObj.getCreatedAt());

	}

}

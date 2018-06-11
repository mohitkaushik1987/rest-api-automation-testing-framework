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
import com.automation.data.MultiplyData;
import com.automation.data.ResponseData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MultiplyServiceTest extends TestBase {
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

	// ************************************************************************
	// HTTP CODE 400 - BAD REQUEST - Testing - Checking for invalid data types
	// ************************************************************************

	// Addition API Test - BAD DATA for HTTP 400
	@Test
	public void multiplytest1_Non_Integers_400BadRequest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("ab", "776");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\MultiplicationData.json"), multiplyData);

		// object to json in string
		String multDataJsonString = mapper.writeValueAsString(multiplyData);
		System.out.println("Request::: "+multDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multDataJsonString, headerMap);

		//Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

		
	}
	
	@Test
	public void multiplytest2_OneNumberAsNull_400BadRequest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("89", "");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\MultiplicationData.json"), multiplyData);

		// object to json in string
		String multDataJsonString = mapper.writeValueAsString(multiplyData);
		System.out.println("Request::: "+multDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multDataJsonString, headerMap);

		//Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

		
	}
	
	@Test
	public void multiplytest3_OneNumberAndOneSpecialCharacter_400BadRequest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("89", "^");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\MultiplicationData.json"), multiplyData);

		// object to json in string
		String multDataJsonString = mapper.writeValueAsString(multiplyData);
		System.out.println("Request::: "+multDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multDataJsonString, headerMap);

		//Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

		
	}

	@Test
	public void multiplytest4_BothNegativeNumbers_200Ok() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("-10", "-776");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\MultiplicationData.json"), multiplyData);

		// object to json in string
		String multDataJsonString = mapper.writeValueAsString(multiplyData);
		System.out.println("Request::: "+multDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (PRODUCT)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// Json to java object:
		ResponseData multDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(multDataResObj);
		
		//Assertions
		String expectedProduct = "7760";
		Assert.assertTrue(expectedProduct.equals(multDataResObj.getProduct()));
		
		
		System.out.println("Actual Product is:: " +multDataResObj.getProduct());
	}
	
	@Test
	public void multiplytest5_OneNegativeInteger_200Ok() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("10", "-776");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\MultiplicationData.json"), multiplyData);

		// object to json in string
		String multDataJsonString = mapper.writeValueAsString(multiplyData);
		System.out.println("Request::: "+multDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (PRODUCT)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// Json to java object:
		ResponseData multDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(multDataResObj);
		
		//Assertions
		String expectedProduct = "-7760";
		Assert.assertTrue(expectedProduct.equals(multDataResObj.getProduct()));
		
		
		System.out.println("Actual Product is:: " +multDataResObj.getProduct());
	}
	

	

}

package com.automation.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.automation.data.MaxMinData;
import com.automation.data.ResponseData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MaxMinServicesTest extends TestBase {
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
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\maxData.json"), maxData);

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

	@Test
	public void maxTest2_SpecialCharactersInSet_400BadRequest()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> numbers = new ArrayList<String>();
		numbers.add("90");
		numbers.add("&*");
		numbers.add("78");
		numbers.add("1");
		MaxMinData maxData = new MaxMinData(numbers);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\maxData.json"), maxData);

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

	@Test
	public void maxTest3_NegativeNumbersInSet_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> numbers = new ArrayList<String>();
		numbers.add("-90");
		numbers.add("1");
		numbers.add("-78");
		numbers.add("0");
		MaxMinData maxData = new MaxMinData(numbers);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\maxData.json"), maxData);

		// object to json in string
		String maxDataJsonString = mapper.writeValueAsString(maxData);
		System.out.println("This is maxDataJsonString:" + maxDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_max, maxDataJsonString, headerMap);

		// System.out.println(restClient.post(url_addition, maxDataJsonString,
		// headerMap));

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData maxDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(maxDataResObj);

		String expectedMax = "1";
		Assert.assertTrue(expectedMax.equals(maxDataResObj.getMax()));

		System.out.println("Actual Max is: " + maxDataResObj.getMax());

	}
	
	@Test
	public void maxTest4_AllNegativeNumbersInSet_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> numbers = new ArrayList<String>();
		numbers.add("-91829");
		numbers.add("-787");
		numbers.add("-786");
		numbers.add("-2178");
		MaxMinData maxData = new MaxMinData(numbers);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\maxData.json"), maxData);

		// object to json in string
		String maxDataJsonString = mapper.writeValueAsString(maxData);
		System.out.println("This is maxDataJsonString:" + maxDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_max, maxDataJsonString, headerMap);

		// System.out.println(restClient.post(url_addition, maxDataJsonString,
		// headerMap));

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData maxDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(maxDataResObj);

		String expectedMax = "-786";
		Assert.assertTrue(expectedMax.equals(maxDataResObj.getMax()));

		System.out.println("Actual Max is: " + maxDataResObj.getMax());

	}
	
	@Test
	public void maxTest5_OneNullInSet_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> numbers = new ArrayList<String>();
		numbers.add("");
		numbers.add("637");
		numbers.add("786");
		numbers.add("1000");
		MaxMinData maxData = new MaxMinData(numbers);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\maxData.json"), maxData);

		// object to json in string
		String maxDataJsonString = mapper.writeValueAsString(maxData);
		System.out.println("This is maxDataJsonString:" + maxDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_max, maxDataJsonString, headerMap);

		// System.out.println(restClient.post(url_addition, maxDataJsonString,
		// headerMap));

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData maxDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(maxDataResObj);

		String expectedMax = "1000";
		Assert.assertTrue(expectedMax.equals(maxDataResObj.getMax()));

		System.out.println("Actual Max is: " + maxDataResObj.getMax());

	}
	//*******************************************************************
	//MIN Service endpoint testing
	//*******************************************************************
	
	
	@Test
	public void minTest1_NonIntegersInSet_400_BadRequest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("uiu");
		numbers.add("78");
		numbers.add("2398");
		numbers.add("99");
		MaxMinData minData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\minData.json"), minData);

		// object to json in string
		String minDataJsonString = mapper.writeValueAsString(minData);
		System.out.println("This is maxDataJsonString:" +minDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_min, minDataJsonString, headerMap);
		
		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

		

	}
	
	
	@Test
	public void minTest2_SpecialCharactersInSet_400_BadRequest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("&*");
		numbers.add("78");
		numbers.add("^%#^");
		numbers.add("99");
		MaxMinData minData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\minData.json"), minData);

		// object to json in string
		String minDataJsonString = mapper.writeValueAsString(minData);
		System.out.println("This is maxDataJsonString:" +minDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_min, minDataJsonString, headerMap);
		
		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

		

	}
	
	@Test
	public void minTest3_NegativeNumbersInSet_200_Ok() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("-9");
		numbers.add("90");
		numbers.add("786");
		numbers.add("1");
		MaxMinData minData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\minData.json"), minData);

		// object to json in string
		String minDataJsonString = mapper.writeValueAsString(minData);
		System.out.println("This is maxDataJsonString:" +minDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_min, minDataJsonString, headerMap);
		
		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
				String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("The response from API is:" + responseJson);

				// json to java object:
				ResponseData minDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
				System.out.println(minDataResObj);

				
				String expectedMin = "-9";
				Assert.assertTrue(expectedMin.equals(minDataResObj.getMin()));

				System.out.println("Actual Min is: " + minDataResObj.getMin());

	}
	
	@Test
	public void minTest4_AllNegativeNumbersInSet_200_Ok() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("-988");
		numbers.add("-889");
		numbers.add("-2000");
		numbers.add("-12");
		MaxMinData minData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\minData.json"), minData);

		// object to json in string
		String minDataJsonString = mapper.writeValueAsString(minData);
		System.out.println("This is maxDataJsonString:" +minDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_min, minDataJsonString, headerMap);
		
		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
				String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("The response from API is:" + responseJson);

				// json to java object:
				ResponseData minDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
				System.out.println(minDataResObj);

				
				String expectedMin = "-2000";
				Assert.assertTrue(expectedMin.equals(minDataResObj.getMin()));

				System.out.println("Actual Min is: " + minDataResObj.getMin());

	}
	

	@Test
	public void minTest5_OneNullInSet_200_Ok() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("");
		numbers.add("-889");
		numbers.add("-2");
		numbers.add("-12");
		MaxMinData minData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\minData.json"), minData);

		// object to json in string
		String minDataJsonString = mapper.writeValueAsString(minData);
		System.out.println("This is maxDataJsonString:" +minDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_min, minDataJsonString, headerMap);
		
		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
				String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("The response from API is:" + responseJson);

				// json to java object:
				ResponseData minDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
				System.out.println(minDataResObj);

				
				String expectedMin = "-889";
				Assert.assertTrue(expectedMin.equals(minDataResObj.getMin()));

				System.out.println("Actual Min is: " + minDataResObj.getMin());

	}
}

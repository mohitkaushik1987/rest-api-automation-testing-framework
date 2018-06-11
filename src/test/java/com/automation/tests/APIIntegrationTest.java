package com.automation.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.automation.data.DivisionData;
import com.automation.data.MaxMinData;
import com.automation.data.MultiplyData;
import com.automation.data.ResponseData;
import com.automation.data.UnionIntersect;
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
		AddData addData = new AddData("776","10");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println("Request::: " +addDataJsonString);

    	closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData resDataObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		
		System.out.println(resDataObj);

		String expectedSum = "786";
		//Assert.assertTrue(expectedSum.equals(resDataObj.getSum()));
		

		System.out.println("Actual Sum is:: " +resDataObj.getSum());
	}

	// Multiplication Test
	@Test
	public void multiplytest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("10", "776");

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

	// Division Test
	@Test
	public void divisionTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		DivisionData divData = new DivisionData("776", "10");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\DivisionData.json"), divData);

		// object to json in string
		String divDataJsonString = mapper.writeValueAsString(divData);
		System.out.println(divDataJsonString);
		
		//Making REST call
		closebaleHttpResponse = restClient.post(url_division, divDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (QUOTIENT)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData divDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(divDataResObj);

		String expectedQuotient = "77";
		Assert.assertTrue(expectedQuotient.equals(divDataResObj.getQuotient()));

		System.out.println("Actual Quotient is: " + divDataResObj.getQuotient());
		// System.out.println(addDataResObj.getCreatedAt());

	}
	
	
	@Test
	public void maxTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("2");
		numbers.add("34");
		numbers.add("78");
		numbers.add("1");
		MaxMinData maxData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\maxData.json"), maxData);

		// object to json in string
		String maxDataJsonString = mapper.writeValueAsString(maxData);
		System.out.println("This is maxDataJsonString:" +maxDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_max, maxDataJsonString, headerMap);
		
		//System.out.println(restClient.post(url_addition, maxDataJsonString, headerMap));
		
		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData maxDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(maxDataResObj);

		
		String expectedMax = "78";
		Assert.assertTrue(expectedMax.equals(maxDataResObj.getMax()));

		System.out.println("Actual Max is: " + maxDataResObj.getMax());

	}
	
	@Test
	public void minTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> numbers = new ArrayList<String>();
		numbers.add("2");
		numbers.add("34");
		numbers.add("78");
		numbers.add("1");
		MaxMinData minData = new MaxMinData(numbers) ;

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\minData.json"), minData);

		// object to json in string
		String minDataJsonString = mapper.writeValueAsString(minData);
		System.out.println("This is maxDataJsonString:" +minDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_min, minDataJsonString, headerMap);
		
		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData minDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(minDataResObj);

		
		String expectedMin = "1";
		Assert.assertTrue(expectedMin.equals(minDataResObj.getMin()));

		System.out.println("Actual Min is: " + minDataResObj.getMin());

	}
	
	@Test
	public void unionTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("2");
		left.add("34");
		left.add("78");
		left.add("1");
		
		right.add("21");
		right.add("3");
		right.add("7");
		right.add("1");

		UnionIntersect unionData = new UnionIntersect(left, right) ;
		
		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" +unionDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);
		
		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData unionDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(unionDataResObj);

		
		Set <String> expectedUnion = new HashSet<String>();
		expectedUnion.add("1");
		expectedUnion.add("2");
		expectedUnion.add("3");
		expectedUnion.add("7");
		expectedUnion.add("21");
		expectedUnion.add("34");
		expectedUnion.add("78");
		
		//Assertions
		Assert.assertEquals(unionDataResObj.getUnion(),expectedUnion);
	

		System.out.println("Actual Union is: " + unionDataResObj.getUnion());

	}
	
	
	@Test
	public void intersectionTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("2");
		left.add("34");
		left.add("7");
		left.add("1");
		
		right.add("21");
		right.add("3");
		right.add("7");
		right.add("1");

		UnionIntersect intersectionData = new UnionIntersect(left, right) ;
		
		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\intersectionData.json"), intersectionData);

		// object to json in string
		String intersectionDataJsonString = mapper.writeValueAsString(intersectionData);
		System.out.println("This is maxDataJsonString:" +intersectionDataJsonString);
		
		//Making the REST CALL
		closebaleHttpResponse = restClient.post(url_intersect, intersectionDataJsonString, headerMap);
		
		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData intersectDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(intersectDataResObj);

		
		Set <String> expectedIntersection = new HashSet<String>();
		expectedIntersection.add("1");
		expectedIntersection.add("7");
		
		//Assertions
		Assert.assertEquals(intersectDataResObj.getIntersection(),expectedIntersection);
	

		System.out.println("Actual Intersect is: " + intersectDataResObj.getIntersection());

	}

}

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
import com.automation.data.ResponseData;
import com.automation.data.UnionIntersect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnionIntersectServiceTest extends TestBase {
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
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}

	@Test
	public void unionTest2_NonIntegersInRightSet_400BadRequest()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("1");
		left.add("2");
		left.add("3");
		left.add("4");

		right.add("cd");
		right.add("4");
		right.add("5");
		right.add("6");

		UnionIntersect unionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}

	@Test
	public void unionTest3_NegativeNumbersInLeftSet_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("-1");
		left.add("-2");
		left.add("3");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("5");
		right.add("6");

		UnionIntersect unionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData unionDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(unionDataResObj);

		Set<String> expectedUnion = new HashSet<String>();
		expectedUnion.add("-1");
		expectedUnion.add("-2");
		expectedUnion.add("3");
		expectedUnion.add("4");
		expectedUnion.add("5");
		expectedUnion.add("6");

		// Assertions
		Assert.assertEquals(unionDataResObj.getUnion(), expectedUnion);

		System.out.println("Actual Union is: " + unionDataResObj.getUnion());

	}

	@Test
	public void unionTest4_NegativeNumbersInBothSets_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("-1");
		left.add("-2");
		left.add("3");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("-5");
		right.add("-6");

		UnionIntersect unionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData unionDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(unionDataResObj);

		Set<String> expectedUnion = new HashSet<String>();
		expectedUnion.add("-1");
		expectedUnion.add("-2");
		expectedUnion.add("3");
		expectedUnion.add("4");
		expectedUnion.add("-5");
		expectedUnion.add("-6");

		// Assertions
		Assert.assertEquals(unionDataResObj.getUnion(), expectedUnion);

		System.out.println("Actual Union is: " + unionDataResObj.getUnion());

	}

	@Test
	public void unionTest5_NullsInLeftSet_200Ok() throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("");
		left.add("");
		left.add("3");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("-5");
		right.add("-6");

		UnionIntersect unionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData unionDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(unionDataResObj);

		Set<String> expectedUnion = new HashSet<String>();
		expectedUnion.add(null);
		expectedUnion.add("3");
		expectedUnion.add("4");
		expectedUnion.add("-5");
		expectedUnion.add("-6");

		// Assertions
		Assert.assertEquals(unionDataResObj.getUnion(), expectedUnion);

		System.out.println("Actual Union is: " + unionDataResObj.getUnion());

	}

	@Test
	public void unionTest6_NullsInBothSets_200Ok() throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("");
		left.add("");
		left.add("3");
		left.add("4");

		right.add("");
		right.add("");
		right.add("-5");
		right.add("-6");

		UnionIntersect unionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\unionData.json"), unionData);

		// object to json in string
		String unionDataJsonString = mapper.writeValueAsString(unionData);
		System.out.println("This is maxDataJsonString:" + unionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_union, unionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// Check response (MIN)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData unionDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(unionDataResObj);

		Set<String> expectedUnion = new HashSet<String>();
		expectedUnion.add(null);
		expectedUnion.add("3");
		expectedUnion.add("4");
		expectedUnion.add("-5");
		expectedUnion.add("-6");

		// Assertions
		Assert.assertEquals(unionDataResObj.getUnion(), expectedUnion);

		System.out.println("Actual Union is: " + unionDataResObj.getUnion());

	}

	// ***************************************
	// Intersection Endpoint Testing
	// ***************************************

	@Test
	public void intersectionTest1_NonIntegersInLeftSet_400BadRequest()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("xy");
		left.add("2");
		left.add("3");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("5");
		right.add("6");

		UnionIntersect intersectionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\intersectionData.json"), intersectionData);

		// object to json in string
		String intersectionDataJsonString = mapper.writeValueAsString(intersectionData);
		System.out.println("This is maxDataJsonString:" + intersectionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_intersect, intersectionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}
	
	@Test
	public void intersectionTest2_NonIntegersBothSets_400BadRequest()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("xy");
		left.add("2");
		left.add("3");
		left.add("4");

		right.add("ab");
		right.add("4");
		right.add("5");
		right.add("6");

		UnionIntersect intersectionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\intersectionData.json"), intersectionData);

		// object to json in string
		String intersectionDataJsonString = mapper.writeValueAsString(intersectionData);
		System.out.println("This is maxDataJsonString:" + intersectionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_intersect, intersectionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_400);

	}
	
	@Test
	public void intersectionTest3_NegativeNumbersInBothSets_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("-1");
		left.add("-2");
		left.add("3");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("-5");
		right.add("-6");

		UnionIntersect intersectionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\intersectionData.json"), intersectionData);

		// object to json in string
		String intersectionDataJsonString = mapper.writeValueAsString(intersectionData);
		System.out.println("This is maxDataJsonString:" + intersectionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_intersect, intersectionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		
		//  Check response (MIN)
				String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("The response from API is:" + responseJson);

				// json to java object:
				ResponseData intersectDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
				System.out.println(intersectDataResObj);

				
				Set <String> expectedIntersection = new HashSet<String>();
				expectedIntersection.add("3");
				expectedIntersection.add("4");
				
				//Assertions
				Assert.assertEquals(intersectDataResObj.getIntersection(),expectedIntersection);
			

				System.out.println("Actual Intersect is: " + intersectDataResObj.getIntersection());

	}
	
	@Test
	public void intersectionTest4_NullsPresentInBothSets_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();

		List<String> left = new ArrayList<String>();
		List<String> right = new ArrayList<String>();
		left.add("");
		left.add("");
		left.add("78");
		left.add("4");

		right.add("3");
		right.add("4");
		right.add("");
		right.add("");

		UnionIntersect intersectionData = new UnionIntersect(left, right);

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\intersectionData.json"), intersectionData);

		// object to json in string
		String intersectionDataJsonString = mapper.writeValueAsString(intersectionData);
		System.out.println("This is maxDataJsonString:" + intersectionDataJsonString);

		// Making the REST CALL
		closebaleHttpResponse = restClient.post(url_intersect, intersectionDataJsonString, headerMap);

		// Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		
		//  Check response (MIN)
				String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("The response from API is:" + responseJson);

				// json to java object:
				ResponseData intersectDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
				System.out.println(intersectDataResObj);

				
				Set <String> expectedIntersection = new HashSet<String>();
				expectedIntersection.add(null);
				expectedIntersection.add("4");
		
				
				//Assertions
				Assert.assertEquals(intersectDataResObj.getIntersection(),expectedIntersection);
			

				System.out.println("Actual Intersect is: " + intersectDataResObj.getIntersection());

	}
}

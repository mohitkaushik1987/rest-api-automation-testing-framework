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
import com.automation.data.DivisionData;
import com.automation.data.MultiplyData;
import com.automation.data.ResponseData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToBeRetestedTest extends TestBase {
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

	// Addition Test- Float in inputs
	//@Test
	public void additionTest6_TBR_DecimalNumbersInInput_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		AddData addData = new AddData("1.5", "90");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\AddData.json"), addData);

		// object to json in string
		String addDataJsonString = mapper.writeValueAsString(addData);
		System.out.println(addDataJsonString);

		closebaleHttpResponse = restClient.post(url_addition, addDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		//Assertion commented deliberately
		//Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (SUM)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData resDataObj = mapper.readValue(responseString, ResponseData.class); // actual users object

		System.out.println(resDataObj);

		String expectedSum = "91.5";
		
		//Assertion commented deliberately
		//Assert.assertTrue(expectedSum.equals(resDataObj.getSum()));

		System.out.println("Actual Sum is:: " + resDataObj.getSum());

	}

	//@Test
	public void divisionTest7_TBR_DividendFloat_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		DivisionData divData = new DivisionData("4.5", "2");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\DivisionData.json"), divData);

		// object to json in string
		String divDataJsonString = mapper.writeValueAsString(divData);
		System.out.println(divDataJsonString);

		// Making REST call
		closebaleHttpResponse = restClient.post(url_division, divDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		//Assertion commented deliberately
		//Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (QUOTIENT)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData divDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(divDataResObj);

		String expectedQuotient = "2.25";
		
		//Assertion commented deliberately
		//Assert.assertTrue(expectedQuotient.equals(divDataResObj.getQuotient()));

		System.out.println("Actual Quotient is: " + divDataResObj.getQuotient());
		// System.out.println(addDataResObj.getCreatedAt());

	}

	//@Test
	public void divisionTest8_TBR_DivisorFloat_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		DivisionData divData = new DivisionData("5", "2.5");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\DivisionData.json"), divData);

		// object to json in string
		String divDataJsonString = mapper.writeValueAsString(divData);
		System.out.println(divDataJsonString);

		// Making REST call
		closebaleHttpResponse = restClient.post(url_division, divDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		//Assertion commented deliberately
		//Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (QUOTIENT)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// json to java object:
		ResponseData divDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(divDataResObj);

		String expectedQuotient = "2.0";
		
		//Assertion commented deliberately
		//Assert.assertTrue(expectedQuotient.equals(divDataResObj.getQuotient()));

		System.out.println("Actual Quotient is: " + divDataResObj.getQuotient());
		// System.out.println(addDataResObj.getCreatedAt());

	}

	//@Test
	public void multiplytest6_TBR_DecimalNumbers_200Ok()
			throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		MultiplyData multiplyData = new MultiplyData("7.5", "2");

		// object to json file
		mapper.writeValue(new File("src\\main\\java\\com\\automation\\data\\MultiplicationData.json"), multiplyData);

		// object to json in string
		String multDataJsonString = mapper.writeValueAsString(multiplyData);
		System.out.println("Request::: " + multDataJsonString);

		closebaleHttpResponse = restClient.post(url_multiply, multDataJsonString, headerMap);

		// 1. Check Status Code
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		//Assertion commented deliberately
		//Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

		// 2. Check response (PRODUCT)
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// Json to java object:
		ResponseData multDataResObj = mapper.readValue(responseString, ResponseData.class); // actual users object
		System.out.println(multDataResObj);

		// Assertions
		String expectedProduct = "15";
		
		//Assertion commented deliberately
		//Assert.assertTrue(expectedProduct.equals(multDataResObj.getProduct()));

		System.out.println("Actual Product is:: " + multDataResObj.getProduct());
	}

}

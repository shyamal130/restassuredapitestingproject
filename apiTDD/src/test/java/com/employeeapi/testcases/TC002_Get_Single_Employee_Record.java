package com.employeeapi.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;



public class TC002_Get_Single_Employee_Record extends TestBase {
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("******* Started TC002_Get_Single_Employee_Record *******");
		// specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		response = httpRequest.request(Method.GET, "/employee/"+empID);

		Thread.sleep(3);

	}
	

	@Test
	void checkResponseBody() {

		logger.info("******* Checking Response Body *******");

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	
	}
	
	@Test
	void checkStatusCode() {

		logger.info("******* Checking Status code *******");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

	}
	
	@Test
	void checkStatusLine() {

		logger.info("******* Checking Status Line *******");

		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);

	}

	@Test
	void checkContentType() {

		logger.info("******* Checking Content type *******");

		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==>" + contentType);
		Assert.assertEquals("application/json;charset=utf-8", contentType);

	}

	@Test
	void checkserverType() {

		logger.info("******* Checking Server type *******");

		String serverType = response.header("Server");
		logger.info("Server Type is ==>" + serverType);
		Assert.assertEquals("nginx/1.16.0", serverType);

	}

	@Test
	void checkContentLength() {
		
		logger.info("******* Checking Content Length *******");
		
		String contentLength  = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
			Assert.assertTrue(Integer.parseInt(contentLength)>100);
	
	}
	
	@AfterClass
	void tearDowm() {
		
		logger.info("******* Finished TC002_Get_Single_Employee_Records *******");
	
	 }
}

package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext ) {
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/myReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest API Testing Report");
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name","Employee Database API");		
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","pavan");
		
	}
		
	public void onTestSuccess(ITestResult result)
		{
			//test=extent.createTest(result.getClass().getName());
			//test.createNode(result.getName());
			
			test=extent.createTest(result.getName());
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
			
		}
		
		
		public void onTestFailure(ITestResult result)
		{

			test=extent.createTest(result.getName());
			test.log(Status.FAIL, "Test Case FAILED IS " + result.getName());
			test.log(Status.FAIL, "Test Case FAILED IS " + result.getThrowable());
			
		}
		
		public void onTestSkipped(ITestResult result)
		{

			test=extent.createTest(result.getName());
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
			
			
		}
		
		public void onFinish(ITestContext testContest)
		{

			extent.flush();
		
			
		}
				
	}
	
	
	



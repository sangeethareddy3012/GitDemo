package Selenium.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Selenium.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//HELPS IN PERFECT PARALLEL EXECUTION AND GET PERFECT RESULTS IN HTML
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//assign one unique thread id to each test-creates map
	}
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	public void onTestFailure(ITestResult result)
	{
		 extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String file = null;
		try {
			file = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(file, result.getMethod().getMethodName());
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
	}
}

package Selenium.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count=0;
	int maxTry=2;

	@Override
	public boolean retry(ITestResult result) {
		//whenever test fails it also comes to this class
		//it asks whether we want to rerun again
		if(count<maxTry)
		{
			count++;
			return true;//method will go ahead and rerun
		}
		return false;//method will stop rerun
	}

}

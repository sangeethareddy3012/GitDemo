package Selenium.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import Selenium.TestComponents.BaseTest;
import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.ProductsPage;
public class ErrorValidationsTest extends BaseTest{
	ExtentReports extent;
	@Test(groups= {"Error Handling"},retryAnalyzer=Selenium.TestComponents.Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException
	{
		
	
      landingPage.loginApplication("sangeethared29@gmail.com", "Maoes@7");
    
      Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	@Test
	public void productErrorValidation() throws InterruptedException, IOException
	{
		
	String productName="ADIDAS ORIGINAL";
    
  
    ProductsPage productsPage=landingPage.loginApplication("sangeethareddy1209@gmail.com", "Mangoes@7");//
	productsPage.getProducts();
	productsPage.addProductToCart(productName);
	CartPage cartPage=productsPage.goToCartPage();
	//though this method is not in products page we can access as it extends from abstract components which actually contains this method
	Boolean match=cartPage.match("ADIDAS ORIGINALSE");
    Assert.assertFalse(match);//validations present in testcase only
   
	
}
}

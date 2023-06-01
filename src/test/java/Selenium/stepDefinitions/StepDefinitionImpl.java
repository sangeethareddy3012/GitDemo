package Selenium.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Selenium.TestComponents.BaseTest;
import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.CheckoutPage;
import Selenium.pageObjects.LandingPage;
import Selenium.pageObjects.OrdersPage;
import Selenium.pageObjects.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
 
	public LandingPage landingPage;
	public ProductsPage productsPage;
	public OrdersPage ordersPage;
	
	
	 @Given("^I landed on Ecommerce Page$")
	    public void i_landed_on_ecommerce_page() throws Throwable {
		 landingPage=launchApp();
	    }

	    @Given("^Logged in with username (.+) and password (.+)$")
	    public void logged_in_with_username_and_password(String name, String password) throws Throwable {
	        

			 productsPage=landingPage.loginApplication(name, password);
	    }

	    @When("^I add the product (.+) to Cart$")
	    public void i_add_the_product_to_cart(String productname) throws Throwable {
	       
	    	productsPage.getProducts();
			productsPage.addProductToCart(productname);
	    }
	    @And("^checkout (.+) and submit the order$")
	    public void checkout_and_submit_the_order(String productname) throws Throwable {
	    	CartPage cartPage=productsPage.goToCartPage();//though this method is not in products page we can access as it extends from abstract components which actually contains this method
			Boolean match=cartPage.match(productname);
		    Assert.assertTrue(match);//validations present in testcase only
		    CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
		    checkoutPage.selectCountry("india");
		    ordersPage=checkoutPage.submitOrder();
	    }

	    @Then("^check \"([^\"]*)\" message is displayed on confirmation page$")
	    public void check_something_message_is_displayed_on_confirmation_page(String msg) throws Throwable {
	    	String text=ordersPage.confirmationMessage();
		    Assert.assertTrue(text.equalsIgnoreCase(msg));

			
	    }
	    
	    @Then("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String msg) throws Throwable {
	    	    
	         Assert.assertEquals(msg, landingPage.getErrorMessage());
	         driver.close();
	    }

	   

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApp();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String pwd)
	{
		 productsPage=landingPage.loginApplication(username, pwd);
	}
	
	@When("^I add the product (.+)to Cart$")
	public void i_add_the_product_to_Cart(String product) throws InterruptedException
	{
		productsPage.getProducts();
		productsPage.addProductToCart(product);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String product)
	{
		CartPage cartPage=productsPage.goToCartPage();//though this method is not in products page we can access as it extends from abstract components which actually contains this method
		Boolean match=cartPage.match(product);
	    Assert.assertTrue(match);//validations present in testcase only
	    CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
	    checkoutPage.selectCountry("india");
	    ordersPage=checkoutPage.submitOrder();
	}
	@Then("check {String} message is displayed on confirmation page")
	public void check_message_is_displayed_on_confirmation_page(String msg)
	{
		String text=ordersPage.confirmationMessage();
	    Assert.assertTrue(text.equalsIgnoreCase(msg));
	}*/
	
}

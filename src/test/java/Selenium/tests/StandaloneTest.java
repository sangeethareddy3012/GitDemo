package Selenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import Selenium.TestComponents.BaseTest;
import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.CheckoutPage;
import Selenium.pageObjects.OrdersPage;
import Selenium.pageObjects.ProductsPage;
import Selenium.pageObjects.ConfirmPage;
public class StandaloneTest extends BaseTest{
	String productName="ADIDAS ORIGINAL";
		@Test(dataProvider="getData",groups= {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
		{
			
		
        
       // LandingPage landingPage=launchApp(); no need to call this method as we are giving before method annotation
       //but still we want landing page object which is in base test class as we are extending that class we can use variables,methods from parent class
        ProductsPage productsPage=landingPage.loginApplication(input.get("email"), input.get("password"));//
		//login applicaton method returns productspage and we store it in variable
		productsPage.getProducts();
		productsPage.addProductToCart(input.get("product"));
		CartPage cartPage=productsPage.goToCartPage();//though this method is not in products page we can access as it extends from abstract components which actually contains this method
		Boolean match=cartPage.match(input.get("product"));
	    Assert.assertTrue(match);//validations present in testcase only
	    CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
	    checkoutPage.selectCountry("india");
	    OrdersPage ordersPage=checkoutPage.submitOrder();
		String text=ordersPage.confirmationMessage();
	    Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
//run this code after above code
	//to verify adidas original is displaying in orders page
		//depending test
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		
		ProductsPage productsPage=landingPage.loginApplication("sangeethareddy1229@gmail.com", "Mangoes@7");
		ConfirmPage confirmPage=productsPage.goToConfirmPage();
		Boolean match=confirmPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		List<HashMap<String, String>> data=getJsonData(System.getProperty("user.dir")+"//src//test//java//Selenium//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	

	
	
	/*HashMap<Object,Object> map=new HashMap<Object,Object>();
	map.put("email", "sangeethareddy1229@gmail.com");
	map.put("password", "Mangoes@7");
	map.put("product", "ADIDAS ORIGINAL");
	HashMap<Object,Object> map1=new HashMap<Object,Object>();
	map1.put("email", "sangeethareddy1209@gmail.com");
	map1.put("password", "Mangoes@7");
	map1.put("product", "IPHONE 13 PRO");*/
}

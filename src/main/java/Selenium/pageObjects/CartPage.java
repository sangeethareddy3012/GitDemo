package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProdutNames;
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	 
	public List<WebElement> getCartProductNames()
	{
		return cartProdutNames;
	}
	
	public Boolean match(String productName)
	{
		Boolean match=getCartProductNames().stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckoutPage()
	{
		checkoutBtn.click();
		//CheckoutPage checkoutPage=new CheckoutPage(driver);
		//return checkoutPage;
		return new CheckoutPage(driver);
	}
	
	/*List<WebElement> cartProductNames=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=cartProductNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	driver.findElement(By.cssSelector(".totalRow button")).click();*/

}

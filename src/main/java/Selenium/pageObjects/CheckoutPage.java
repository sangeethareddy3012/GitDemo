package Selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import Selenium.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	By appear=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(appear);
		selectCountry.click();
		
	}
	public OrdersPage submitOrder()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", placeOrder);
		for(int i=0;i<=2;i++)
		{
			try {
				placeOrder.click();
				break;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		
		//we can write like this also instead of directly creating and returning object
		//OrdersPage ordersPage=new OrdersPage(driver);
		//return ordersPage;
        return new OrdersPage(driver);
	}
	
	
	
}

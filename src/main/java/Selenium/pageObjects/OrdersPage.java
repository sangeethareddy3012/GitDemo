package Selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirmationMessage;
	
	public String confirmationMessage()
	{
		String text=confirmationMessage.getText();
		return text;
	}
	
	
	
	
}

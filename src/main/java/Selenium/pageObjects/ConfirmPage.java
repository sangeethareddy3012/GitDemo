package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class ConfirmPage extends AbstractComponent {

	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderList;
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match=orderList.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
		return match;
	}
}

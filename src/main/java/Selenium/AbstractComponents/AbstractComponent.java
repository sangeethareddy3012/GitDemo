package Selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.ConfirmPage;

public class AbstractComponent {
	
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {//inorder to catch the driver from child class we are using constructor
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void waitForElementToAppear(By visibleWait)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(visibleWait));
		
	}
	public void waitForWebElementToAppear(WebElement visibleWait)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(visibleWait));
		
	}

	
	public void waitForElementToDisappear(WebElement invisibleWait) throws InterruptedException
	{
		Thread.sleep(2000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(invisibleWait));
		
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
		
	}
	public ConfirmPage goToConfirmPage()
	{
		orderHeader.click();
		ConfirmPage confirmPage=new ConfirmPage(driver);
		return confirmPage;
	}
	
	
}

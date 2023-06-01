package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {
	WebDriver driver;
	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	/*
	List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod=products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();*/
	
	
	


	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement roundCircle;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastContainer=By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProducts()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProducts().stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	 public void addProductToCart(String productName) throws InterruptedException
	 {
		WebElement prod= getProductByName(productName);
		 prod.findElement(addToCart).click();
		 waitForElementToAppear(toastContainer);
		 waitForElementToDisappear(roundCircle);
	 }
}

package Selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);//we are sending driver from child class to parent class using constructor instead of getting driver from test class standalone
		this.driver=driver;
		PageFactory.initElements(driver, this);//this method intialise all the elements present in this class
		// and as it takes driver as input it initialises the driver
	}
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//WebElement password=driver.findElement(By.id("userPassword"));
	
	//WebElement login=driver.findElement(By.id("login"));
	//page factory
	@FindBy(id="userEmail")//all driver.findElement will be constructed at run time and will be assigned to variable next to it
	WebElement userEmail;//how @FindBy knows about the driver =by using pagefactory.initelements method
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")	
	WebElement errorMessage;
	public ProductsPage loginApplication(String email,String pwd)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		login.click();
		ProductsPage productsPage=new ProductsPage(driver);
		return productsPage;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
}
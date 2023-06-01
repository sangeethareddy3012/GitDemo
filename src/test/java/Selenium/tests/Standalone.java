package Selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Standalone {

	public static void main(String[] args) {
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("sangeethareddy1229@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mangoes@7");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProductNames=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProductNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//WebElement country=driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		//a.sendKeys(country."India").build().perform();
	    //driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String text=driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}

package Selenium.TestComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initialiseDriver() throws IOException
	{
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Selenium//resources//global.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless"))
			{
			  options.addArguments("headless");//helps in operating in invisible mode
			}
			options.addArguments("--remote-allow-origins=*");
			
			driver=new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900));//we need to run this window in this dimension for full screen mode
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException
	{
		//it reads json file into string
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String to hashmap-USE jackson data bind dependency
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});
		//returns {map,map1}
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApp() throws IOException
	{
		driver=initialiseDriver();
	    landingPage=new LandingPage(driver);//as we are using before method annotation this method executes first 
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
	}
}

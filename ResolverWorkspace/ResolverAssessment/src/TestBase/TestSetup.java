package TestBase;

import java.io.FileReader;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;


public class TestSetup 
{
	public static Properties xPathProp,paramProp;
	public static String browser;
	public static WebDriver driver;
	public static SoftAssert softAssert;
    
	//openBrowser() method will be called before each test case class.
    @BeforeClass
	public static void openBrowser() throws Throwable
	{
		softAssert = new SoftAssert();
		xPathProp = new Properties();
		paramProp = new Properties();
		xPathProp.load(new FileReader("src\\Properties\\PageObjects.properties"));
		paramProp.load(new FileReader("src\\Properties\\Parameters.properties"));
		browser = paramProp.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			// Chrome configuration settings
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		
		//open URL
		driver.get(paramProp.getProperty("url")); 
		
		//maximize browser window
		driver.manage().window().maximize();	
		
	}
    
    //closeBrowser() method will be called after each test case class execution.
    @AfterClass
    public void closeBrowser() throws Throwable 
    {
    	Thread.sleep(1000);
    	
    	//To close all the browser sessions and release the memory.
    	if (driver != null) {
            driver.quit();
        }
    }    
 
}

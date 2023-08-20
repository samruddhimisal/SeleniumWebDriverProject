package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import TestBase.TestSetup;

public class CommonUtils extends TestSetup
{
	//Method to scroll to a particular form in the page
	public static void scrollToAForm(WebElement element) throws Throwable
	{
		//To wait for 1 second after the page is loaded. 
		Thread.sleep(1000);
		
		//A script that navigates to a particular element in the page.
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		
		//Verify that the Form Header for the test is visible.
		verifyHeaderVisibility(element);
		
	}
	
	//Method to verify the Form Header visibility
	public static void verifyHeaderVisibility(WebElement element)
	{					
		softAssert.assertEquals(true, element.isDisplayed(), element.getText()+" form header is not visible.");
	}
		
	//Method to navigate to the home Page
	public static void navigateToHome()
	{
		//Verify that the 'Home' hyperlink is visible and then Navigate to the Home page			
		WebElement homePage = driver.findElement(By.xpath(xPathProp.getProperty("link_homePage")));
		try
		{
			softAssert.assertEquals(true, homePage.isDisplayed());
			Reporter.log("Home Page is visible.");
		}
		catch(Throwable t)
		{
			Reporter.log("Home Page is not visible.");
		}
		homePage.click();
	}	
}

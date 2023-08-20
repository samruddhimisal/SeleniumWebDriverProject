package TestCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Utils.TestUtils;

/* Use Cases to be covered
1. Navigate to Homepage.
2. In the test 3 div, assert that "Option 1" is the default selected value
3. Select "Option 3" from the select list
*/

public class Test3 extends TestUtils
{	
			
	@Test
	public void verifyDefaultLovValue() throws Throwable
	{		
		//Navigate to the home page
		navigateToHome();
				
		//Scroll to the Test3 form
		scrollToAForm(driver.findElement(By.xpath(xPathProp.getProperty("h_test3Header"))));
		
		//Verify the default selected option in dropdown
		verifyDefaultSelectedValue();
	}
	
	@Test
	public void selectLOVvalue()
	{		
		//select "Option 3" from the dropdown
		selectOptionFromLOV(paramProp.getProperty("T3_lovOption3"));
	}
	
}

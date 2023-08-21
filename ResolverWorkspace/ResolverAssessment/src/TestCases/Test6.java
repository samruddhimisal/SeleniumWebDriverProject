package TestCases;

/*
 * Approach-2 :
 * Arrange, act and assert are performed in utilities java file.
 */

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import Utils.TestUtils;

/* Use Cases to be covered
1. Navigate to Homepage.
2. Write a method that allows you to find the value of any cell on the grid
3. Use the method to find the value of the cell at coordinates 2, 2 (staring at 0 in the top left corner)
4. Assert that the value of the cell is "Ventosanzap"
*/

public class Test6 extends TestUtils
{

	@Test
	public void findAndVerifyTableCellValue() throws Throwable
	{
		//Navigate to the home page
		navigateToHome();
				
		//Scroll to the Test5 form
		scrollToAForm(driver.findElement(By.xpath(xPathProp.getProperty("h_test5Header"))));
		
		String actualCellValue = findCellValue(Integer.parseInt(paramProp.getProperty("T6_rowCord")),Integer.parseInt(paramProp.getProperty("T6_colCord")));
		softAssert.assertEquals(actualCellValue, paramProp.getProperty("T6_expectedCellValue"));
	}
}

package TestCases;
/*
 * Approach-2 :
 * Arrange, act and assert are performed in utilities java file.
 */

import org.testng.annotations.Test;
import Utils.TestUtils;

/* 
 * Use Cases to be covered
1. Navigate to the home page
2. In the test 2 div, assert that there are three values in the listgroup
3. Assert that the second list item's value is set to "List Item 2"
4. Assert that the second list item's badge value is 6
*/

public class Test2 extends TestUtils
{			
	@Test
	public void listGroupValidations() throws Throwable
	{		

		//Navigate to the home page
		navigateToHome();
		
		//Verify that there are three values in the listgroup
		verifyListItemsCount();
		
		//Item number in the list for which text has to be verified
		int itemNumber= Integer.parseInt(paramProp.getProperty("T2_inputListItemNum"));
		
		//Verify that the second list item's value is set to "List Item 2"
		verifyListItemText(itemNumber);
		
		//Verify that the second list item's badge value is 6
		verifyListItemBadgeValue(itemNumber);
		

	}	

	
	
}

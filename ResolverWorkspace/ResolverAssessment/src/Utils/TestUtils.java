package Utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Reporter;

/* 
 * 1. This class contain methods used in all the test case classes.
 * 2. Property values used in this class is fetched from properties files. 
 * 	  Values of input, expected output can be changed from there as per the requirement.
 */

public class TestUtils extends CommonUtils
{
	/* Form-1 Methods */
	// Method to verify the visibility of sample login form fields
	public static void verifyLoginFormFields(WebElement emailId,WebElement password,WebElement signInButton)
	{
		// Verify that the email address field is visible and insert the user entered value
		softAssert.assertEquals(true, emailId.isDisplayed(), "Email Id textbox is not displayed.");
		softAssert.assertEquals(true, password.isDisplayed(), "Password textbox is not displayed.");
		softAssert.assertEquals(true, signInButton.isDisplayed(), "Sign in button is not displayed.");
	}

	// Method to verify the login credentials combinations with valid input
	// UseCase: Input - "sam@gmail.com", Output - Should accept input on button
	// click, clear the input fields
	public static void verifyForValidCredentials()
	{
		WebElement emailIdElement, passwordElement;//, signInButtonElement;
		/*
		 * Below code is to check whether the button is clicked by checking if
		 * the credential fields are empty or not. To check whether they are
		 * empty, placeholder text has been used.
		 */
		// Reinitializing elements because element id in the DOM changes after
		// form refresh on button click.
		emailIdElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_emailAddress")));
		passwordElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_password")));

		String actualEmailPlaceholder = emailIdElement.getAttribute("placeholder");
		String actualPasswordPlaceholder = passwordElement.getAttribute("placeholder");
		softAssert.assertEquals(actualEmailPlaceholder, paramProp.getProperty("T1_emailPlaceholder"),
				"Placeholder value not matching. Please check if the email id is valid.");
		softAssert.assertEquals(actualPasswordPlaceholder, paramProp.getProperty("T1_passwordPlaceholder"),
				"Placeholder value not matching. Please check if the password is valid.");
	}

	// Method to verify the login credentials combinations with empty email and/or password input
	/*
	 * UseCase1: Input - Empty/No credentials, Output - message: "Please fill out this field." 
	 * UseCase2: Input - Empty email id value, Output - message: "Please fill out this field." 
	 * UseCase3: Input - Empty password value, Output - message: "Please fill out this field."
	 */
	public static void verifyForEmptyEmailPassword(String[] emailInputsArr, String[] passwordInputsArr, WebElement emailIdElement, WebElement passwordElement, WebElement signInButtonElement)
	{
		String actualValidationMsg;
		/*
		 * Below code fetches email, password data from the parameters.properties file.
		 * It splits the data based on comma(,) and stores them in two different arrays. 
		 * Later, in the for loop, it sends values from both the arrays as combination of email/password and validates actual and expected message.
		 */
		
		for (int i = 0; i < emailInputsArr.length; i++)
		{
			emailIdElement.sendKeys(emailInputsArr[i]);
			passwordElement.sendKeys(passwordInputsArr[i]);
			signInButtonElement.click();
			if (emailInputsArr[i] != null)
			{
				actualValidationMsg = passwordElement.getAttribute("validationMessage");
			} else
			{
				actualValidationMsg = emailIdElement.getAttribute("validationMessage");
			}

			softAssert.assertEquals(actualValidationMsg, paramProp.getProperty("T1_emptyCredentialsMsg"),
					"Validation message not matching.");
			emailIdElement.clear();
			passwordElement.clear();
		}

	}

	// Method to verify the login credentials combinations with invalid email inputs
	/*
	 * UseCase5: Input - "sam", Output - message: "Please include an '@' in the email address. 'sam' is missing an '@'."
	 * UseCase6: Input - "sam@", Output - message: "Please enter a part following '@'. 'sam@' is incomplete." 
	 * UseCase7: Input - "sam@gmail.", Output - message: "'.' is used at a wrong position in 'sam.'."
	 */
	public static void verifyForInvalidEmailId(String[] emailInputsArr, String[] incompleteEmailValidationMsgsArr, WebElement emailIdElement, WebElement passwordElement, WebElement signInButtonElement)
	{
		String actualValidationMsg;
		int len = emailInputsArr.length;
		passwordElement.sendKeys(paramProp.getProperty("T1_validPasswordValue"));
		for (int i = 0; i < len; i++)
		{
			emailIdElement.sendKeys(emailInputsArr[i]);
			signInButtonElement.click();
			actualValidationMsg = emailIdElement.getAttribute("validationMessage");
			softAssert.assertEquals(actualValidationMsg, incompleteEmailValidationMsgsArr[i],
					"Validation message not matching.");
			emailIdElement.clear();
		}
		passwordElement.clear();

	}

	/* Form-2 Methods */
	// Method to verify that there are 3 items in the listgroup
	public void verifyListItemsCount()
	{
		// Preparing a list of elements in form-2
		List<WebElement> list = driver.findElements(By.xpath(xPathProp.getProperty("list_listGroup")));
		int actualListCount = list.size();
		softAssert.assertEquals(actualListCount, Integer.parseInt(paramProp.getProperty("T2_expectedListCount")),
				"Total list count is not matching.");
	}

	// Method to verify the text for list item 2
	public void verifyListItemText(int itemNum)
	{
		// Fetching the xPath for listgroup.
		String listItemPath = xPathProp.getProperty("list_listGroup");
		
		/*
		 * Explanation for the below code :
		 * itemList2Value would have "List Item 2 6". 
		 * itemList2Badge would have "6".
		 * actualListText would have the expected output "List Item 2" after trimming the itemList2Badge value.
		 */
		String itemList2Value = driver.findElement(By.xpath(listItemPath + "[" + itemNum + "]")).getText();
		String itemList2Badge = driver.findElement(By.xpath(listItemPath + "[" + itemNum + "]/span")).getText();
		String actualListText = itemList2Value.replace(itemList2Badge, "").trim();

		softAssert.assertEquals(actualListText, paramProp.getProperty("T2_expectedListText"),
				"The list text doesn't match with expected text value.");
	}

	// Method to verify that the second list item's badge value is 6
	public void verifyListItemBadgeValue(int itemNum)
	{
		String itemBadge = xPathProp.getProperty("list_listGroup");
		int actualBadgeValue = Integer
				.parseInt(driver.findElement(By.xpath(itemBadge + "[" + itemNum + "]/span")).getText());
		softAssert.assertEquals(actualBadgeValue, paramProp.getProperty("T2_expectedBadgeValue"),
				"The list badge doesn't match with expected badge value.");

	}

	/* Form-3 Methods */
	// Method to verify the default value in the drop-down.
	public static void verifyDefaultSelectedValue()
	{
		WebElement btn_LOV = driver.findElement(By.xpath(xPathProp.getProperty("btn_LOV")));

		if (btn_LOV.isDisplayed() && btn_LOV.isEnabled())
		{
			String actualLOVvalue = driver.findElement(By.xpath(xPathProp.getProperty("btn_LOV"))).getText();
			softAssert.assertEquals(actualLOVvalue, paramProp.getProperty("T3_lovOption1"),
					"The default value in LOV doesn't match with expected value.");
		} else
		{
			System.out.println("LOV button is not visible");
		}
	}

	// Method to select a value from the drop-down based on the given input.
	public static void selectOptionFromLOV(String option)
	{
		WebElement btn_LOV = driver.findElement(By.xpath(xPathProp.getProperty("btn_LOV")));
		if (btn_LOV.isDisplayed() && btn_LOV.isEnabled())
		{
			btn_LOV.click();
			// Create a list of the shown values in the drop-down
			List<WebElement> list = driver.findElements(By.xpath(xPathProp.getProperty("link_LOV")));

			// Loop through the list to find "Option 3". If found, break the loop.
			for (WebElement elem : list)
			{
				if (option.equals(elem.getText()))
				{
					elem.click();
					break;
				}
			}
			
			// Fetch the newly selected value
			btn_LOV = driver.findElement(By.xpath(xPathProp.getProperty("btn_LOV")));
			
			// Check if the newly selected value is correct
			softAssert.assertEquals(btn_LOV, option, "Expected value is not selected.");
		}

	}

	/* Form-4 Methods */
	// Method to verify that the primary button is enabled and secondary is disabled.
	public void verifyButtonsStatus()
	{
		WebElement btn = driver.findElement(By.xpath(xPathProp.getProperty("btn_test4PrimaryButton")));
		softAssert.assertEquals(btn.isEnabled(), true);
		btn = driver.findElement(By.xpath(xPathProp.getProperty("btn_test4secondaryButton")));
		softAssert.assertEquals(btn.isEnabled(), false);
	}

	/* Form-5 Methods */
	// Method to click on the delayed button once it is visible and verify the message.
	public void verifyDelayedBtnMsg()
	{
		// Wait for the button to be displayed and then click on it
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement delayedButton = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath(xPathProp.getProperty("btn_test5DelayedButton")))));
		delayedButton.click();

		// Verify the message after button is clicked
		String successMessage = driver.findElement(By.xpath(xPathProp.getProperty(("m_btnConfirmationMsg")))).getText();
		softAssert.assertEquals(successMessage, "You clicked a button!");

		// Verify that the button is disabled after click.
		softAssert.assertEquals(false, delayedButton.isEnabled(), "The button is not disabled.");
	}

	/* Form-6 Methods */
	// Method to find the value of the cell at given coordinates.
	public static String findCellValue(int rowCoordinate, int colCoordinate)
	{
		WebElement table = driver.findElement(By.xpath(xPathProp.getProperty("tb_table")));
		List<WebElement> rowsList = table.findElements(By.xpath(xPathProp.getProperty("tbl_bodyRows")));
		List<WebElement> columnsList = null;
		int rowCount = 0, colCount = 0;

		for (WebElement row : rowsList)
		{
			columnsList = row.findElements(By.tagName("td"));

			for (WebElement column : columnsList)
			{
				if (rowCoordinate == rowCount && colCoordinate == colCount)
				{
					return column.getText();
				}

				colCount++;
			}
			rowCount++;
			colCount = 0;
		}
		return null;
	}

}

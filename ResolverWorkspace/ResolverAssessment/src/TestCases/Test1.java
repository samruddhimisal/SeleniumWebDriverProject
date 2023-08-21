package TestCases;

/*
 * Approach: Arrange, Act, Assert -
 * Arranging - Taking inputs from the properties files(can be changed as per the requirement by the engineer).
 * Act - Perform actions(click, send inputs, etc.)
 * Assert - Verify actual and expected output
 * 
 * Approach-1 :
 * For "Test1", arrange and act are performed in the Test case java file, whereas, assertion is performed in utilities java file.
 * 
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import Utils.TestUtils;

/*Use Cases to be covered
1. Navigate to Home Page
2. Assert that both the email address and password inputs are present as well as the login button
3. Enter in an email address and password combination into the respective fields 
	2.1. empty credentials -- o/p= alert: "Please fill out this field."
	2.2. empty email id -- o/p= alert: "Please fill out this field."
	2.3. empty password  -- o/p= alert: "Please fill out this field."
	2.4. email id validations-- 
		(UseCase_3.1.1) ip = sam o/p= alert: "Please include an '@' in the email address. 'sam' is missing an '@'."
		(UseCase_3.1.2) ip = sam@ o/p= alert: "Please enter a part following '@'. 'sam@' is incomplete."		
		(UseCase_3.1.3) ip = sam@gmail. o/p= alert: "'.' is used at a wrong position in 'sam.'."
		(UseCase_3.1.4) ip = sam@gmail.com o/p= valid input. fields should be cleared and should show placeholder values
*/
		
public class Test1 extends TestUtils
{	
	
	@Test
	public void loginFormVisibility()
	{
		//Navigate to the home page
		navigateToHome();		
		
		WebElement emailId, password, signInButton;
		// Getting xPaths of login form elements.
		emailId = driver.findElement(By.xpath(xPathProp.getProperty("ip_emailAddress")));
		password = driver.findElement(By.xpath(xPathProp.getProperty("ip_password")));
		signInButton = driver.findElement(By.xpath(xPathProp.getProperty("btn_signIn")));
		//Verify that the login form elements are visible
		verifyLoginFormFields(emailId,password,signInButton);
	}
	
	@Test
	public void loginValidations() throws Throwable
	{
		/*
		 * UseCase1: Input - "sam@gmail.com", Output - Should accept input on button click, clear the input fields
		 */
		WebElement emailIdElement, passwordElement, signInButtonElement;
		// Getting xPaths of login form elements.
		emailIdElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_emailAddress")));
		passwordElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_password")));
		signInButtonElement = driver.findElement(By.xpath(xPathProp.getProperty("btn_signIn")));

		// Entering valid email and password.
		emailIdElement.sendKeys(paramProp.getProperty("T1_validEmailId"));
		passwordElement.sendKeys(paramProp.getProperty("T1_validPasswordValue"));
		signInButtonElement.click();
		
		verifyForValidCredentials();
		
		
		/*
		 * UseCase2: Input - Empty email id and password field, Output - message: "Please fill out this field."
		 * UseCase3: Input - Empty email id value, Output - message: "Please fill out this field."
		 * UseCase4: Input - Empty password value, Output - message: "Please fill out this field."
		 */
		String emailInputsString, passwordInputsString;
		String[] emailInputsArr = null, passwordInputsArr = null;

		emailIdElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_emailAddress")));
		passwordElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_password")));
		signInButtonElement = driver.findElement(By.xpath(xPathProp.getProperty("btn_signIn")));

		/*
		 * Below code fetches email, password data from the parameters.properties file.
		 * It splits the data based on comma(,) and stores them in two different arrays. 
		 * Later, in the for loop, it sends values from both the arrays as combination of email/password and validates actual and expected message.
		 */
		emailInputsString = paramProp.getProperty("T1_emptyTestEmails");
		emailInputsArr = emailInputsString.split(",");
		passwordInputsString = paramProp.getProperty("T1_emptyTestPasswords");
		passwordInputsArr = passwordInputsString.split(",");
		
		//Method to validate empty email id and password combinations as per the provided inputs. 
		verifyForEmptyEmailPassword(emailInputsArr,passwordInputsArr,emailIdElement,passwordElement,signInButtonElement);		

		/*
		 * UseCase5: Input - "sam", Output - message: "Please include an '@' in the email address. 'sam' is missing an '@'."
		 * UseCase6: Input - "sam@", Output - message: "Please enter a part following '@'. 'sam@' is incomplete."
		 * UseCase7: Input - "sam@gmail.", Output - message:  "'.' is used at a wrong position in 'sam.'."
		*/
		
		emailIdElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_emailAddress")));
		passwordElement = driver.findElement(By.xpath(xPathProp.getProperty("ip_password")));
		signInButtonElement = driver.findElement(By.xpath(xPathProp.getProperty("btn_signIn")));

		/*
		 * Below code fetches invalid email and its expected validation message data from the parameters.properties file.
		 * It splits the data based on comma(,) and stores them in two different arrays. 
		 * Later, in the for loop, it sends email id from the array with a constant password and validates with expected message on button click.
		 */
		emailInputsString = paramProp.getProperty("T1_incompleteEmailInputs");
		emailInputsArr = emailInputsString.split(",");

		String incompleteEmailValidationMsgsString = paramProp.getProperty("T1_incompleteEmailValidationMsgs");
		String[] incompleteEmailValidationMsgsArr = incompleteEmailValidationMsgsString.split(",");
		
		//Method to validate invalid email id and password combinations as per the provided inputs. 
		verifyForInvalidEmailId(emailInputsArr,incompleteEmailValidationMsgsArr,emailIdElement,passwordElement,signInButtonElement);
		
	}
	
}

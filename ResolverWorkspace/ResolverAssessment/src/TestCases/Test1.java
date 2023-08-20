package TestCases;

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
		verifyLoginForm();
	}
	
	@Test
	public void loginValidations() throws Throwable
	{
		//UseCase1: Input - "sam@gmail.com", Output - Should accept input on button click, clear the input fields
		verifyForValidCredentials();
		
		/*
		 * UseCase2: Input - Empty email id and password field, Output - message: "Please fill out this field."
		 * UseCase3: Input - Empty email id value, Output - message: "Please fill out this field."
		 * UseCase4: Input - Empty password value, Output - message: "Please fill out this field."
		 */
		 verifyForEmptyEmailPassword();		

		/*
		 * UseCase5: Input - "sam", Output - message: "Please include an '@' in the email address. 'sam' is missing an '@'."
		 * UseCase6: Input - "sam@", Output - message: "Please enter a part following '@'. 'sam@' is incomplete."
		 * UseCase7: Input - "sam@gmail.", Output - message:  "'.' is used at a wrong position in 'sam.'."
		*/
		verifyForInvalidEmailId();
		
	}
	
}

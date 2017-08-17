import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import pageObjects.BasePage;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static helpers.WebLibrary.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {

	private static final Logger log = Logger.getLogger(LoginTest.class);
	public WebDriver driver;
	public BasePage basePage;
	public Actions actions;
	
	@BeforeClass
	public static void beforeAll(){
		log.info("Running suite: LoginTest");
			}
	
	@Before
	public void beforeEach() {
		driver = new FirefoxDriver();
		setUpDriver(driver,20);
		basePage = new BasePage(driver);
		goToUrl(driver, "http://www.adriatic.hr/");
		}
	
	@After
	public void afterEach(){
		driver.quit();
		}
	
	
	@Test
	public void loginValidUser(){
		 log.info("Running test - LoginValidUser");
		 log.info("Click SignInButton");
		 clickButton(basePage.SignInButton);
		 
		 log.info("Fill EmailTextfield");
		 setTextIntoWebElement(basePage.EmailTextfield, "olyahit@gmail.com");
		 log.info("Fill PasswordTextfield");
		 setTextIntoWebElement(basePage.PasswordTextfield, "Good458");
		 
		 log.info("unchecked checkbox");
		 setCheckBoxState(basePage.RememberCheckbox,"unchecked");
		 log.info("Click logInButton");
		 clickButton(basePage.logInButton);
		 
		 boolean result = isElementDisplayed(basePage.SignoutButton);
		 log.info("User is log in:" + result);
		 Assert.assertTrue("User is LogIn", result); 
	}
	
	
	@Test
	public void loginInvalidEmail() {
		 log.info("Running test - LoginInvalidEmail");
		 log.info("Click SignInButton");
		 clickButton(basePage.SignInButton);
		
		 log.info("Fill EmailTextfield");
		 setTextIntoWebElement(basePage.EmailTextfield, "tyuutf@gmail.com");
		 log.info("Fill PasswordTextfield");
		 setTextIntoWebElement(basePage.PasswordTextfield, "Good458");
		 
		 log.info("Click logInButton");
		 clickButton(basePage.logInButton);
		 
		 String errorMessage = basePage.ErrorMessageLogin.getText();
		 log.info("Error message is display -" + errorMessage);
		 Assert.assertEquals("Username or E-Mail - The combination of the entered e-mail address and password is invalid", errorMessage );
	}
	
	
	@Test
	public void loginInvalidPassword() {
		 log.info("Running test - LoginInvalidPassword");
		 log.info("Click SignInButton");
		 clickButton(basePage.SignInButton);
		
		 log.info("Fill EmailTextfield");
		 setTextIntoWebElement(basePage.EmailTextfield, "olyahit@gmail.com");
		 log.info("Fill PasswordTextfield");
		 setTextIntoWebElement(basePage.PasswordTextfield, "hhjr09fjh");
		 
		 log.info("Click logInButton");
		 clickButton(basePage.logInButton);
		 
		 String errorMessage = basePage.ErrorMessageLogin.getText();
		 log.info("Error message is display -" + errorMessage);
		 Assert.assertEquals("Username or E-Mail - The combination of the entered e-mail address and password is invalid", errorMessage );
	}
	
	
	@Test
	public void signOut(){
		 log.info("Running test - signOut");
		 log.info("Click SignInButton");
		 clickButton(basePage.SignInButton);
		 log.info("Fill EmailTextfield");
		 setTextIntoWebElement(basePage.EmailTextfield, "olyahit@gmail.com");
		 
		 log.info("Fill PasswordTextfield");
		 setTextIntoWebElement(basePage.PasswordTextfield, "Good458");
		 log.info("unchecked checkbox");
		 setCheckBoxState(basePage.RememberCheckbox,"unchecked");
		 
		 log.info("Click logInButton");
		 clickButton(basePage.logInButton);
		 log.info("Click SignoutButton");
		 clickButton(basePage.SignoutButton);
		 
		 boolean result = isElementDisplayed(basePage.SignInButton);
		 log.info("User is logout:" + result);
		 Assert.assertTrue("User is LogIn", result); 
	}
	
	
	@Test
	public void resetPasswordWithValidEmail(){
		log.info("Running test - resetPasswordWithValidEmail");
		log.info("Click SignInButton");
		clickButton(basePage.SignInButton);
		log.info("Click ForgotYourPassword");
		clickLink(basePage.ForgotYourPassword);
		
		log.info("Fill Email");
		setTextIntoWebElement(basePage.EmailFieldForResetPassword, "olyahit@gmail.com");
		log.info("Click SendPasswordButton");
		clickButton(basePage.SendPasswordButton);
		
		String message = basePage.ValidationMessageAfterClickSendPassword.getText();
		log.info("ValidationMessage is display:-"+ message);
		Assert.assertEquals("Enter the password we sent to your e-mail address.", message);
		
	}
}

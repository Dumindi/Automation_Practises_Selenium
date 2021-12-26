package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.LoginPage;
import Pages.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserRegistrationTests {
	
	WebDriver driver;
	RegistrationPage objRegistrationPage;
	LoginPage objLoginPage;
	SoftAssert sa;
	
	@BeforeTest
	public void setup() {
		ChromeOptions option = new ChromeOptions();
		option.setHeadless(false);		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		
		sa = new SoftAssert();
	}
	
	@Test
	public void test_successfulUserResgistration() {
		
		//In this test case,
		//First load the signup page
		// Fill all the given fields
		// Click on Register button and register the user
		// Check whether user is directed to the Login page 
		
		objRegistrationPage = new RegistrationPage(driver);
		objLoginPage = new LoginPage(driver);
				
		driver.get("http://demo.guru99.com/insurance/v1/");		
		objRegistrationPage.btnRegister_click();		
		
		sa.assertTrue(objRegistrationPage.getCurrentURL_RegisterPage().contains("v1/register"));
		sa.assertTrue(objRegistrationPage.getTitle_RegisterPage().contains("Sign up as a new user"));
		
		objRegistrationPage.select_userTitle(16);
		objRegistrationPage.enter_Firstname("Frankie");
		objRegistrationPage.enter_Surname("Michella");
		objRegistrationPage.enter_phone("+61456666666");
		objRegistrationPage.select_DOBYear("1993");
		objRegistrationPage.select_DOBMonth(4);
		objRegistrationPage.select_DOBday("10");
		objRegistrationPage.select_Provisional();
		objRegistrationPage.select_licesnsePeriod("20");
		objRegistrationPage.select_occupation("Architect");
		objRegistrationPage.enterAddr_Street("08 Sherbrook Road");
		objRegistrationPage.enterAddr_City("Paradise");
		objRegistrationPage.enterAddr_Country("Switzerland");
		objRegistrationPage.enterAddr_Postcode("SWP3 048");
		objRegistrationPage.enter_Email("frankie.m@hotmail.com");
		objRegistrationPage.enter_Password("1qaz@WSX");
		objRegistrationPage.enter_ConfirmationPassword("1qaz@WSX");
		objRegistrationPage.click_btnCreate();
				
		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains("insurance/v1/"));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains("Login"));
	}
	
	@AfterTest
	public void teardown(){
		sa.assertAll();
		driver.close();
		driver.quit();
	}
		
		
	
		
	

}

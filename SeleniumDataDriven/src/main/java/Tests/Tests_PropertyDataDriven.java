package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import Utility.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Tests_PropertyDataDriven {


	WebDriver driver;
	RegistrationPage objRegistrationPage;
	LoginPage objLoginPage;
	HomePage objHomePage;
	SoftAssert sa;
	
	@BeforeTest
	public void setup(){
		ChromeOptions option = new ChromeOptions();
		option.setHeadless(false);		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		
		sa = new SoftAssert();
		
	}
	
	@Test(priority=1)
	public void test_successfulUserResgistration() throws InvalidFormatException, IOException, FileNotFoundException, ParseException {
		
		//In this test case,
		//First load the signup page
		// Fill all the given fields
		// Click on Register button and register the user
		// Check whether user is directed to the Login page 
		
		objRegistrationPage = new RegistrationPage(driver);
		objLoginPage = new LoginPage(driver);
				
		driver.get(PropertyFileReader.readPropertyFile("URL", "./Data/propertydata.properties" ));
		objRegistrationPage.btnRegister_click();

		sa.assertTrue(objRegistrationPage.getCurrentURL_RegisterPage().contains(PropertyFileReader.readPropertyFile("URL_RegistrationPage", "./Data/propertydata.properties")));
		sa.assertTrue(objRegistrationPage.getTitle_RegisterPage().contains(PropertyFileReader.readPropertyFile("PageTitle_RegisterPage", "./Data/propertydata.properties")));

		objRegistrationPage.select_userTitle(PropertyFileReader.readPropertyFile("Title", "./Data/propertydata.properties"));
		objRegistrationPage.enter_Firstname(PropertyFileReader.readPropertyFile("FirstName", "./Data/propertydata.properties"));
		objRegistrationPage.enter_Surname(PropertyFileReader.readPropertyFile("Surname", "./Data/propertydata.properties"));
		objRegistrationPage.enter_phone(PropertyFileReader.readPropertyFile("Phone", "./Data/propertydata.properties"));
		objRegistrationPage.select_DOBYear(PropertyFileReader.readPropertyFile("DOB_Year", "./Data/propertydata.properties"));
		objRegistrationPage.select_DOBMonth(PropertyFileReader.readPropertyFile("DOB_Month", "./Data/propertydata.properties"));
		objRegistrationPage.select_DOBday(PropertyFileReader.readPropertyFile("DOB_Day", "./Data/propertydata.properties"));
		objRegistrationPage.select_Provisional();
		objRegistrationPage.select_licesnsePeriod(PropertyFileReader.readPropertyFile("licesnsePeriod", "./Data/propertydata.properties"));
		objRegistrationPage.select_occupation(PropertyFileReader.readPropertyFile("Occupation", "./Data/propertydata.properties"));
		objRegistrationPage.enterAddr_Street(PropertyFileReader.readPropertyFile("Street", "./Data/propertydata.properties"));
		objRegistrationPage.enterAddr_City(PropertyFileReader.readPropertyFile("City", "./Data/propertydata.properties"));
		objRegistrationPage.enterAddr_Country(PropertyFileReader.readPropertyFile("Country", "./Data/propertydata.properties"));
		objRegistrationPage.enterAddr_Postcode(PropertyFileReader.readPropertyFile("Postcode", "./Data/propertydata.properties"));
		objRegistrationPage.enter_Email(PropertyFileReader.readPropertyFile("Email", "./Data/propertydata.properties"));
		objRegistrationPage.enter_Password(PropertyFileReader.readPropertyFile("Password", "./Data/propertydata.properties"));
		objRegistrationPage.enter_ConfirmationPassword(PropertyFileReader.readPropertyFile("Confirm_Password", "./Data/propertydata.properties"));
		objRegistrationPage.click_btnCreate();

		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains(PropertyFileReader.readPropertyFile("CheckURL_LoginPage", "./Data/propertydata.properties")));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(PropertyFileReader.readPropertyFile("PageTitle_LoginPage", "./Data/propertydata.properties")));
	}
	
	
	@Test(priority =2)
	public void test_successfulLogin() throws InvalidFormatException, IOException, FileNotFoundException, ParseException {
		//For this test case,
		// First load the URL having the user login. 
		// Then check the Login Title is visible in the page
		// Enter username and password and login to the system
		// Check the navigated URL is correct
		// Check the Home page displayed username is correct to which the user used to login
		// Click on Logout button to renavigate to the login page
		// Check the user correctly navigated to the Login page again
		
		objLoginPage = new LoginPage(driver);
		objHomePage = new HomePage(driver);

		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(PropertyFileReader.readPropertyFile("PageTitle_LoginPage", "./Data/propertydata.properties")));

		objLoginPage.login_guru99InsurancePortal (PropertyFileReader.readPropertyFile("Email", "./Data/propertydata.properties"), PropertyFileReader.readPropertyFile("Password", "./Data/propertydata.properties") );

		sa.assertTrue(objHomePage.getCurrentURL_HP().contains(PropertyFileReader.readPropertyFile("CheckURL_HomePage", "./Data/propertydata.properties")));
		sa.assertTrue(objHomePage.getUsernameAtHomePage().contains(PropertyFileReader.readPropertyFile("Email", "./Data/propertydata.properties")));

		objHomePage.click_logoutBtn();

		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains(PropertyFileReader.readPropertyFile("CheckURL_LoginPage", "./Data/propertydata.properties")));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(PropertyFileReader.readPropertyFile("PageTitle_LoginPage", "./Data/propertydata.properties")));
	}
	
	
	@AfterTest
	public void teardown(){
		sa.assertAll();
		driver.close();
		driver.quit();
	}
	
	
}

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
import Utility.JSONReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Tests_JSONDataDriven {


	WebDriver driver;
	RegistrationPage objRegistrationPage;
	LoginPage objLoginPage;
	HomePage objHomePage;
	SoftAssert sa;
	
	@BeforeTest
	public void setup() throws InvalidFormatException, IOException, FileNotFoundException{
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
				
		driver.get(JSONReader.ReadJSONFile("URL", ".\\Data\\JSONData.json"));		
		objRegistrationPage.btnRegister_click();		
		
		sa.assertTrue(objRegistrationPage.getCurrentURL_RegisterPage().contains(JSONReader.ReadJSONFile("URL_RegistrationPage", ".\\Data\\JSONData.json")));
		sa.assertTrue(objRegistrationPage.getTitle_RegisterPage().contains(JSONReader.ReadJSONFile("PageTitle_RegisterPage", ".\\Data\\JSONData.json")));
		
		objRegistrationPage.select_userTitle(JSONReader.ReadJSONFile("Title", ".\\Data\\JSONData.json"));
		objRegistrationPage.enter_Firstname(JSONReader.ReadJSONFile("FirstName", ".\\Data\\JSONData.json"));
		objRegistrationPage.enter_Surname(JSONReader.ReadJSONFile("Surname", ".\\Data\\JSONData.json"));
		objRegistrationPage.enter_phone(JSONReader.ReadJSONFile("Phone", ".\\Data\\JSONData.json"));
		objRegistrationPage.select_DOBYear(JSONReader.ReadJSONFile("DOB_Year", ".\\Data\\JSONData.json"));
		objRegistrationPage.select_DOBMonth(JSONReader.ReadJSONFile("DOB_Month", ".\\Data\\JSONData.json"));
		objRegistrationPage.select_DOBday(JSONReader.ReadJSONFile("DOB_Day", ".\\Data\\JSONData.json"));
		objRegistrationPage.select_Provisional();
		objRegistrationPage.select_licesnsePeriod(JSONReader.ReadJSONFile("licesnsePeriod", ".\\Data\\JSONData.json"));
		objRegistrationPage.select_occupation(JSONReader.ReadJSONFile("Occupation", ".\\Data\\JSONData.json"));
		objRegistrationPage.enterAddr_Street(JSONReader.ReadJSONFile("Street", ".\\Data\\JSONData.json"));
		objRegistrationPage.enterAddr_City(JSONReader.ReadJSONFile("City", ".\\Data\\JSONData.json"));
		objRegistrationPage.enterAddr_Country(JSONReader.ReadJSONFile("Country", ".\\Data\\JSONData.json"));
		objRegistrationPage.enterAddr_Postcode(JSONReader.ReadJSONFile("Postcode", ".\\Data\\JSONData.json"));
		objRegistrationPage.enter_Email(JSONReader.ReadJSONFile("Email", ".\\Data\\JSONData.json"));
		objRegistrationPage.enter_Password(JSONReader.ReadJSONFile("Password", ".\\Data\\JSONData.json"));
		objRegistrationPage.enter_ConfirmationPassword(JSONReader.ReadJSONFile("Confirm_Password", ".\\Data\\JSONData.json"));
		objRegistrationPage.click_btnCreate();
				
		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains(JSONReader.ReadJSONFile("CheckURL_LoginPage", ".\\Data\\JSONData.json")));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(JSONReader.ReadJSONFile("PageTitle_LoginPage", ".\\Data\\JSONData.json")));
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
	
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(JSONReader.ReadJSONFile("PageTitle_LoginPage", ".\\Data\\JSONData.json")));
		
		objLoginPage.login_guru99InsurancePortal (JSONReader.ReadJSONFile("Email", ".\\Data\\JSONData.json"), JSONReader.ReadJSONFile("Password", ".\\Data\\JSONData.json") );		
		
		sa.assertTrue(objHomePage.getCurrentURL_HP().contains(JSONReader.ReadJSONFile("CheckURL_HomePage", ".\\Data\\JSONData.json")));
		sa.assertTrue(objHomePage.getUsernameAtHomePage().contains(JSONReader.ReadJSONFile("Email", ".\\Data\\JSONData.json")));	
		
		objHomePage.click_logoutBtn();	
		
		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains(JSONReader.ReadJSONFile("CheckURL_LoginPage", ".\\Data\\JSONData.json")));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(JSONReader.ReadJSONFile("PageTitle_LoginPage", ".\\Data\\JSONData.json")));				
	}
	
	
	@AfterTest
	public void teardown(){
		sa.assertAll();
		driver.close();
		driver.quit();
	}
	
	
}

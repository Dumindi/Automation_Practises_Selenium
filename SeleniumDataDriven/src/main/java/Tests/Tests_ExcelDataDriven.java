package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import Utility.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Tests_ExcelDataDriven{

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
	public void test_successfulUserResgistration() throws InvalidFormatException, IOException, FileNotFoundException {
		
		//In this test case,
		//First load the signup page
		// Fill all the given fields
		// Click on Register button and register the user
		// Check whether user is directed to the Login page 
		
		objRegistrationPage = new RegistrationPage(driver);
		objLoginPage = new LoginPage(driver);
				
		driver.get(ReadExcel.readExcel(1, 0, ".\\Data\\DataSheet.xlsx", "Sheet2"));		
		objRegistrationPage.btnRegister_click();		
		
		sa.assertTrue(objRegistrationPage.getCurrentURL_RegisterPage().contains(ReadExcel.readExcel(1, 1, ".\\Data\\DataSheet.xlsx", "Sheet2")));
		sa.assertTrue(objRegistrationPage.getTitle_RegisterPage().contains(ReadExcel.readExcel(1, 4, ".\\Data\\DataSheet.xlsx", "Sheet2")));
		
		objRegistrationPage.select_userTitle(ReadExcel.readExcel(1, 0, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enter_Firstname(ReadExcel.readExcel(1, 1, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enter_Surname(ReadExcel.readExcel(1, 2, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enter_phone(ReadExcel.readExcel(1, 3, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.select_DOBYear(ReadExcel.readExcel(1, 4, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.select_DOBMonth(ReadExcel.readExcel(1, 5, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.select_DOBday(ReadExcel.readExcel(1, 6, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.select_Provisional();
		objRegistrationPage.select_licesnsePeriod(ReadExcel.readExcel(1, 8, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.select_occupation(ReadExcel.readExcel(1, 9, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enterAddr_Street(ReadExcel.readExcel(1, 10, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enterAddr_City(ReadExcel.readExcel(1, 11, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enterAddr_Country(ReadExcel.readExcel(1, 12, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enterAddr_Postcode(ReadExcel.readExcel(1, 13, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enter_Email(ReadExcel.readExcel(1, 14, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enter_Password(ReadExcel.readExcel(1, 15, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.enter_ConfirmationPassword(ReadExcel.readExcel(1, 16, ".\\Data\\DataSheet.xlsx", "Sheet1"));
		objRegistrationPage.click_btnCreate();
				
		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains(ReadExcel.readExcel(1, 2, ".\\Data\\DataSheet.xlsx", "Sheet2")));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(ReadExcel.readExcel(1, 5, ".\\Data\\DataSheet.xlsx", "Sheet2")));
	}
	
	
	@Test(priority =2)
	public void test_successfulLogin() throws InvalidFormatException, IOException, FileNotFoundException {
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
	
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(ReadExcel.readExcel(1, 5, ".\\Data\\DataSheet.xlsx", "Sheet2")));
		
		objLoginPage.login_guru99InsurancePortal (ReadExcel.readExcel(1, 14, ".\\Data\\DataSheet.xlsx", "Sheet1"), ReadExcel.readExcel(1, 15, ".\\Data\\DataSheet.xlsx", "Sheet1") );		
		
		sa.assertTrue(objHomePage.getCurrentURL_HP().contains(ReadExcel.readExcel(1, 3, ".\\Data\\DataSheet.xlsx", "Sheet2")));
		sa.assertTrue(objHomePage.getUsernameAtHomePage().contains(ReadExcel.readExcel(1, 14, ".\\Data\\DataSheet.xlsx", "Sheet1")));	
		
		objHomePage.click_logoutBtn();	
		
		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains(ReadExcel.readExcel(1, 2, ".\\Data\\DataSheet.xlsx", "Sheet2")));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains(ReadExcel.readExcel(1, 5, ".\\Data\\DataSheet.xlsx", "Sheet2")));				
	}
	
	
	@AfterTest
	public void teardown(){
		sa.assertAll();
		driver.close();
		driver.quit();
	}
	
}

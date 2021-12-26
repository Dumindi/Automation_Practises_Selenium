package Tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTests {

	WebDriver driver;
	LoginPage objLoginPage;
	HomePage objHomePage;
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
	
	@Test(priority = 0)
	public void test_successfulLogin() {
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
				
		driver.get("http://demo.guru99.com/insurance/v1/");
		
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains("Login"));
		
		objLoginPage.login_guru99InsurancePortal ("frankie.m@hotmail.com", "1qaz@WSX");		
		
		sa.assertTrue(objHomePage.getCurrentURL_HP().contains("insurance/v1/header"));
		sa.assertTrue(objHomePage.getUsernameAtHomePage().contains("frankie.m@hotmail.com"));	
		
		objHomePage.click_logoutBtn();	
		
		sa.assertTrue(objLoginPage.getCurrentURL_LP().contains("insurance/v1/"));
		sa.assertTrue(objLoginPage.getTitle_LoginPage().contains("Login"));				
	}
	
	@AfterTest
	public void teardown() {
		sa.assertAll();
		driver.close();
		driver.quit();
	}
}

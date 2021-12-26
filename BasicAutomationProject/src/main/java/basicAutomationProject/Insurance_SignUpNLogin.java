package basicAutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Insurance_SignUpNLogin {
	
	WebDriver driver;
	SoftAssert sa;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup()
	{
		ChromeOptions option = new ChromeOptions();
		option.setHeadless(false);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		
		sa = new SoftAssert();
		wait = new WebDriverWait(driver,20);
		
	}
	
	@Test (priority = 1)
	public void user_register() {
		
		//load URL
		driver.get("http://demo.guru99.com/insurance/v1/");
		
		//Check the validity of the loaded URL
		sa.assertTrue(driver.getCurrentUrl().contains("insurance/v1"), "You have successfully navigated to the User Registration page in Guru99 Insurance");
	    
		//Wait until the Register button loads
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Register")));
		
		//Click Register button to Register a User
		driver.findElement(By.linkText("Register")).click(); 
		
		//Wait until the Register page loads
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), \"Sign up as a new user\")]")));
		
		//Check the validity of the Register page
		sa.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Sign up as a new user");
		
		//Select an item from Title dropdown
		Select dropDwn_title = new Select(driver.findElement(By.id("user_title")));
		dropDwn_title.selectByIndex(3);		
		
		//Enter a firstname
		driver.findElement(By.id("user_firstname")).sendKeys("Frankie");

		//Enter a Surname
		driver.findElement(By.id("user_surname")).sendKeys("Michella");
		
		//Enter a Phone number
		driver.findElement(By.id("user_phone")).sendKeys("+61456666666");
		
		//Select an item from DOB Year dropdown
		Select dropDOB_year = new Select(driver.findElement(By.id("user_dateofbirth_1i")));
		dropDOB_year.selectByVisibleText("1993");
		
		//Select an item from DOB Month dropdown
		Select dropDOB_month = new Select(driver.findElement(By.id("user_dateofbirth_2i")));
		dropDOB_month.selectByIndex(4);
		
		//Select an item from DOB Day dropdown
		Select dropDOB_day = new Select(driver.findElement(By.id("user_dateofbirth_3i")));
		dropDOB_day.selectByVisibleText("10");
		
		//Select the radio button Provosional
		WebElement radio_provisional = driver.findElement(By.id("licencetype_f"));
		radio_provisional.click();
	
		//Select an item from License Period dropdown
		Select drop_licencePeriod = new Select(driver.findElement(By.id("user_licenceperiod")));
		drop_licencePeriod.selectByVisibleText("5");
		
		//Select an item from Occupation dropdown
		Select drop_occupation = new Select(driver.findElement(By.id("user_occupation_id")));
		drop_occupation.selectByVisibleText("Writer");
		
        //Enter a Street
		driver.findElement(By.id("user_address_attributes_street")).sendKeys("08 Sherbrook Road");
		
		//Enter a City
		driver.findElement(By.id("user_address_attributes_city")).sendKeys("Paradise");
		
		//Enter a Country
		driver.findElement(By.id("user_address_attributes_county")).sendKeys("Switzerland");
		
		//Enter a Postcode
		driver.findElement(By.id("user_address_attributes_postcode")).sendKeys("SWP3 048");
		
		//Enter an email address
		driver.findElement(By.id("user_user_detail_attributes_email")).sendKeys("frankie.m@hotmail.com");

		//Enter a password
		driver.findElement(By.id("user_user_detail_attributes_password")).sendKeys("1qaz@WSX");
		
		//Enter password confirmation
		driver.findElement(By.id("user_user_detail_attributes_password_confirmation")).sendKeys("1qaz@WSX");
		
		//Click on Submit button
        driver.findElement(By.name("submit")).click();
        
        //Check whether the navigated URL is correct
        sa.assertTrue(driver.getCurrentUrl().contains("insurance/v1"), "User Registration is successful");
        
        //Check whether the navigated page (Login page) is correct
        sa.assertEquals(driver.findElement(By.xpath("//h3[contains(text(), \"Login\")]")).getText(), "Login");

	}
	
	@Test (priority = 2)
	public void userLogin_valiCredentials()
	{

		//Enter the registered Email Address
		driver.findElement(By.id("email")).sendKeys("frankie.m@hotmail.com");	
		
		//Enter the registered Password
		driver.findElement(By.id("password")).sendKeys("1qaz@WSX");	
		
		//Click on Submit button to Log in
		driver.findElement(By.name("submit")).click();
		
		//Wait until the username element located after logged in
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'content']/h4")));
		
		//Check whether the username displayed in the Dashboard is the expected
		sa.assertEquals(driver.findElement(By.xpath("//div[@class = 'content']/h4")).getText(), "frankie.m@hotmail.com");
		
	}
	

	@Test (priority = 3)
	public void logout()
	{
		//Click on Logout button
		driver.findElement(By.xpath("//input[@value = 'Log out']")).click();
		
		//Wait until the Login button located
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), \"Login\")]")));
		
		//Check whether the navigated page has expected text
		sa.assertEquals(driver.findElement(By.xpath("//h3[contains(text(), \"Login\")]")).getText(), "Login");
	}
	
	@AfterTest
	public void teardown() 
	{
		sa.assertAll();
		
		//Close the driver
		driver.close();
		
		//Quit the driver
		driver.quit();
	}
	

}

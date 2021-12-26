package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	
	WebDriver driver;
	
	By btnRegister = By.linkText("Register");
	By text_mainTitle = By.xpath("//h1[contains(text(), \"Sign up as a new user\")]");
	By textbox_userTitle = By.id("user_title");
	By textbox_firstname = By.id("user_firstname");
	By textbox_surname = By.id("user_surname");
	By textbox_phone = By.id("user_phone");
	By drpDwn_DOB_year = By.id("user_dateofbirth_1i");
	By drpDwn_DOB_month = By.id("user_dateofbirth_2i");
	By drpDwn_DOB_day = By.id("user_dateofbirth_3i");
	By radiobtn_provisional = By.id("licencetype_f");
	By drpDwn_licensePeriod = By.id("user_licenceperiod");
	By drpDwn_Occupation = By.id("user_occupation_id");
	By textbox_Street = By.id("user_address_attributes_street");
	By textbox_City = By.id("user_address_attributes_city");
	By textbox_Country = By.id("user_address_attributes_county");
	By textbox_Postcode = By.id("user_address_attributes_postcode");
	By textbox_email = By.id("user_user_detail_attributes_email");
	By textbox_password = By.id("user_user_detail_attributes_password");
	By textbox_confirmPassword = By.id("user_user_detail_attributes_password_confirmation");
    By btn_Create = By.name("submit");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void btnRegister_click() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnRegister));
	    driver.findElement(btnRegister).click();
	}
	
	public String getCurrentURL_RegisterPage() {
		return driver.getCurrentUrl();
	}
	
	public String getTitle_RegisterPage() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(text_mainTitle));
		return driver.findElement(text_mainTitle).getText();
	}
	
	public void select_userTitle(String str_UserTitle) {
		Select dropDwn_title = new Select(driver.findElement(textbox_userTitle));
		dropDwn_title.selectByVisibleText(str_UserTitle);			
	}
	
	public void enter_Firstname(String strfirstname) {
		driver.findElement(textbox_firstname).sendKeys(strfirstname);
	}
	
	public void enter_Surname(String strSurname) {
		driver.findElement(textbox_surname).sendKeys(strSurname);		
	}
	
	public void enter_phone(String strPhone) {
		driver.findElement(textbox_phone).sendKeys(strPhone);
	}
	
	public void select_DOBYear(String strDOBYear) {
		Select drpDwn_DOBYear = new Select(driver.findElement(drpDwn_DOB_year));
		drpDwn_DOBYear.selectByVisibleText(strDOBYear);
	}
	
	public void select_DOBMonth(String str_DOBMonth) {
		Select dropDOB_month = new Select(driver.findElement(drpDwn_DOB_month));
		dropDOB_month.selectByVisibleText(str_DOBMonth);
	}
	
//	public void select_DOBMonth(int index_DOBMonth) {
//		Select dropDOB_month = new Select(driver.findElement(drpDwn_DOB_month));
//		dropDOB_month.selectByIndex(index_DOBMonth);
//	}
	
	public void select_DOBday(String strDOBDay) {
		Select dropDOB_day = new Select(driver.findElement(drpDwn_DOB_day));
		dropDOB_day.selectByVisibleText(strDOBDay);
	}
	
	public void select_Provisional() {
		WebElement radio_provisional = driver.findElement(radiobtn_provisional);
		radio_provisional.click();
	}
	
	public void select_licesnsePeriod(String strLicensePeriod) {
		Select drpDwn_LP = new Select(driver.findElement(drpDwn_licensePeriod));
		drpDwn_LP.selectByVisibleText(strLicensePeriod);		
	}

	public void select_occupation(String strOccupation) {
		Select drpDwn_Occu = new Select(driver.findElement(drpDwn_Occupation));
		drpDwn_Occu.selectByVisibleText(strOccupation);
	}
	
	public void enterAddr_Street(String strStreet) {
		driver.findElement(textbox_Street).sendKeys(strStreet);
	}
	
	public void enterAddr_City(String strCity) {
		driver.findElement(textbox_City).sendKeys(strCity);
	}
	
	public void enterAddr_Country(String strCountry) {
		driver.findElement(textbox_Country).sendKeys(strCountry);
	}
	
	public void enterAddr_Postcode(String strPostcode) {
		driver.findElement(textbox_Postcode).sendKeys(strPostcode);
	}
	
	public void enter_Email(String strEmail) {
		driver.findElement(textbox_email).sendKeys(strEmail);
	}
	
	public void enter_Password(String strpassword) {
		driver.findElement(textbox_password).sendKeys(strpassword);
	}
	
	public void enter_ConfirmationPassword(String strconfPwd) {
		driver.findElement(textbox_confirmPassword).sendKeys(strconfPwd);
	}
	
	public void click_btnCreate() {
		driver.findElement(btn_Create).click();
	}
	
}

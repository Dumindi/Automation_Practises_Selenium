package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
		
	By emailAddress = By.id("email");
	By password = By.id("password");
	By btnLogin = By.name("submit");
	By titleText = By.xpath("//h3[contains(text(), \"Login\")]");	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getCurrentURL_LP() {		
		return driver.getCurrentUrl();
	}
	
	public String getTitle_LoginPage() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(titleText));
		return driver.findElement(titleText).getText();		
	}
	
	public void enter_username(String strUsername) {
		driver.findElement(emailAddress).sendKeys(strUsername);
	}
	
	public void enter_password(String strPassword) {
		driver.findElement(password).sendKeys(strPassword);
	}
	
	public void login() {
		driver.findElement(btnLogin).click();
	}
	
	public void login_guru99InsurancePortal(String strUsername,String strPassword ) {
		driver.findElement(emailAddress).sendKeys(strUsername);
		driver.findElement(password).sendKeys(strPassword);
		driver.findElement(btnLogin).click();
	}	

}

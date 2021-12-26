package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	By usernameAtHomePage = By.xpath("//div[@class = 'content']/h4");
	By btnLogout = By.xpath("//input[@value = 'Log out']");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getCurrentURL_HP() {
		return driver.getCurrentUrl();
	}
	
	public String getUsernameAtHomePage() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameAtHomePage));
		return driver.findElement(usernameAtHomePage).getText();
	}
	
	public void click_logoutBtn() {
		driver.findElement(btnLogout).click();
	}
	
	
	
}

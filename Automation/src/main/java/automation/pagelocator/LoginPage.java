package automation.pagelocator;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	public LoginPage(WebDriver _driver)
	{
		this.driver = _driver;
	}
	public void LoginFunction (String email, String pass) {
		WebElement textEmail= driver.findElement(By.id("email"));
		if(textEmail.isDisplayed()==true)
		{
			textEmail.sendKeys(email);
		}
		WebElement textPass= driver.findElement(By.id("password"));
		if(textPass.isDisplayed()==true)
		{
			textPass.sendKeys(pass);
			driver.findElement(By.name("signin")).click();
		driver.switchTo().alert().accept();
			
	}

	}
}



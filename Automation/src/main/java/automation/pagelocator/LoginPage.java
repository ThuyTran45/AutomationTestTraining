package automation.pagelocator;

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
		if(textEmail.isDisplayed())
		{
			textEmail.sendKeys(email);
		}
		WebElement textPass= driver.findElement(By.id("password"));
		if(textPass.isDisplayed())
		{
			textPass.sendKeys(pass);
			driver.findElement(By.name("signin")).click();
		driver.switchTo().alert().accept();

	}

	}
}



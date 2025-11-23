package automation.testsuite;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class Login extends CommonBase {
	@BeforeMethod
	public void openWebpage() {
		driver = FirefoxDriver(CT_pageURL.CRMSTAR_URL);
	}

	@Test
	public void loginSuccessfully()
	{
		WebElement textEmail= driver.findElement(By.id("email"));
		if(textEmail.isDisplayed()==true)
		{
			textEmail.sendKeys("admin@gmail.com");
		}
		WebElement textPass= driver.findElement(By.id("password"));
		if(textPass.isDisplayed()==true)
		{
			textPass.sendKeys("12345678");
			driver.findElement(By.name("signin")).click();
			//Accept alert
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			WebElement textQuanLyND = driver.findElement(By.xpath("//p[text()='Quản lý người dùng']"));	
			assertTrue(textQuanLyND.isDisplayed());
		}
	}
	@Test
	public void loginFail() throws InterruptedException {
		WebElement textEmail = driver.findElement(By.id("email"));
		if (textEmail.isDisplayed() == true) {
			textEmail.sendKeys("admin_Incorrect@gmail.com");
			Thread.sleep(3000);
		}
		WebElement textPass = driver.findElement(By.id("password"));
		if (textPass.isDisplayed() == true) {
			textPass.sendKeys("12345678");
			driver.findElement(By.name("signin")).click();
			// Accept alert
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			
			WebElement error = driver.findElement(By.xpath("//h4[text()='Đăng nhập']"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			assertTrue(error.isDisplayed());
			
		}
	}
}

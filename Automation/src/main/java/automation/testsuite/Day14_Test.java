package automation.testsuite;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;
import automation.pagelocator.LoginPage_Factory;
import automation.pagelocator.LogoutPage_Factory;

public class Day14_Test extends CommonBase {
	@BeforeMethod
	public void openWebpage() {
		driver = initFirefox(CT_pageURL.CRMSTAR_URL);
	}

	@Test
	public void loginSuccessfully() {
		LoginPage_Factory login = new LoginPage_Factory(driver);
		login.LoginFunction("admin@gmail.com", "12345678");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(driver.findElement(By.xpath("//p[text()='Quản lý người dùng']")).isDisplayed());
	}

	@Test
	public void logoutSuccessfully() {
		loginSuccessfully();
		LogoutPage_Factory logout = new LogoutPage_Factory(driver);
		logout.LogoutFunction();
		assertTrue(driver.findElement(By.id("login")).isDisplayed());

	}
}

package automation.testsuite;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;
import automation.pagelocator.LoginPage;
import automation.pagelocator.LogoutPage;

public class LogoutTest extends CommonBase {
	@BeforeMethod
	public void openWebPage() {
		driver = initFirefox(CT_pageURL.CRMSTAR_URL);
	}

	@Test
	public void logoutSuccessfully() throws InterruptedException  {
		LoginPage login = new LoginPage(driver);
		login.LoginFunction("admin@gmail.com", "12345678");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(Duration.ofSeconds(10));
				// Chờ admin item display thì click
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
					WebElement admin_item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuLink")));
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_item);
		assertTrue(admin_item.isDisplayed());
		LogoutPage logoutPage = new LogoutPage(driver);
		logoutPage.LogoutFunction();
		// Chờ logout item hiển thị thì click
		WebElement logout_item = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='dropdown-item' and normalize-space(text())='Đăng xuất']")));
		assertTrue(logout_item.isDisplayed());
		// Chờ submit item hiển thị thì click
		WebElement submit_item = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-success' and @type= 'submit' and text()='Đăng xuất']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit_item);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit_item);
	}
}

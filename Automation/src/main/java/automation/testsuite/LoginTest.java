package automation.testsuite;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;
import automation.pagelocator.LoginPage;

public class LoginTest extends CommonBase{
@BeforeMethod
public void openWebPage()
{
	driver = initFirefox(CT_pageURL.CRMSTAR_URL);
}
@Test
public void loginSuccessfully() {
LoginPage login = new LoginPage(driver);
login.LoginFunction("admin@gmail.com", "12345678");
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement linkQuanLyND = wait.until(
  ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//li[contains(@class, 'btn-toggle-nav')]//a[normalize-space(text())='Quản lý người dùng']")
  )
);
assertTrue(linkQuanLyND.isDisplayed());
	}

public void LoginFail_IncorrectPass() {
	LoginPage login = new LoginPage(driver);
	login.LoginFunction("admin_Incorrect@gmail.com", "12345678_0000");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	WebElement textDangnhap = driver.findElement(By.xpath("//h4[text()='Đăng nhập']"));

	assertTrue(textDangnhap.isDisplayed());
}
}


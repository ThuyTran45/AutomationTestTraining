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

public class BTVN_Day16_Bài1 extends CommonBase {
	@BeforeMethod
	public void openWebpage() {
		driver = FirefoxDriver(CT_pageURL.CRMSTAR_URL);
	}

	@Test
	public void loginSuccessfully() {
		type(By.name("email"), "admin@gmail.com");
		type(By.name("password"), "12345678");
		click(By.xpath("//button[@class= 'btn btn-success' and text()='Đăng nhập']"));
		driver.switchTo().alert().accept();
		findElement_fluent(By.xpath("//a[normalize-space()='Quản lý khu làm việc']"));
	}

	@Test
	public void addSuccessfully_KLV() {
		loginSuccessfully();
		click(By.xpath("//a[normalize-space()='Quản lý khu làm việc']"));
		click(By.xpath("//button[text()='Thêm mới' and @type='button']"));
		type(By.name("work_areas_code"), "A45");
	    type(By.name("name"), "Ann2");
	    click(By.xpath("//button[@class='btn btn-outline-success' and normalize-space()='Lưu']"));

	
	}
}

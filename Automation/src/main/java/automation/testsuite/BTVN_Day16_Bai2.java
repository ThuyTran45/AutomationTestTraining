package automation.testsuite;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day16_Bai2 extends CommonBase {
	@BeforeMethod
	public void openWebpage() {
		driver = initFirefox(CT_pageURL.Sele_Day16_bai2);
	}
	@Test
	public void Test() {
		click(By.xpath("//button[text()='Try it']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = alert.getText();
		Assert.assertEquals(alertMessage, "Welcome to Selenium WebDriver Tutorials");
		alert.accept();
	}
}

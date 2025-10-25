package automation.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day8_Bài2 extends CommonBase {
	@BeforeMethod
	public void init1() {
		driver = initWebDriver(CT_pageURL.Day8_Bai2);
	}

	@Test
	public void getElementbyId_Day8_Bai2() {
		WebElement name = driver.findElement(By.name("name"));
	}

	@Test
	public void getElementbyname_Day8_Bai2() {
		WebElement address = driver.findElement(By.name("address"));
		System.out.println("address element is:" + address);
		WebElement email = driver.findElement(By.name("email"));
		System.out.println("password element is:" + email);
		WebElement password = driver.findElement(By.name("password"));
		System.out.println("password element is:" + password);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

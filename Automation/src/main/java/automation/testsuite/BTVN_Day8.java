package automation.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day8 extends CommonBase {
	@Test
	public void getElementbyname_Day8_Bai1() {
		driver = initWebDriver(CT_pageURL.Day8_Bai1);
		WebElement email = driver.findElement(By.name("email"));
		System.out.println("Email element is:" + email);
		WebElement password = driver.findElement(By.name("Password"));
		System.out.println("password element is:" + password);
		WebElement company = driver.findElement(By.name("company"));
		System.out.println("company element is:" + company);
		WebElement m1 = driver.findElement(By.name("mobile number"));
		System.out.println("mobile number element is:" + m1);
	}
		@Test
		public void getElementbyId_Day8_Bai2() {
			driver = initWebDriver(CT_pageURL.Day8_Bai2);
			WebElement name = driver.findElement(By.name("name"));
		
	}
	@Test
	public void getElementbyname_Day8_Bai2() {
		driver = initWebDriver(CT_pageURL.Day8_Bai2);
		WebElement address = driver.findElement(By.name("address"));
		System.out.println("address element is:" + address);
		WebElement email = driver.findElement(By.name("email"));
		System.out.println("password element is:" + email);
		WebElement password = driver.findElement(By.name("password"));
		System.out.println("password element is:" + password);
	}


}

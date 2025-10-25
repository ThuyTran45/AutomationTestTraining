package automation.testsuite;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class Day8_Locator extends CommonBase{
	@Test
	public void getElementbyId()
	{
		driver = initWebDriver(CT_pageURL.RISE_URL);
		WebElement email = driver.findElement(By.id("email"));
		System.out.println("Email element is:" + email);
		WebElement password = driver.findElement(By.name("password"));
		System.out.println("password element is:" + password);
	}
	@Test
	public void getElementbylinkText()
	{
		driver = initWebDriver(CT_pageURL.BEPANTOAN_URL);
		WebElement linkText = driver.findElement(By.linkText("Máy Rửa Chén Bát"));
		System.out.println("linkText element is:" + linkText);
		
	}
	@Test
	public void getElementbyClass_TagName()
	{
		driver = initWebDriver(CT_pageURL.BEPANTOAN_URL);
		WebElement h1 = driver.findElement(By.className("inline-block"));
		System.out.println("h1 là :" + h1);
		WebElement h3 = driver.findElement(By.tagName("h3"));
		System.out.println("h3 là :" + h3);
	}
}

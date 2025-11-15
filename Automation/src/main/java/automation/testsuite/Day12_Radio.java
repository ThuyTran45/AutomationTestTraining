package automation.testsuite;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class Day12_Radio extends CommonBase {
	@Test
	public void clickToRadio() {
		driver = ChromeDriver(CT_pageURL.DEMOQA_URL);
		// Kiểm tra giá trị mặc định isSelected == False
		WebElement maleRadio = driver.findElement(By.id("gender-radio-1"));//input element
		WebElement femaleRadio = driver.findElement(By.id("gender-radio-2"));//input
		WebElement otherRadio = driver.findElement(By.id("gender-radio-3"));//input

		boolean male = maleRadio.isSelected();
		// Mong đợi xác nhận rằng male chưa được bấm = False
		assertFalse(male);
		// Mong đợi xác nhận rằng Female chưa được bấm = False
		assertFalse(femaleRadio.isSelected());
		// Mong đợi xác nhận rằng other chưa được bấm = False
		assertFalse(otherRadio.isSelected());
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", femaleRadio);
		// Click Female
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		// femaleRadio.click();
		assertTrue(femaleRadio.isSelected());

	}

}

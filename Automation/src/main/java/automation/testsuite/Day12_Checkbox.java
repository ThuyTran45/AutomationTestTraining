package automation.testsuite;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

@Test
public class Day12_Checkbox extends CommonBase {
	@BeforeMethod
	@Parameters("browser") //browser là name của parameters trong testNG
	public void openWebPage(String browserSetup) {
		setupDriver(browserSetup);
		driver.get(CT_pageURL.DEMOQA_URL);
	}
	public void checkHandle() {

		// Case1: kiểm tra giá trị mặc định (theo yêu cầu REQ), vd: vào trang web thì
		// các checkbox chưa được check
		// các checbox chưa được check

		WebElement sportCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
		WebElement readCheckbox = driver.findElement(By.id("hobbies-checkbox-2"));
		WebElement musicCheckbox = driver.findElement(By.id("hobbies-checkbox-3"));
		// Mong đợi cả 3 checkbox đều chưa check (theo REQ)
		boolean checkSport = sportCheckbox.isSelected();
		boolean checkRead = readCheckbox.isSelected();
		boolean checkMusic = musicCheckbox.isSelected();
		assertFalse(checkSport);
		assertFalse(checkRead);
		assertFalse(checkMusic);
	}

	public void clickToCheckbox() {
		driver = ChromeDriver(CT_pageURL.DEMOQA_URL);
		WebElement sportCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
		WebElement readCheckbox = driver.findElement(By.id("hobbies-checkbox-2"));
		WebElement musicCheckbox = driver.findElement(By.id("hobbies-checkbox-3"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sportCheckbox);

		if (!sportCheckbox.isSelected()) {
			driver.findElement(By.xpath("//label[text()='Sports']")).click();
			// sportCheckbox.click();
			assertTrue(sportCheckbox.isSelected());
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", readCheckbox);
		if (!readCheckbox.isSelected()) {
			driver.findElement(By.xpath("//label[text()='Reading']")).click();
			// readCheckbox.click();
			assertTrue(readCheckbox.isSelected());
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", musicCheckbox);
		if (!musicCheckbox.isSelected()) {
			driver.findElement(By.xpath("//label[text()='Music']")).click();
			// musicCheckbox.click();
			assertTrue(musicCheckbox.isSelected());
		}

	}
}

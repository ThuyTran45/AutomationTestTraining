package automation.testsuite;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day12_Bai2 extends CommonBase {
	@Test
	public void clickRadiobutton() throws InterruptedException {
		driver = ChromeDriver(CT_pageURL.Day12_Bai2);

		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement olderRadio = driver.findElement(By.id("over_18"));
		WebElement disableRadio = driver.findElement(By.id("radio-disabled"));

		boolean under18 = under18Radio.isSelected();
		boolean disable = disableRadio.isEnabled();
		// Mong đợi xác nhận rằng under18 chưa được bấm = False
		assertFalse(under18);
		// Mong đợi xác nhận rằng older chưa được bấm = False
		assertFalse(olderRadio.isSelected());
		// Mong đợi rằng phần tử disableRadio ở trạng thái disable
		assertFalse(disable);
		// Mong đợi disableRadio chưa được chọn
		assertFalse(disableRadio.isSelected());
		// Đảm bảo disableRadio hiển thị trên màn hình (vùng nhìn thấy)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", disableRadio);
		// Click olderRadio
		driver.findElement(By.xpath("//label[text()='18 or older']")).click();
		Thread.sleep(3000);
		// Button under18Radio.click();
		assertTrue(olderRadio.isSelected());

		// Click under18Radio
		driver.findElement(By.xpath("//label[text()='Under 18']")).click();
		// Button under18Radio.click();
		Thread.sleep(3000);
		assertTrue(under18Radio.isSelected());

		// Click disableRadio
		driver.findElement(By.xpath("//label[text()='Radio button is disabled']")).click();
		Thread.sleep(3000);
		// Radio button is disabled.click();
		assertFalse(disableRadio.isSelected());

	}
@Test
	public void selectSingleDropdown() throws InterruptedException {
		driver = ChromeDriver(CT_pageURL.Day12_Bai2);
		Thread.sleep(8000);
		Select singleDropdown = new Select(driver.findElement(By.id("job1")));
		// Kiểm tra size
		int size = singleDropdown.getOptions().size();
		System.out.println("Size is: " + size);

		// chọn Automation Testing theo cách 1
		singleDropdown.selectByVisibleText("Automation Testing");
		String automationTesting = singleDropdown.getFirstSelectedOption().getText();
		System.out.println("Text sau khi chọn Automation Testing: " + automationTesting);
		// chọn Manual Testing theo cách 2
		singleDropdown.selectByIndex(1);
		Thread.sleep(3000);
		String manualTesting = singleDropdown.getFirstSelectedOption().getText();
		System.err.println("Text sau khi chọn manualTesting: " + manualTesting);
	}

}

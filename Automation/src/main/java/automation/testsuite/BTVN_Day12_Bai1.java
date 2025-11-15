package automation.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day12_Bai1 extends CommonBase {
	@Test
	public void selectCountry() throws InterruptedException {
		driver = ChromeDriver(CT_pageURL.Day12_Bai1);
		Thread.sleep(3000);
		Select dropcountry = new Select(driver.findElement(By.tagName("select")));
		// Kiểm tra size
		int size = dropcountry.getOptions().size();
		System.out.println("Size is: " + size);
		// chọn Afghanistan theo cách 1
		dropcountry.selectByVisibleText("Afghanistan");
		String Afghanistan = dropcountry.getFirstSelectedOption().getText();
		System.out.println("Text sau khi chọn Afghanistan: " + Afghanistan);
		// chọn Algeria theo cách 2
		dropcountry.selectByIndex(3);
		String Algeria = dropcountry.getFirstSelectedOption().getText();
		System.err.println("Text sau khi chọn Algeria : " + Algeria);
	}

}

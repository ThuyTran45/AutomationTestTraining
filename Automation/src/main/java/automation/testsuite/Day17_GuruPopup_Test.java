package automation.testsuite;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;
import automation.pagelocator.Day17_GuruPopup;

public class Day17_GuruPopup_Test extends CommonBase {
	@BeforeMethod
	public void openGuruPage() {
		driver = initFirefox(CT_pageURL.Guru_PopupURL);
	}

	@Test
	public void getDetailAccess_Successfully() {
		// 1.Tìm window đầu tiên(main, first)
		String firstWindow = driver.getWindowHandle();
		// Click button
		click(By.xpath("//a[text()='Click Here']"));
		// Lấy các window con rồi xử lý trên trang cần test
		Set<String> windows = driver.getWindowHandles();
		for (String childWindow : windows) {
			if (!childWindow.equals(firstWindow)) {
				driver.switchTo().window(childWindow);
				Day17_GuruPopup guruPage = new Day17_GuruPopup(driver);
				guruPage.getAccessDetails();
				isDisplay_fluent(By.xpath("//h2[text()='Access details to demo site.']"));
				driver.close();
			}
		}
		// Trở về window đầu tiên để thực hiện tiếp
		driver.switchTo().window(firstWindow);
		String actualUrl = driver.getCurrentUrl();
		// Expected: https://demo.guru99.com/popup.php
		assertEquals(actualUrl, "https://demo.guru99.com/popup.php");
	}
}

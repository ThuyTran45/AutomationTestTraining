package automation.testsuite;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day18_Bai2 extends CommonBase {

	@BeforeMethod
	// Chạy trên nhiều trình duyệt
	@Parameters("browser") // browser là name của parameters trong testNG
	public void openWebPage(String browserSetup) {
		setupDriver(browserSetup);
		driver.get(CT_pageURL.BEPANTOAN_URL);
	}

	@Test
	public void newPage() throws InterruptedException {
		// Lưu lại cửa sổ window đầu tiên
		String firstWindow = driver.getWindowHandle();
		System.out.println(firstWindow);
		// Click Zalo item
		click(By.xpath("// a[contains(@href,'https://zalo.me')]/span[text()='Chat với chúng tôi']"));
		// Lấy tất cả các mã định danh Tab Window.
		Set<String> windows = driver.getWindowHandles();
		for (String childWindow : windows) {
			if (!childWindow.equals(firstWindow)) {
				driver.switchTo().window(childWindow);
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				String actualUrl = driver.getCurrentUrl();
				System.out.println("acutalUrl: " + actualUrl);
				assertTrue(actualUrl.contains("https://id.zalo.me/"));
				driver.close();
			}
			driver.switchTo().window(firstWindow);
		}
	}

	@Override
	@AfterMethod
	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

}

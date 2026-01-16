package automation.testsuite;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day17_Bai1 extends CommonBase {
	@BeforeMethod
	public void openMediaPage() {
		driver = initFirefox(CT_pageURL.Day17_Zalo);
	}

	@Test
	public void message_Zalo() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Số lượng iframe là: " + size);
		// Switch vào iframe đó
		driver.switchTo().frame(findElement_fluent(By.xpath("//iframe[contains(@src,'https://page.widget.zalo.me')]")));
		click(By.xpath("//div[@class='za-chat__head-box']"));
		assertTrue(isDisplay_fluent(By.xpath("//p[text()='Siêu thị điện máy MediaMart']")));

	}

//1.Accessing the Element within the Shadow DOM easy chatgpt-widget is the shadow host
	@Test
	public void cach2_message_Zalo() {
		// Lấy element host của Shadow DOM
		WebElement shadowHost = driver.findElement(By.id("easychatgpt-widget"));
		// Lấy ShadowRoot
		SearchContext ShadowRoot = shadowHost.getShadowRoot();
		// Click button close cssSelector button.w-5.h-5 (// Click button close trong
		// Shadow DOM)
		WebElement closeButton = ShadowRoot.findElement(By.cssSelector("button.w-5.h-5"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", closeButton);
		js.executeScript("arguments[0].click();", closeButton);

		driver.switchTo().frame(findElement_fluent(By.xpath("//iframe[contains(@src,'https://page.widget.zalo.me')]")));
		click(By.xpath("//div[@class='za-chat__head-box']"));
		click(By.xpath("//div[text()='Chat nhanh']"));
		assertTrue(isDisplay_fluent(By.xpath("//p[text()='Siêu thị điện máy MediaMart']")));

	}
}

//@AfterMethod
//public void closeDriver() {
//	if (driver != null)
//		driver.close();
//}

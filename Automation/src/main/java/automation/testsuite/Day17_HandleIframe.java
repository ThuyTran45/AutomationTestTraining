package automation.testsuite;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class Day17_HandleIframe extends CommonBase {
	@BeforeMethod
	@Parameters("browser") //browser là name của parameters trong testNG
	public void openWebPage(String browserSetup) {
		setupDriver(browserSetup);
		driver.get(CT_pageURL.CODESTSAR2_URL);
	}


	@Test
	public void dangKyTuVan_Notsuccessully() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Số lượng iframe là: " + size);
		scrollToElement(By.xpath("//h2[text()='Đăng kí nhận tư vấn lộ trình phát triển nghề nghiệp về AWS/Kiểm thử/Lập trình web']"));
		driver.switchTo().frame(0);
		type(By.id("name"), "Test name");
		type(By.id("phone_number"), "043848348434");
		type(By.id("email"), "Email@gmail.com");
		assertTrue(isDisplay_fluent(By.id("name")));
	}

	@Test
	public void followFacebook() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Số lượng iframe là: " + size);
		scrollToElement(By.xpath("//p[text()='Về chúng tôi']"));
		driver.switchTo().frame(3);
		String firstWindow = driver.getWindowHandle();
		click(By.xpath("//a[text()='Follow Page']"));
		//Mở ra tab window mới, lấy currentUrl rồi assert
		Set<String> windows = driver.getWindowHandles();
		for(String childWindow : windows)
		{
			if(!childWindow.equals(firstWindow))
			{
			driver.switchTo().window(childWindow);
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			String actualUrl = driver.getCurrentUrl();
			System.out.println("acutalUrl: "+ actualUrl);
			assertEquals(actualUrl, "https://www.facebook.com/CodeStarAcademy/?ref=embed_page");
			driver.close();
			}
			driver.switchTo().window(firstWindow);
		}
	}

}

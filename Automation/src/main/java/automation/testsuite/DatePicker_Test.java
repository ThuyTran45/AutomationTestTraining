package automation.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class DatePicker_Test extends CommonBase{
	@BeforeClass
	public void openWebPage()
	{
		driver= initFirefox(CT_pageURL.Day15_DatePciker);
	}

	@Test
	public void date_Picker() {
		WebElement dayteBox= driver.findElement(By.xpath("//input[@type='datetime-local']"));
		dayteBox.clear();
		dayteBox.sendKeys("031220260204CH");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

}

package automation.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class Day12_Dropdownlist extends CommonBase{
	@Test
	public void selectDropdownlist() throws InterruptedException
	{
		driver = ChromeDriver(CT_pageURL.CODESTSAR_URL);
		Thread.sleep(3000);
		Select dropCourse = new Select(driver.findElement(By.id("product_categories_filter")));
		//Kiểm tra size
		int size = dropCourse.getOptions().size();
		System.out.println("Size is: "+ size);
		
		//chọn aws theo cách 1
		dropCourse.selectByVisibleText("AWS");
		String aws = dropCourse.getFirstSelectedOption().getText();
		System.out.println("Text sau khi chọn AWS: "+ aws);
		//chọn lập trình web theo cách 2
		dropCourse.selectByIndex(3);
		String laptrinhWeb = dropCourse.getFirstSelectedOption().getText();
		System.err.println("Text sau khi chọn lap trinh Web: "+ laptrinhWeb);
	}

}

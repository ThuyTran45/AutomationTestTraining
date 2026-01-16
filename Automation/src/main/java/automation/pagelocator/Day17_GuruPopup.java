package automation.pagelocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.common.CommonBase;

public class Day17_GuruPopup extends CommonBase {
	private WebDriver driver;

	public Day17_GuruPopup(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public void getAccessDetails()
	{
		type(By.name("emailid"), "randomeemail@gmail.com");
		click(By.name("btnLogin"));
	}

}

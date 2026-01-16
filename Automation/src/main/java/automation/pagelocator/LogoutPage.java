package automation.pagelocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage {
	private WebDriver driver;

	public LogoutPage(WebDriver _driver) {
		this.driver = _driver;
	}

	public void LogoutFunction() {
		WebElement admin_item = driver.findElement(By.id("dropdownMenuLink"));
		if (admin_item.isDisplayed()) {
			admin_item.click();
		}
		WebElement logout_item = driver
				.findElement(By.xpath("//button[@class='dropdown-item' and normalize-space(text())='Đăng xuất']"));
		if (logout_item.isDisplayed()) {
			logout_item.click();
		}

		WebElement submit_item = driver
				.findElement(By.xpath("//button[@class='btn btn-success' and @type= 'submit' and text()='Đăng xuất']"));
		if (submit_item.isDisplayed()) {
			submit_item.click();
		}
	}
}

package automation.pagelocator;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage_Factory {
	private WebDriver driver;
	@FindBy(id = "dropdownMenuLink")
	WebElement adminDropdown;
	@FindBy(xpath = "//button[text()='Đăng xuất' and @type = 'button']")
	WebElement btnDangXuat;
	@FindBy(xpath = "//button[text()='Đăng xuất' and @type = 'submit']")
	WebElement btnConfirmDangXuat;

	public LogoutPage_Factory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void LogoutFunction() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		adminDropdown.click();
		btnDangXuat.click();
		// Đợi button confirm đăng xuất hiển thị ra
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Đăng xuất' and @type = 'submit']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnConfirmDangXuat);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnConfirmDangXuat);
		btnConfirmDangXuat.click();
		
	}
}
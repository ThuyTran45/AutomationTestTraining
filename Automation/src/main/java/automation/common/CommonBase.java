package automation.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonBase {
	public static WebDriver driver;

	public WebDriver initWebDriver(String URL) {
		System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		return driver;

	}

	public void closeDriver() {
		if (driver != null)
			driver.close();
	}
}

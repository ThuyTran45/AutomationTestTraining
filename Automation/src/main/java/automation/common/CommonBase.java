package automation.common;

import static java.time.Duration.ofSeconds;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonBase {
	public static WebDriver driver;
	public WebDriver initFirefox(String URL) {
		System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
		FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.cookie.cookieBehavior", 0); // Allows all cookies
        profile.setPreference("permissions.default.cookie", 1); // auto allow cookie permissions if site require
        profile.setPreference("permissions.default.desktop-notification", 1);// auto allow page send "desktop

        // Add the profile to FirefoxOptions
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

		driver = new FirefoxDriver(options);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		return driver;
	}

	public WebDriver ChromeDriver(String URL) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--allow-third-party-cookies");

		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("credentials_enable_service", false); // Disables the "save password" prompt
		chromePrefs.put("profile.password_manager_enabled", false); // Disables the password manager
		chromePrefs.put("profile.password_manager_leak_detection", false); // Disables the password leak detection
																			// warning
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		ChromeDriver driver = new ChromeDriver(chromeOptions);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		return driver;
	}



	// 1. Explicit wait
	public WebElement findElement_Ex(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	// 1. 1.Explicit wait for visibility of all elements
	public List<WebElement> findElements_Ex(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	    return driver.findElements(locator);
	}
	// 2. Fluent wait cho By
	public WebElement findElement_fluent(By locator) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) // Maximum time to wait
				.pollingEvery(Duration.ofMillis(200)) // Interval between each poll
				.ignoring(NoSuchElementException.class);
		// Exceptions to ignore
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	// 2.1. Fluent wait cho @FindBy
	public WebElement findElement_fluent(WebElement element) {
	    Wait<WebDriver> wait = new FluentWait<>(driver)
	        .withTimeout(Duration.ofSeconds(10))
	        .pollingEvery(Duration.ofMillis(200))
	        .ignoring(NoSuchElementException.class);

	    wait.until(driver -> element.isDisplayed());

	    return element;
	}
//Method click bằng Actions + wait element clickable (có thể do thư viện trình duyệt nê có luc click được và có luc k)
	public void clickByActions(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    new Actions(driver)
	        .moveToElement(element)
	        .click()
	        .perform();
	}
	// Wrap click method
	public void click(By locator) {
		WebElement element = findElement_fluent(locator);
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)) // Maximum time to wait
				.pollingEvery(Duration.ofMillis(200)) // Interval between each poll
				.ignoring(NoSuchElementException.class);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}

	// list result by search method: tìm lại element mới nhất tại thời điểm click → không xảy ra stale.
	public int resultList_search(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Wait until element is displayed after fresh WebElement (wait newest element list)
	    List<WebElement> list = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));

	    // Now click the fresh reference
	    return list.size();
	}

	// Wrap sendKeys method
	public void type(By locator, String value) {
		WebElement element = findElement_fluent(locator);
		element.clear();
		element.sendKeys(value);
	}

	// Click by javascript
	public void clickByJS(By locator) {
		WebElement element = findElement_fluent(locator);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	public boolean isDisplay_fluent(By locator) {
		try {
			WebElement element = findElement_fluent(locator);
			return element.isDisplayed(); // true
		} catch (NoSuchElementException ex1) {
			return false; // mean element is not Display, error in findElement_fluent
		} catch (Exception ex) {
			return false;
		}
	}

	// Scroll to element By locator
	public void scrollToElement(By locator) {
		WebElement element = findElement_fluent(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Scroll to element to center viewport By locator
		public void scrollToElement_Center(By locator) {
			WebElement element = findElement_fluent(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
		}

	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

	private WebDriver initFirefoxDriver() {
		System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("network.cookie.cookieBehavior", 0); // Allows all cookies
//		 // Add the profile to FirefoxOptions
//        FirefoxOptions options = new FirefoxOptions();
//        options.setProfile(profile);
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(ofSeconds(30));
		return driver;

	}

	private WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
//		ChromeOptions ChromeOptions = new ChromeOptions();
//		Map<String, Object> Chromeprefs = new HashMap<>();
//		Chromeprefs.put("credentials_enable_service", false); // Disable the "save password" prompt
//		Chromeprefs.put("profile.password_manager_enabled", false);// Disable the password manager
//		Chromeprefs.put("profile.password_manager_leak_detection", false);// Disable the password leak detection warning
//		ChromeOptions.setExperimentalOption("prefs", Chromeprefs);
//		ChromeDriver driver = new ChromeDriver(ChromeOptions);
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(ofSeconds(30));
		return driver;
	}

	private WebDriver initEdge() {
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\driver\\msedgedriver.exe");
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(ofSeconds(30));
		return driver;
	}

	public WebDriver setupDriver(String browserName) {
		switch (browserName.trim().toLowerCase()) {
		case "firefox":
			System.out.println("Initialing firefox driver...");
			driver = initFirefoxDriver();
			break;
		case "chrome":
			System.out.println("Initialing chrome driver...");
			driver = initChromeDriver();
			break;
		case "edge":
			System.out.println("Initialing edge driver...");
			driver = initEdge();
			break;
		default:
			System.out.println("browser: " + browserName + "is invalid, Launch chrome: ");
			driver = initFirefoxDriver();
		}
		return driver;
	}

}

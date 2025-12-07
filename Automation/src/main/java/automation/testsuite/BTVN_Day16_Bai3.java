package automation.testsuite;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;

public class BTVN_Day16_Bai3 extends CommonBase{
	
	@BeforeMethod
	public void openWebPage()
	{
		driver = FirefoxDriver(CT_pageURL.Sele_Day16_bai3);
	}
	@Test
	public void alertOK()
	{
		click(By.xpath("//a[normalize-space()='Alert with OK']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(normalize-space(),'click the button to display an  alert box')]")));
        System.out.println(button.getText());
        click(By.xpath("//button[contains(normalize-space(),'click the button to display an  alert box')]"));
		wait.until(ExpectedConditions.alertIsPresent());
		String message = driver.switchTo().alert().getText();
		assertEquals(message, "I am an alert box!");
		driver.switchTo().alert().accept();
        closeDriver();

	}
	@Test
	public void alertOKandCancel()
	{
		click(By.xpath("//a[normalize-space()='Alert with OK & Cancel']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(normalize-space(),'display a confirm box')]")));
        System.out.println(button.getText());
        click(By.xpath("//button[contains(normalize-space(),'display a confirm box')]"));
		wait.until(ExpectedConditions.alertIsPresent());
		String message = driver.switchTo().alert().getText();
		assertEquals(message, "Press a Button !");
		driver.switchTo().alert().accept();
        closeDriver();

	}
	@Test
	public void alertTextBox()
	{
		click(By.xpath("//a[normalize-space()='Alert with Textbox']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(normalize-space(),'demonstrate the prompt box')]")));
        System.out.println(button.getText());
        click(By.xpath("//button[contains(normalize-space(),'demonstrate the prompt box')]"));
		wait.until(ExpectedConditions.alertIsPresent());
		String message = driver.switchTo().alert().getText();
		assertEquals(message, "Please enter your name");
		Alert prompt = driver.switchTo().alert();
		prompt.sendKeys("TrangNTK");
		prompt.accept();
        closeDriver();
	}


}

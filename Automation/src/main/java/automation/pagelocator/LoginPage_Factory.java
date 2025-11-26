package automation.pagelocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Factory {

private WebDriver driver;
@FindBy(id = "email") WebElement textEmail;
@FindBy(id = "password") WebElement textPass;
@FindBy(name = "signin") WebElement btnDangNhap;

public LoginPage_Factory(WebDriver driverTest) {
	this.driver = driverTest;
	PageFactory.initElements(driverTest, this);
}
public void LoginFunction(String email, String password)
{
	textEmail.sendKeys(email);
	textPass.sendKeys(password);
	btnDangNhap.click();
	driver.switchTo().alert().accept();
}
}

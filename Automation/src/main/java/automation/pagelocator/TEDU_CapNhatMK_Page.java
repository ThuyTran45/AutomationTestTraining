package automation.pagelocator;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TEDU_CapNhatMK_Page {
	private WebDriver driver;

	@FindBy(id= "onesignal-slidedown-cancel-button")
	WebElement btnSubcribe;
	@FindBy(xpath = "(//a[normalize-space()='Đăng nhập'])[1]")
	WebElement menuLogin;
	@FindBy(id = "UserName")
	WebElement textEmail;
	@FindBy(id = "password")
	WebElement textPass;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnLogin;

//update pass element
	@FindBy(xpath = "//i[@class='fas fa-user-circle']")
	WebElement avatar;
	@FindBy(xpath = "//a[@title='Đổi mật khẩu']")
	WebElement updatePassButton;
	@FindBy(id = "OldPassword")
	WebElement textOldPassword;
	@FindBy(id = "NewPassword")
	WebElement textNewPassword;
	@FindBy(id = "ConfirmNewPassword")
	WebElement textConfirmNewPassword;
	@FindBy(xpath = "//input[@value='Cập nhật']")
	WebElement btnUpdate;
	@FindBy(xpath = "//div[@class='alert alert-success']")
			WebElement Alert;

	public TEDU_CapNhatMK_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void loginFunction(String email, String pass) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		btnSubcribe.click();
		menuLogin.click();
		textEmail.sendKeys(email);
		textPass.sendKeys(pass);
		btnLogin.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	public void changePassword(String oldPass, String newPass) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		avatar.click();
		updatePassButton.click();
		textOldPassword.sendKeys(oldPass);
		textNewPassword.sendKeys(newPass);
		textConfirmNewPassword.sendKeys(newPass);
		btnUpdate.click();
	}
	   public boolean isAvatarDisplayed() {
	        return avatar.isDisplayed();
	    }

	   public boolean AlertDisplayed() {
	        return Alert.isDisplayed();
	    }
}

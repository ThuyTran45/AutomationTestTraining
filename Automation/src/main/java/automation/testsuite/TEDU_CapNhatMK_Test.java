package automation.testsuite;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;
import automation.pagelocator.TEDU_CapNhatMK_Page;

public class TEDU_CapNhatMK_Test extends CommonBase {
	@BeforeMethod
	public void openWebPage() {
		driver = ChromeDriver(CT_pageURL.DiDong_URL);
	}

	@Test
	public void loginSuccessfully() {
		TEDU_CapNhatMK_Page tedu = new TEDU_CapNhatMK_Page(driver);
		tedu.loginFunction("tranthanhthuy451991@gmail.com", "12345678");
		assertTrue(tedu.isAvatarDisplayed());

	}

	@Test
	public void updatePasswordSuccessfully() {
		loginSuccessfully();
		TEDU_CapNhatMK_Page tedu = new TEDU_CapNhatMK_Page(driver);
		tedu.changePassword("12345678", "12345678");
		assertTrue(tedu.AlertDisplayed());
	}
}

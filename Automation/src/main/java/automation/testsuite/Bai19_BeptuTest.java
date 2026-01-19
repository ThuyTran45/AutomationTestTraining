package automation.testsuite;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigInteger;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_pageURL;
import automation.pagelocator.Bai19_BeptuPage;

public class Bai19_BeptuTest extends CommonBase {
	private Bai19_BeptuPage beptu;

	@BeforeMethod
	public void openWebPage() {
		driver = initFirefox(CT_pageURL.BEPANTOAN_URL);
		beptu = new Bai19_BeptuPage(driver);
	}

	@Test
	// TC01: Lọc BT theo hãng khi không click xem thêm
	public void lochangBeptu_Kainer() {
		beptu.locHangBT_Kainer();
		assertEquals(beptu.resultList_search(beptu.result_Kainer), 9);
	}

	@Test
	// TC02:lọc BT theo hãng khi click xem thêm
	public void xemthem_hangSunhouse() {
		beptu.locHangBT_Sunhouse();
		assertEquals(beptu.resultList_search(beptu.result_Sunhouse), 14);

	}

	// TC03: lọc BT theo mức giá <3.000.000
	@Test
	public void locgia_muc1() {
		beptu.locgiaBT_muc1();
		// Lấy tất cả sản phẩm hiển thị trên trang web(bao gồm cả < 3000, có text liên
		// hệ)
		List<WebElement> ProductList = beptu.ProductList(beptu.product_result);
		// Set ngưỡng so sánh giá
		BigInteger threshold = new BigInteger("3000000");
		for (WebElement product : ProductList) {
			// List<WebElement> salePrices = beptu.ProductList(beptu.result_giathap);
			List<WebElement> salePrices = product.findElements(beptu.result_muc1);
			for (WebElement element : salePrices) {
				// Lấy text sản phẩm
				String priceText = element.getText();
				// loại bỏ tất cả ký tự không phải số, chỉ giữ lại chữ số.
				String price_number = priceText.replaceAll("[^0-9]", "");
				System.out.println("Parsed digits: " + price_number);
				if (!price_number.isEmpty()) {
					BigInteger price = new BigInteger(price_number);
					assertTrue(price.compareTo(threshold) < 0);
				} else {
					assertTrue(product.findElements(beptu.result_muc1_lienHe).size() > 0);
				}
			}
		}
	}

//TC04: lọc BT theo 3.000.000 <= mức giá < 5.000.000
	@Test
	public void locgia_muc2() throws InterruptedException {
		beptu.locgiaBT_muc2();
		String firstWindow = driver.getWindowHandle();
		// Mở ra tab window mới, lấy currentUrl rồi assert
		Set<String> windows = driver.getWindowHandles();
		for (String childWindow : windows) {
			if (!childWindow.equals(firstWindow)) {
				driver.switchTo().window(childWindow);
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				String actualUrl = driver.getCurrentUrl();
				System.out.println("acutalUrl: " + actualUrl);
				assertEquals(actualUrl,
						"https://bepantoan.vn/danh-muc/bep-tu?price=3000000-5000000&page=1&sort=discount");
				driver.close();
			}
			driver.switchTo().window(firstWindow);
		}
		// Lấy tất cả sản phẩm hiển thị trên trang web(bao gồm cả < 3000, có text liên
		// hệ)
		List<WebElement> ProductList = beptu.ProductList(beptu.product_result);
		// Set ngưỡng so sánh giá
		BigInteger min = new BigInteger("3000000");
		BigInteger max = new BigInteger("5000000");
		for (WebElement product : ProductList) {
			// List<WebElement> salePrices = beptu.ProductList(beptu.result_giathap);
			List<WebElement> salePrices = product.findElements(beptu.result_muc1);
			for (WebElement element : salePrices) {
				// Lấy text sản phẩm
				String priceText = element.getText();
				// loại bỏ tất cả ký tự không phải số, chỉ giữ lại chữ số.
				String price_number = priceText.replaceAll("[^0-9]", "");
				if (!price_number.isEmpty()) {
					BigInteger price = new BigInteger(price_number);
					assertTrue(price.compareTo(min) >= 0 && price.compareTo(max) <= 0);
				} else {
					assertTrue(product.findElements(beptu.result_muc1_lienHe).size() > 0);
				}
			}
		}
	}
	// TC05,06,07: tương tự TC03,04

	// TC08
	@Test
	public void xuatxu_Germany() {
		beptu.xuatxu(beptu.xuatxu_Germany);
		// Lấy danh sách sản phẩm sau khi lọc
		List<WebElement> ProductList = beptu.ProductList(beptu.product_result);
		// Đếm số sản phẩm
		int count = beptu.countVisibleElements(beptu.product_result, 10);
		assertEquals(count, 20, "Không tìm thấy sản phẩm");
		// Lặp từng sản phẩm
		for (int i = 0; i < count; i++) {
			// Lấy lại danh sách sản phẩm sau mỗi lần loop
			ProductList = beptu.ProductList(beptu.product_result);
			WebElement product = ProductList.get(i);
			// click bằng javascript
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
			String actual_xuatxu = beptu.detailInformation(beptu.detail_Germany);
			assertTrue(actual_xuatxu.equals("GERMANY"), "Sản phầm này không phải Germany");
			// Quay lại màn list
			driver.navigate().back();
		}
	}

	// TC09
	@Test
	public void xuatxu_England() {
		beptu.xuatxu(beptu.xuatxu_Anh);
		int count = beptu.countVisibleElements(beptu.product_result, 10);
		// assertTrue(count==0 , "Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn
		// của bạn");
		assertEquals(count, 0, "Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn");
	}

	// TC10: Chọn số bếp >> đang fail
	@Test
	public void select_sobep() {
		beptu.sobep_BT(beptu.sobep_5bep);
		// Lấy danh sách sản phẩm sau khi lọc
		List<WebElement> sobep_list = beptu.ProductList(beptu.product_result);
		// Đếm số sản phẩm
		int count = beptu.countVisibleElements(beptu.product_result, 10);
		assertEquals(count, 7, "Không tìm thấy sản phẩm"); // bỏ step này vì số lượng có thể tăng giảm, chỉ cần check số vùng nấu thoả mãn chưa
		// Lặp từng sản phẩm
		//số lương sp hiển thị đúng, check vs DB, viết method riêng cho DB (oracle)
		for (int i = 0; i < count; i++) {
			// Lấy lại danh sách sản phẩm sau mỗi lần loop
			sobep_list = beptu.ProductList(beptu.product_result);
			WebElement sobep = sobep_list.get(i);
			// click bằng javascript
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sobep);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sobep);

			String actual_sobep = beptu.detailInformation(beptu.detail_5bep);
			assertTrue(actual_sobep.equals("05") || actual_sobep.equals("5"), "Số bếp không thoả mãn 5 bếp");
			// Quay lại màn list
			driver.navigate().back();

		}
	}

	// TC12: Chọn Bếp từ (cái này đang có cả dư thừa bếp lọc cả k phải bếp từ, by
	// pass bằng 06 bếp)
	@Test
	public void phanloaitest_BDT() {
		beptu.phanloai();
		List<WebElement> listBDT = driver.findElements(beptu.result_bepDT);
		assertEquals(beptu.resultList_search(beptu.result_bepDT), 6);
		for (WebElement element : listBDT) {
			String text = element.getText().toLowerCase();
			assertTrue(text.contains("điện từ") || text.contains("điện từ")); // sửa assert
		}
	}
//	TC13: Tương tự TC 09

	//TC mua hàng
	@Test
	public void Payment_fail()
	{
		beptu.locHangBT_Kainer();
		List<WebElement> orderList = beptu.ProductList(beptu.product_result);
		assertTrue(orderList.size() > 0, "Không tìm thấy sản phẩm");
		//Click 1 sản phẩm bất kỳ
	    WebElement product = orderList.get(0); // hoặc random
	    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].scrollIntoView(true);", product);
	    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", product);


	}
}

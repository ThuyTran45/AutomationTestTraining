package automation.testsuite;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
		driver = FirefoxDriver(CT_pageURL.BEPANTOAN_URL);
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
			for (int i = 0; i < salePrices.size(); i++) {
				// Lấy text sản phẩm
				String priceText = salePrices.get(i).getText();
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
		// Lấy tất cả sản phẩm hiển thị trên trang web(bao gồm cả < 3000, có text liên
		// hệ)
		List<WebElement> ProductList = beptu.ProductList(beptu.product_result);
		// Set ngưỡng so sánh giá
		BigInteger min = new BigInteger("3000000");
		BigInteger max = new BigInteger("5000000");
		for (WebElement product : ProductList) {
			// List<WebElement> salePrices = beptu.ProductList(beptu.result_giathap);
			List<WebElement> salePrices = product.findElements(beptu.result_muc1);
			for (int i = 0; i < salePrices.size(); i++) {
				// Lấy text sản phẩm
				String priceText = salePrices.get(i).getText();
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
	//TC05,06,07: tương tự TC03,04
	
	//TC08
		@Test
		public void xuatxu_Test() {
			beptu.xuatxu();
			int count = beptu.countVisibleElements(beptu.product_result, 10);
			// assertTrue(count==0 , "Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn");
			 assertEquals(count, 0, "Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn");
		}
}

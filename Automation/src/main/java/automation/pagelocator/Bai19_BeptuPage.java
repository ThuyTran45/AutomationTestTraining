package automation.pagelocator;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.common.CommonBase;

public class Bai19_BeptuPage extends CommonBase {
	private WebDriver driver;
	By menu_beptu = By.xpath("(//a[@href='/danh-muc/bep-tu'])[2]");
	By hang_Kainer = By.xpath("//a[contains(@href,'/danh-muc/bep-tu/kainer')]");
	By xemthem_hang = By.xpath("(//span[text()='Xem thêm'])[1]");
	By hang_Sunhouse = By.xpath("//a[contains(@href,'/danh-muc/bep-tu/sunhouse')]");
	By gia_muc1 = By.xpath("//div[@class='category-menus']/div/span[contains(text(),'< 3.000.000')]");
	By gia_muc2 = By.xpath("//div[@class='category-menus']/div/span[contains(text(),'3.000.000 > 5.000.000')]");
	public By xuatxu_Anh = By.xpath("//span[text()='England']");
	public By xuatxu_Germany = By.xpath("//input[starts-with(@id,'germany')]");
	By xemthem_xuatxu = By.xpath("(//span[text()='Xem thêm'])[2]");
	public By phanloai_BDT = By.xpath("//span[text()='Bếp điện từ']");
	// public By sobep_5bep = By.xpath("//input[contains(@id,'5-bep')]");
	public By sobep_5bep = By.xpath("//span[contains(text(),'5 bếp')]");
	public By muaNgay = By.xpath("(//span[contains(text(),'Mua ngay')])[1]");
	
	//thông tin order
	public By Hoten = By.xpath("//input[@placeholder='Nhập họ và tên']");
	public By sdt = By.xpath("//input[@placeholder='Nhập số điện thoại']");
	public By diachi = By.xpath("//input[@placeholder='Nhập số nhà, tên đường, phường/ xã, quận/huyện, tỉnh/ thành phố']");

	// menu tab result
	By tab_Giamgianhieu = By.xpath("//a[@class='menu-tab' and text()='Giảm giá nhiều']");

	// Expected result for search page
	public By product_result = By.xpath("//div[@class='flex flex-wrap product-list']/a");
	public By result_Kainer = By.xpath("//div[@class='flex flex-wrap product-list']/a/div[3]/div/img[@alt='KAINER']");
	public By result_Sunhouse = By
			.xpath("//div[@class='flex flex-wrap product-list']/a/div[3]/div/img[@alt='Sunhouse']");
	public By result_muc1 = By.xpath("//span[contains(@class,'sale-price')]");
	public By result_muc1_lienHe = By.xpath("//div[contains(@class,'sale-price')]");
	public By result_muc2 = By.xpath("//span[contains(@class,'sale-price')]");
	public By result_muc2_lienHe = By.xpath("//div[contains(@class,'sale-price')]");
	public By result_bepDT = By.xpath("//div[contains(@class,'product-detail')]//span[text()='05' or text()='5']");

	// Expected for detail page
	public By detail_Germany = By.xpath("(//div[contains(@class,'product-information')]//span[text()='Germany'])[1]");
	public By detail_5bep = By.xpath("//h4[contains(text(), 'điện từ') or contains(text(), 'điện từ')]");

	public Bai19_BeptuPage(WebDriver driver) {
		this.driver = driver;
	}

	// TC01:Lọc BT theo hãng khi không click xem thêm
	public void locHangBT_Kainer() {
		click(menu_beptu);
		scrollToElement(hang_Kainer);
		clickByJS(hang_Kainer);

	}

	// TC02: lọc BT theo hãng khi click xem thêm
	public void locHangBT_Sunhouse() {
		click(menu_beptu);
		scrollToElement(xemthem_hang);
		clickByJS(xemthem_hang);
		clickByJS(hang_Sunhouse);
	}

	// TC03: lọc BT theo mức giá <3.000.000
	public void locgiaBT_muc1() {
		click(menu_beptu);
		clickByActions(gia_muc1);
	}
	// TC04: lọc BT theo 3.000.000 <= mức giá < 5.000.000

	public void locgiaBT_muc2() throws InterruptedException {
		click(menu_beptu);
		clickByActions(gia_muc2);
		click(tab_Giamgianhieu);
	}

	// TC08, TC09:click xuất xứ có 2 case : 1 là vd: vào germany có sản phẩm >> vào
	// detail
	// check thông tin sản phẩm. case 2: k có sản phẩm nào
	// Hiển thị text: Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn
	public void xuatxu(By locator) {
		click(menu_beptu);
		scrollToElement_Center(xemthem_xuatxu);
		clickByJS(xemthem_xuatxu);
		WebElement checkbox = findElement_fluent(locator);
		if (!checkbox.isSelected()) {
			clickByActions(locator);
		}
	}

	// TC10: Chọn số bếp
	public void sobep_BT(By locator) {
		click(menu_beptu);
		scrollToElement_Center(sobep_5bep);
		clickByActions(sobep_5bep);
	}

	

	// TC12, 13: Chọn Bếp từ
	public void phanloai() {
		click(menu_beptu);
		scrollToElement_Center(phanloai_BDT);
		clickByActions(phanloai_BDT);
	}
	
	//Mua hàng
	public void muahang_BT() {
		click(muaNgay);
		type(hang_Kainer);
		clickByJS(hang_Kainer);
	}

	// Get số lượng sản phẩm khi lọc một điều kiện
	// Đếm sô lượng phần tử ở màn search
	public int resultList_search(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		List<WebElement> list = wait
				.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
		return list.size();
	}

	// Trả về danh sách WebElement, load DOM
	public List<WebElement> ProductList(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// check list count = 0
	public int countVisibleElements(By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		// Polling: đợi DOM load xong (hoặc timeout)
		wait.pollingEvery(Duration.ofMillis(500)).until(driver -> true); // optional, chỉ để polling DOM

		// Lấy tất cả element hiện tại (có thể rỗng)
		List<WebElement> list = driver.findElements(locator);

		// Chỉ đếm những element visible
		return (int) list.stream().filter(WebElement::isDisplayed).count(); // nếu list rỗng -> trả về 0
	}

	// Check detail information
	public String detailInformation(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
	}
}

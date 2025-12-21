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
	
	//menu tab result
	By tab_Giamgianhieu = By.xpath("//a[@class='menu-tab' and text()='Giảm giá nhiều']");

	// Expected result
	public By product_result = By.xpath("//div[@class='flex flex-wrap product-list']/a");
	public By result_Kainer = By.xpath("//div[@class='flex flex-wrap product-list']/a/div[3]/div/img[@alt='KAINER']");
	public By result_Sunhouse = By
			.xpath("//div[@class='flex flex-wrap product-list']/a/div[3]/div/img[@alt='Sunhouse']");
	public By result_muc1 = By.xpath("//span[contains(@class,'sale-price')]");
	public By result_muc1_lienHe = By.xpath("//div[contains(@class,'sale-price')]");
	public By result_muc2 = By.xpath("//span[contains(@class,'sale-price')]");
	public By result_muc2_lienHe = By.xpath("//div[contains(@class,'sale-price')]");

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
		String firstWindow = driver.getWindowHandle();
        click(tab_Giamgianhieu);
        //Mở ra tab window mới, lấy currentUrl rồi assert
        Set<String> windows = driver.getWindowHandles();
        for(String childWindow : windows)
        {
                if(!childWindow.equals(firstWindow))
                {
                driver.switchTo().window(childWindow);
                Thread.sleep(5000);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 
                String actualUrl = driver.getCurrentUrl();
                System.out.println("acutalUrl: "+ actualUrl);
                assertEquals(actualUrl, "https://bepantoan.vn/danh-muc/bep-tu?price=3000000-5000000&page=1&sort=discount");
                driver.close();
                }
                driver.switchTo().window(firstWindow);
        }
	}
	// TC08:click xuất xứ có 2 case : 1 là vd: vào germany có sản phẩm >> vào detail
	// check thông tin sản phẩm. case 2: k có sản phẩm nào
	// Hiển thị text: Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn
	public void xuatxu() {
		click(menu_beptu);
		scrollToElement(xuatxu_Anh);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('div.p-4.border-b.border-grey-200').style.display='none';");
		 WebElement checkbox = findElement_fluent(xuatxu_Anh);
		    if (!checkbox.isSelected()) {
		    	clickByActions(xuatxu_Anh);
	    }
	}
	
					// Get số lượng sản phẩm khi lọc một điều kiện
	// Đếm sô lượng phần tử ở màn search
	public int resultList_search(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> list = wait
				.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
		return list.size();
	}

	// Trả về danh sách WebElement, load DOM
	public List<WebElement> ProductList(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(product_result));
	}

	public int countVisibleElements(By locator, int timeoutSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

	    // Polling: đợi DOM load xong (hoặc timeout)
	    wait.pollingEvery(Duration.ofMillis(500))
	        .until(driver -> true); // optional, chỉ để polling DOM

	    // Lấy tất cả element hiện tại (có thể rỗng)
	    List<WebElement> list = driver.findElements(locator);

	    // Chỉ đếm những element visible
	    return (int) list.stream()
	                     .filter(WebElement::isDisplayed)
	                     .count(); // nếu list rỗng -> trả về 0
	}
}

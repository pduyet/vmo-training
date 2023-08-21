package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.Duration;

public class Topic10_Wait {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void TC02() throws InterruptedException {
        driver.get("https://www.vntrip.vn/");
        Assert.assertEquals(driver.getTitle(),"Vntrip: Đặt phòng khách sạn, vé máy bay, combo du lịch giá rẻ");

        WebElement clickKhacSan = driver.findElement(By.xpath("//div[@class ='header__navbar']//span[text()='Khách sạn']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class ='header__navbar']//span[text()='Khách sạn']")));
        clickKhacSan.click();

        WebElement enterKhachSan = driver.findElement(By.xpath("//div[@class='inputInline']//input[contains(@placeholder,'Nhập điểm đến, khách sạn')]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inputInline']//input[contains(@placeholder,'Nhập điểm đến, khách sạn')]")));
        enterKhachSan.sendKeys("Jw marriot");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class ='listPlace']")));
        WebElement selectKhacSan = driver.findElement(By.xpath("//ul[@class ='listPlace']//li[@class = 'listPlace__item ']//p[text() ='JW Marriott Hanoi']"));
        selectKhacSan.click();

        WebElement clickTimKiem = driver.findElement(By.xpath("//span[text() = 'Tìm kiếm']"));
        clickTimKiem.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class ='listRoom']")));

        WebElement clickDatPhongDauTien = driver.findElement(By.xpath("(//span[text() ='Phòng Deluxe, 2 Giường Đôi, Nhìn Ra Thành Phố']/ancestor::div[@class ='roomItem']//span[text()='Đặt phòng'])[1]"));
        clickDatPhongDauTien.click();

        WebElement inputHoTen = driver.findElement(By.xpath("//input[@placeholder = 'Họ và tên']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Họ và tên']")));
        inputHoTen.sendKeys("Nguyen Thi Mai Dung");

        WebElement inputSDT = driver.findElement(By.xpath("//input[@placeholder = 'Số điện thoại']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Số điện thoại']")));
        inputSDT.sendKeys("335582330");

        WebElement inputEmail = driver.findElement(By.xpath("//input[@placeholder = 'Email']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Email']")));
        inputEmail.sendKeys("dung@gmail.com");

        WebElement buttonTiepTuc = driver.findElement(By.xpath("//div[@class ='checkoutInfo']//span[text() = 'Tiếp tục']"));
        buttonTiepTuc.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text() = 'THÔNG TIN THANH TOÁN']")));
        WebElement clickThanhToanChuyenKhoan = driver.findElement(By.xpath("//label[@for ='payment_method_bank_transfer_new']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Thanh toán chuyển khoản']")));
        clickThanhToanChuyenKhoan.click();
        WebElement clickTiepTuc = driver.findElement(By.xpath("//span[text() = 'Tiếp tục']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", clickTiepTuc);
        clickTiepTuc.click();

        WebElement errorDisplayed = driver.findElement(By.xpath("//span[text() ='Vui lòng chọn hình thức thanh toán.']"));
        if (errorDisplayed.isDisplayed()) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text() ='Vui lòng chọn hình thức thanh toán.']")));
            clickThanhToanChuyenKhoan.click();
            clickTiepTuc.click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text() = 'HƯỚNG DẪN CHUYỂN KHOẢN']")));
        WebElement buttonToiDaChuyenKhoan = driver.findElement(By.xpath("//span[text() = 'Tôi đã chuyển khoản xong']/parent::button"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Tôi đã chuyển khoản xong']/parent::button")));
        buttonToiDaChuyenKhoan.click();

        WebElement verifyPageXacNhan = driver.findElement(By.xpath("//p[text() ='Giao dịch đang được xác nhận']"));
        verifyPageXacNhan.isDisplayed();

        WebElement clickQuayVe = driver.findElement(By.xpath("//span[text() = 'Quay về trang chủ']"));
        clickQuayVe.click();

        WebElement verifyTrangChu = driver.findElement(By.xpath("//span[text() ='Trang chủ']"));
        verifyTrangChu.isDisplayed();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

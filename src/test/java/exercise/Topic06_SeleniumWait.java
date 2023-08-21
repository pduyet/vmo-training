package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic06_SeleniumWait {
    WebDriver driver;
    WebDriverWait wait;
    String valueDestination = "Jw marriot";
    String fullName = "Đỗ Hậu";
    String numberPhone = "0345678332";
    String email = "haudt@vmodev.com";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC02_VerifyVnTrip() {
        driver.get("https://www.vntrip.vn/");
        Assert.assertEquals(driver.getTitle(), "Vntrip: Đặt phòng khách sạn, vé máy bay, combo du lịch giá rẻ");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.vntrip.vn/");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/khach-san']//span[contains(text(),'Khách sạn')]")));
        driver.findElement(By.xpath("//a[@href='/khach-san']//span[contains(text(),'Khách sạn')]")).click();

        WebElement inputDestination = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ant-input ant-input-lg']")));
        inputDestination.sendKeys(valueDestination);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='listPlace']")));
        driver.findElement(By.xpath("//p[normalize-space()='JW Marriott Hanoi']")).click();
        driver.findElement(By.xpath("//div[@class='vntSearch__btn']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='listRoom']")));
        driver.findElement(By.xpath("(//div[@class='roomItem__btn']//button)[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Họ và tên']")));
        driver.findElement(By.xpath("//input[@placeholder='Họ và tên']")).sendKeys(fullName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Số điện thoại']")));
        driver.findElement(By.xpath("//input[@placeholder='Số điện thoại']")).sendKeys(numberPhone);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Email']")));
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);

        driver.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary btn_orange']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='THÔNG TIN THANH TOÁN']")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[@for='payment_method_bank_transfer_new']")));
        driver.findElement(By.xpath("//label[@for='payment_method_bank_transfer_new']")).click();

        driver.findElement(By.xpath("//span[contains(text(),'Tiếp tục')]/parent::button[.='Tiếp tục']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'HƯỚNG DẪN CHUYỂN KHOẢN')]")));
        driver.findElement(By.xpath("//div[@class='checkoutLayout__btn']//button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='checkoutState__title']//p")).getText(),"GIAO DỊCH ĐANG ĐƯỢC XÁC NHẬN");

        driver.findElement(By.xpath("//span[contains(text(),'Quay về trang chủ')]/parent::a")).click();
        Assert.assertEquals(driver.getTitle(), "Vntrip: Đặt phòng khách sạn, vé máy bay, combo du lịch giá rẻ");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.vntrip.vn/");
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Trang chủ']")).getText(),"Trang chủ");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

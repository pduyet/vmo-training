package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic06_Wait {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC02() throws InterruptedException {
        String url = "https://www.vntrip.vn/";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        Assert.assertEquals(s, url);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nav[@class='navbar']/descendant::span[text()='Khách sạn']")));
        driver.findElement(By.xpath("//nav[@class='navbar']/descendant::span[text()='Khách sạn']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ant-input-suffix']/preceding-sibling::input")));
        driver.findElement(By.xpath("//span[@class='ant-input-suffix']/preceding-sibling::input")).sendKeys("Jw marriot");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='suggestDefault__title']")));
        driver.findElement(By.xpath("//p[text()='JW Marriott Hanoi']")).click();
        driver.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary ant-btn-lg w100']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='listRoom']")));
        driver.findElement(By.xpath("//span[text()='Phòng Deluxe, 2 Giường Đôi, Nhìn Ra Thành Phố']/following::span[text()='Đặt phòng'][1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='checkoutInfo__form']")));
        driver.findElement(By.xpath("//input[@placeholder='Họ và tên']")).sendKeys("Ngo Anh Thai");
        driver.findElement(By.xpath("//input[@placeholder='Số điện thoại']")).sendKeys("392921517");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ngoanhthai@gmail.com");
        driver.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary btn_orange']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='THÔNG TIN THANH TOÁN']")).isDisplayed());
        driver.findElement(By.xpath("//label[@for='payment_method_bank_transfer_new']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-spin-container ant-spin-blur']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin-container ant-spin-blur']")));
        driver.findElement(By.xpath("//button[@class='btn btn_orange']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ant-btn ant-btn-primary']")));
        driver.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Giao dịch đang được xác nhận']")).isDisplayed());
        driver.findElement(By.xpath("//a[@class='btn btn_orange']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Trang chủ']")).isDisplayed());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

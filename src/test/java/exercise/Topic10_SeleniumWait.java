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
import java.util.List;
import java.util.Set;

public class Topic10_SeleniumWait {
    WebDriver driver;
    WebDriverWait wait;
    String destination = "Jw marriot";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC02_VerifyVnTripByUsingWait() {
        driver.get("https://www.vntrip.vn/");
        Assert.assertEquals(driver.getTitle(), "Vntrip: Đặt phòng khách sạn, vé máy bay, combo du lịch giá rẻ");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.vntrip.vn/");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nav[@class='navbar']//ul//li[2]")));
        driver.findElement(By.xpath("//nav[@class='navbar']//ul//li[2]")).click();

        WebElement destinationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//input[@class='ant-input ant-input-lg']")));
        destinationInput.sendKeys(destination);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='listPlace']")));
        driver.findElement(By.xpath("//p[contains(text(),'JW Marriott Hanoi')]")).click();
        driver.findElement(By.xpath("//div[@class='vntSearch__btn']//button")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,600)");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='listRoom']")));
        driver.findElement(By.xpath("(//div[@class='roomItem__btn']//button)[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Anh']")));
        String contrations = "//span[@title='Anh']";
        String contrationOptions = "//div[@class='ant-select-item-option-content']";
        String optionValue = "Chị";
        selectOption(contrations, contrationOptions, optionValue);
        driver.findElement(By.xpath("//input[@placeholder='Họ và tên']")).sendKeys("scarlett johansson");
        driver.findElement(By.xpath("//input[@placeholder='Số điện thoại']")).sendKeys("963882787");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//div[@class='checkoutLayout__btn']//button")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='checkoutPayment']")));
        List<WebElement> finishedSteps = driver.findElements(By.xpath("//div[@class='vntStep__item active']"));
        Assert.assertEquals(finishedSteps.size(), 2);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin ant-spin-spinning']")));
        WebElement bankTranfer = driver.findElement(By.xpath("//label[@for='payment_method_bank_transfer_new']"));
        js.executeScript("arguments[0].scrollIntoView(true);", bankTranfer);
        bankTranfer.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin ant-spin-spinning']")));
        WebElement btnContinue = driver.findElement(By.xpath("//div[@class='checkoutLayout__btn']//button"));
        js.executeScript("arguments[0].scrollIntoView(true);", btnContinue);
        btnContinue.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='HƯỚNG DẪN CHUYỂN KHOẢN']")));
        driver.findElement(By.xpath("//div[@class='checkoutLayout__btn']//button")).click();

        WebElement txtTransactionConfirm = driver.findElement(By.xpath("//div[@class='checkoutState__title']//p"));
        WebElement txtTransactionCode = driver.findElement(By.xpath("//div[@class='checkoutState__code']//p[1]"));
        Assert.assertEquals(txtTransactionConfirm.getText(), "GIAO DỊCH ĐANG ĐƯỢC XÁC NHẬN");
        Assert.assertEquals(txtTransactionCode.getText(), "Mã đơn hàng");

        driver.findElement(By.xpath("//div[@class='checkoutState__btn']//a")).click();
        Assert.assertEquals(driver.getTitle(), "Vntrip: Đặt phòng khách sạn, vé máy bay, combo du lịch giá rẻ");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.vntrip.vn/");
    }

    public void selectOption(String parentXpath, String childXpath, String selectedValue) {
        driver.findElement(By.xpath(parentXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(childXpath)));
        List<WebElement> allOptions = driver.findElements(By.xpath(childXpath));
        for (WebElement option : allOptions) {
            if (option.getText().equals(selectedValue)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

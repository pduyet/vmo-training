package Topic06_SeleniumWait;

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
import java.util.concurrent.TimeUnit;

public class Topic06_B01_Homework {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @Test
    public void tc02() throws InterruptedException {
        String url = "https://www.vntrip.vn/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='navbar']//span[contains(text(),'Khách sạn')]/parent::a"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement destinationInput = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@class='ant-input ant-input-lg']"))));
        destinationInput.click();
        destinationInput.sendKeys("Jw marriot");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='listPlace']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'JW Marriott Hanoi')]/ancestor::button"))).click();
        driver.findElement(By.xpath("//div[@class='vntSearch__btn']/button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='listRoom']")));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[contains(text(),'Phòng Deluxe, 2 Giường Đôi, Nhìn Ra Thành Phố')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'Phòng Deluxe, 2 Giường Đôi, Nhìn Ra Thành Phố')]/ancestor::div[@class='roomItem__header ']/following-sibling::div[@class='roomItem__body']//div[@class='roomItem__btn'])[1]"))).click();

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Họ và tên']")));
        nameField.click();
        nameField.sendKeys("Uyen Nguyen");

        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Số điện thoại']")));
        phoneField.click();
        phoneField.sendKeys("0334797119");

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Email']")));
        emailField.click();
        emailField.sendKeys("uyen.nguyen@gmail.com");
        driver.findElement(By.xpath("//span[text()='Tiếp tục']/parent::button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='THÔNG TIN THANH TOÁN']")).getText(),"THÔNG TIN THANH TOÁN");
        JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
        jsExecutor2.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//label[@for='payment_method_bank_transfer_new']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='payment_method_bank_transfer_new']"))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin ant-spin-spinning']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Tiếp tục']/parent::button"))).click();
        driver.findElement(By.xpath("//span[text()='Tiếp tục']/parent::button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='HƯỚNG DẪN CHUYỂN KHOẢN']")));
        driver.findElement(By.xpath("//span[text()='Tôi đã chuyển khoản xong']/parent::button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Giao dịch đang được xác nhận']")).getText(),"Giao dịch đang được xác nhận");
        driver.findElement(By.xpath("//span[text()='Quay về trang chủ']/parent::a")).click();
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

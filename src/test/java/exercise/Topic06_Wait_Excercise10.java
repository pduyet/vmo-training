package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic06_Wait_Excercise10 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() throws AWTException {
        // bai nay c dung edge vi chay chrome no bao loi do Chrome update version 116 ma chrome driver chi support 114
        //  buoi sau nho Duyen chi moi nguoi loi nay
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test
    public void TC02_VNTrip() throws InterruptedException {
        String url = "https://www.vntrip.vn/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Khách sạn']/parent::a"))).click();
        WebElement menuKSan = driver.findElement(By.xpath("//nav//span[text()='Khách sạn']/parent::a/ancestor::li"));
        Assert.assertTrue(menuKSan.getAttribute("class").equals("active"));

        By locatorInputDest = By.xpath("//div[@class='inputInline']//input[contains(@placeholder,'Nhập điểm đến, khách sạn')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorInputDest)).sendKeys("Jw marriot");
        By locatorListPlace = By.xpath("//ul[@class='listPlace']//li");
        Assert.assertTrue(isElementDisplayed(locatorListPlace, 10));

        List<WebElement> elementList = driver.findElements(locatorListPlace);
        elementList.get(0).click();
        driver.findElement(By.xpath("//div[@class='vntSearch__btn']")).click();
        By locatorListRoom = By.xpath("//div[@class='listRoom__item'][2]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorListRoom));
        Assert.assertTrue(driver.findElement(locatorListRoom).isDisplayed());
        driver.findElement(By.xpath("(//div[@class='listRoom__item'][2]//div[@class='roomItem__body']//button)[1]")).click();

//        WebElement selectDanhXung = driver.findElement
//                (By.xpath("//label[text()='Danh xưng']/parent::div/following-sibling::div//input"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].setAttribute('aria-activedescendant','rc_select_0_list_2');", selectDanhXung);

        System.out.println(driver.findElement
                (By.xpath("(//label[text()='Danh xưng']/parent::div/following-sibling::div//span)[2]")).getAttribute("title"));
        String name = "Chung Nguyen";
        String phoneNumber = "0369494083";
        String email = "chungnt@gmal.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Họ và tên']")))
                .sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Số điện thoại']")))
                .sendKeys(phoneNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']")))
                .sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Tiếp tục']/parent::button")))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Chọn hình thức thanh toán']")));
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Chọn hình thức thanh toán']")).isDisplayed());
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin ant-spin-spinning']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='payment_method_bank_transfer_new']/parent::div"))).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin ant-spin-spinning']")));
        driver.findElement(By.xpath("//span[text()='Tiếp tục']/parent::button")).click();

        By locatorFormInforPayment  = By.xpath("//div[@class='checkoutTransfer__info']//span");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFormInforPayment));
        WebElement total = driver.findElement(locatorFormInforPayment);
        Assert.assertEquals(total.getText(), "Vui lòng chuyển khoản theo thông tin");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin ant-spin-spinning']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Tôi đã chuyển khoản xong']"))).click();

        By locatorAfterPayment = By.xpath("//p[text()='Giao dịch đang được xác nhận']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorAfterPayment));
        Assert.assertTrue(driver.findElement(locatorAfterPayment).isDisplayed());
        driver.findElement(By.xpath("//span[text()='Quay về trang chủ']/parent::a")).click();

        By locatorNavar = By.xpath("//div[@class='header__navbar']");
        wait.until(ExpectedConditions.presenceOfElementLocated(locatorNavar));
        Assert.assertTrue(driver.findElement(locatorNavar).isDisplayed());

    }

    /*
    Return false neu ko hien thi, return true neu co hien thi
     */
    public boolean isElementDisplayed(By by, int timeout) {
        List<WebElement> elementList = driver.findElements(by);
        if (elementList.isEmpty()) {
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        System.out.println("list element size: " + elementList.size());
        return true;
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

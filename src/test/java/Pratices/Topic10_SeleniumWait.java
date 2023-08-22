package Pratices;

import java.time.Duration;
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

public class Topic10_SeleniumWait {

  WebDriver driver;
  WebDriverWait wait;
  String name = "Thuy Hien";
  String phone = "345688994";
  String email = "testerweb@vntrip.vn";

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
  }

  @Test
  public void TC02_VerifyBookingHotel() {
    driver.get("https://www.vntrip.vn/");
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.vntrip.vn/");

    waitForElementClickable("//header[@class='header']//span[text()='Khách sạn']//parent::a").click();
    waitForElementVisible("//input[@class='ant-input ant-input-lg']").sendKeys("Jw marriot");
    waitForElementVisible("//ul[@class='listPlace']");

    driver.findElement(getByXpath("//p[text()='JW Marriott Hanoi']")).click();
    driver.findElement(getByXpath("//span[text() = 'Tìm kiếm']")).click();

    waitForElementVisible("//div[@class='listRoom']");
    driver.findElement(getByXpath("(//span[contains(text(),'2 Giường Đôi, Nhìn Ra Thành Phố')]/ancestor::div[@class ='roomItem']//span[text()='Đặt phòng'])[1]")).click();
    waitForElementVisible("//input[@placeholder='Họ và tên']").sendKeys(name);
    waitForElementVisible("//input[@placeholder='Số điện thoại']").sendKeys(phone);
    waitForElementVisible("//input[@placeholder='Email']").sendKeys(email);
    driver.findElement(getByXpath("//button[@class='ant-btn ant-btn-primary btn_orange']")).click();

    waitForElementVisible("//div[@class='bookingUser']");
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    Assert.assertTrue(driver.findElement(getByXpath("//p[text()='Người đặt phòng']//following-sibling::p")).getText().contains(name));
    Assert.assertTrue(driver.findElement(getByXpath("(//p[text()='Số điện thoại']//following-sibling::p)[1]")).getText().contains(phone));
    Assert.assertTrue(driver.findElement(getByXpath("//p[text()='Email']//following-sibling::p")).getText().contains(email));

    waitForElementVisible("//label[@for='payment_method_bank_transfer_new']").click();
    waitForElementVisible("//div[@class='ant-spin ant-spin-spinning']");
    waitForElementInVisible("//div[@class='ant-spin ant-spin-spinning']");
    driver.findElement(getByXpath("//button[@class='btn btn_orange']")).click();

    waitForElementVisible("//h2[text()='HƯỚNG DẪN CHUYỂN KHOẢN']");
    waitForElementClickable("//button[@class='ant-btn ant-btn-primary']").click();

    waitForElementPresence("//div[@class='checkoutState__title']/p");
    Assert.assertTrue(driver.findElement(getByXpath("//p[text()='Giao dịch đang được xác nhận']")).isDisplayed());
    driver.findElement(getByXpath("//a[@class='btn btn_orange']")).click();
    waitForElementVisible("//header[@class='header']//span[text()='Trang chủ']");
    Assert.assertTrue(driver.findElement(getByXpath("//li[@class='active']//span[text()='Trang chủ']")).isDisplayed());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
  public By getByXpath(String Locator) {
    return By.xpath(Locator);
  }
  public WebElement waitForElementVisible(String locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated((getByXpath(locator))));
  }
  public void waitForElementInVisible(String locator) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
  }
  public WebElement waitForElementClickable(String locator) {
    return wait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
  }
  public void waitForElementPresence(String locator) {
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
  }
}

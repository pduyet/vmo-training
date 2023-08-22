package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Topic07_Exercise11_TestNG {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");


    }

    @Test()
    public void TC01_LoginSuccess() throws InterruptedException, Exception{
        Robot rb = new Robot();
        Thread.sleep(5000);
        WebElement Username = driver.findElement(By.name("username"));
        Username.sendKeys("Admin");
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys("admin123");
        Thread.sleep(5000);
        passWord.click();
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_DELETE);
        Username.click();
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_DELETE);
        Thread.sleep(5000);
    }

    @Test()
    @Parameters({"Username", "Password"})
    public void TC02_LoginUnsuccessfullyWithParams(String Username, String Password) throws Exception {
        Robot rb2 = new Robot();
        WebElement userName = driver.findElement(By.name("username"));
        userName.sendKeys(Username);
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys(Password);
        Thread.sleep(5000);
        passWord.click();
        rb2.keyPress(KeyEvent.VK_CONTROL);
        rb2.keyPress(KeyEvent.VK_A);
        rb2.keyRelease(KeyEvent.VK_CONTROL);
        rb2.keyPress(KeyEvent.VK_DELETE);
        userName.click();
        rb2.keyPress(KeyEvent.VK_CONTROL);
        rb2.keyPress(KeyEvent.VK_A);
        rb2.keyRelease(KeyEvent.VK_CONTROL);
        rb2.keyPress(KeyEvent.VK_DELETE);
        Thread.sleep(5000);
    }

    @Test(dataProvider = "Login")
    public void TC03_LoginUnsuccessfullyWithDataProvider(String Username, String Password) throws InterruptedException {
        WebElement userName = driver.findElement(By.name("username"));
        userName.sendKeys(Username);
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys(Password);
    }

    @DataProvider(name = "Login")
    public Object[][] dataLogin() {
        return new Object[][]
                {
                        {"", ""}
                };
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

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
    }

    @Test()
    public void TC01_LoginSuccess() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        Thread.sleep(5000);
        WebElement Username = driver.findElement(By.name("username"));
        Username.sendKeys("Admin");
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys("admin123");
    }

    @Test()
    @Parameters({"Username", "Password"})
    public void TC02_LoginUnsuccessfullyWithParams(String Username, String Password) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        WebElement userName = driver.findElement(By.name("username"));
        userName.sendKeys(Username);
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys(Password);
    }

    @Test(dataProvider = "Login")
    public void TC03_LoginUnsuccessfullyWithDataProvider(String Username, String Password) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        WebElement userName = driver.findElement(By.name("username"));
        userName.sendKeys(Username);
        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys(Password);
    }

    @DataProvider(name = "Login")
    public Object[][] dataLogin() {
        return new Object[][]
                {
                        {"maidung", "India"}
                };
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

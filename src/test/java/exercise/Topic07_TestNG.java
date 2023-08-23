package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Topic07_TestNG {
    WebDriver driver;
    WebDriverWait wait;
    WebElement pass;
    WebElement user;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        pass = driver.findElement(By.xpath("//input[@name='password']"));
        user = driver.findElement(By.xpath("//input[@name='username']"));
    }
    @Test
    public void HW21() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        Thread.sleep(2000);
    }

    @Parameters({"Thai1","ngoanhthai1"})
    @Test
    public void HW22(@Optional("Thai1") String username, @Optional("ngoanhthai1") String pw) throws InterruptedException,AWTException {
        while (!pass.getAttribute("value").equals("")) {
             pass.sendKeys(Keys.BACK_SPACE);
        }
        while (!user.getAttribute("value").equals("")) {
            user.sendKeys(Keys.BACK_SPACE);
        }
        driver.findElement(By.xpath("//input[@name='username']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
        Thread.sleep(2000);
    }
    @Test(dataProvider = "SearchProvider")
    public void HW23(String username, String pw) throws InterruptedException {
        while (!pass.getAttribute("value").equals("")) {
            pass.sendKeys(Keys.BACK_SPACE);
        }
        while (!user.getAttribute("value").equals("")) {
            user.sendKeys(Keys.BACK_SPACE);
        }
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
        Thread.sleep(2000);
    }
    @DataProvider(name = "SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        {"Thai2","ngoanhthai2"}
                };
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

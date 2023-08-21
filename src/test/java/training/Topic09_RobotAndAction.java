package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Topic09_RobotAndAction {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void VerifyInputByUsingAction() throws AWTException, InterruptedException {
        driver.get("https://www.google.com/");

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_B);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_D);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_ENTER);
    }
    @Test
    public void VerifyInputByAction() {
        driver.get("https://www.saucedemo.com/");
        Actions action = new Actions(driver);
        WebElement userName = driver.findElement(By.id("user-name"));
        action.sendKeys(userName,"standard_user").build().perform();
        WebElement pass = driver.findElement(By.id("password"));
        action.sendKeys(pass,"secret_sauce").build().perform();

        WebElement btnLogin = driver.findElement(By.id("login-button"));
        action.click(btnLogin).build().perform();

    }

    @Test
    public void VerifyJS() throws InterruptedException {
        driver.get("https://kenh14.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        WebElement first = driver.findElement(By.xpath("//div[@class='klwfn-left fl']"));
        js.executeScript("arguments[0].scrollIntoView(true);", first);
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

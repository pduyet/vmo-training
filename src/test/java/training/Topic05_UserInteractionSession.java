package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Topic05_UserInteractionSession {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void RobotFrameWork() throws AWTException {
        Robot rb = new Robot();
        driver.get("https://www.google.com/");
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_B);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_C);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_ENTER);

    }
    @Test
    public void Actions() {
        Actions actions = new Actions(driver);
        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        actions.click(username).sendKeys("standard_user").build().perform();
        actions.click(password).sendKeys("secret_sauce").build().perform();
        WebElement btnLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        actions.click(btnLogin).build().perform();

    }
    @Test
    public void JS() {
        driver.get("https://kenh14.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("(//div[@class='klwfn-left fl'])[1]")));

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}


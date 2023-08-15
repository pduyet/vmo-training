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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class UserInteraction {

    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void TC01_demoGoogle() throws AWTException {

        driver.get("https://www.google.com/");
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_B);
        rb.keyRelease(KeyEvent.VK_B);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_C);
        rb.keyRelease(KeyEvent.VK_C);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_ENTER);
    }
    @Test
    public void TC02_actionClass(){
        driver.get("https://www.saucedemo.com/");
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//input[@name='user-name']"))).sendKeys("standard_user").build().perform();
        actions.click(driver.findElement(By.xpath("//input[@name='password']"))).sendKeys("secret_sauce").build().perform();
        actions.click(driver.findElement(By.xpath("//input[@id='login-button']"))).build().perform();
    }

    @Test
    public void TC03_demoExecute() throws InterruptedException {
        driver.get("https://kenh14.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(9000);
        WebElement article = driver.findElement(By.xpath("(//div[@class='kenh14-body-wrapper']//a)[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);",article);
        article.click();

    }
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
}

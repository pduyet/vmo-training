package Topic05;

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

public class Topic05_B01_Example {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void ex1() throws Exception {
        Robot rb = new Robot();
        driver.get("https://www.google.com");
        rb.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_B);
        Thread.sleep(1000);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_C);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_D);
        Thread.sleep(1000);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }
    @Test
    public void ex2(){
        Actions action = new Actions(driver);
        driver.get("https://www.saucedemo.com/");
        action.sendKeys(driver.findElement(By.xpath("//input[@id='user-name']")),"standard_user").build().perform();
        action.sendKeys(driver.findElement(By.xpath("//input[@id='password']")),"secret_sauce").build().perform();
        action.click(driver.findElement(By.xpath("//input[@type='submit']"))).build().perform();
    }
    @Test
    public void ex3() throws InterruptedException {
        driver.get("https://kenh14.vn/");
        WebElement firstArticle = driver.findElement(By.xpath("//h2[@class='klwfnl-title inited']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",firstArticle);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

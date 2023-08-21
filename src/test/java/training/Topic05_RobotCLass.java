package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Topic05_RobotCLass {
    WebDriver driver;

    @Test
    public void ThucHanhRobot() throws Exception {
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_B);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_C);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_ENTER);
        driver.quit();
    }
    @Test
    public void ThucHanhActions(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//input[@id='user-name']"))).sendKeys("standard_user").build().perform();
        actions.click(driver.findElement(By.xpath("//input[@id='password']"))).sendKeys("secret_sauce").build().perform();
        actions.click(driver.findElement(By.xpath("//input[@id='login-button']"))).perform();
        }
    @Test
    public void ThucHanhJavaScript(){
        driver = new ChromeDriver();
        driver.get("https://kenh14.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//div[@class='klwfn-left fl']")));
    }
}

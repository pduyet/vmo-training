package Training;

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

public class Topic05_UserInteraction {
    WebDriver driver;
    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test()
    public void robotClass() throws Exception{
        Robot rb = new Robot();
        driver.get("https://www.google.com.vn/");
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_B);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_C);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_ENTER);
    }

    @Test()
    public void action() throws Exception, InterruptedException{
        Actions action = new Actions(driver);
        driver.get("https://www.saucedemo.com/");
        WebElement userName = driver.findElement(By.xpath("//input[@id ='user-name']"));
        userName.click();
        action.sendKeys(userName,"standard_user");
        WebElement passWord = driver.findElement(By.xpath("//input[@id ='password']"));
        userName.click();
        action.sendKeys(passWord,"standard_user");
        WebElement login = driver.findElement(By.xpath("//input[@id ='login-button']"));
        login.click();
    }

    @Test()
    public void Executor() throws Exception, InterruptedException{
        driver.get("https://kenh14.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        WebElement baiDautien = driver.findElement(By.xpath("//h2[@class ='klwfnl-title inited']/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", baiDautien);
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

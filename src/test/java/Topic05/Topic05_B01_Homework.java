package Topic05;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Topic05_B01_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download",true);
        options.setExperimentalOption("prefs",prefs);
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void tc1() throws Exception {
        String url = "https://spreadsheetpage.com/accounting/account-receivable/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//a[@rel='noindex nofollow']")));
        driver.findElement(By.xpath("//a[@rel='noindex nofollow']")).click();
        Thread.sleep(5000);
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_D);
        Thread.sleep(2000);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_O);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_W);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_N);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_L);
        Thread.sleep(2000);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_O);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_A);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_D);
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_ENTER);
    }
    @Test
    public void tc2_1() throws InterruptedException {
        String url = "https://demoqa.com/droppable";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
        WebElement sourceLocator = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement destinationLocator = driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceLocator,destinationLocator).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droppable']")).getText(),"Dropped!");
    }
    @Test
    public void tc2_2() throws InterruptedException {
        String url = "https://demoqa.com/droppable";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
        WebElement sourceLocator = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement destinationLocator = driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions action = new Actions(driver);
        action.clickAndHold(sourceLocator).moveToElement(destinationLocator).release(sourceLocator).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droppable']")).getText(),"Dropped!");
    }
    @Test
    public void tc3(){
        String url = "https://demoqa.com/text-box";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='userName']")));
        jsExecutor.executeScript("arguments[0].value = 'Uyen Nguyen';", driver.findElement(By.xpath("//input[@id='userName']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='userEmail']")));
        jsExecutor.executeScript("arguments[0].value = 'uyen.nguyen06102000@gmail.com';", driver.findElement(By.xpath("//input[@id='userEmail']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//textarea[@id='currentAddress']")));
        jsExecutor.executeScript("arguments[0].value = '40 trung kinh';", driver.findElement(By.xpath("//textarea[@id='currentAddress']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//textarea[@id='permanentAddress']")));
        jsExecutor.executeScript("arguments[0].value = '18 ton that thuyet';", driver.findElement(By.xpath("//textarea[@id='permanentAddress']")));
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='userName']")).getAttribute("value"),"Uyen Nguyen");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='userEmail']")).getAttribute("value"),"uyen.nguyen06102000@gmail.com");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='currentAddress']")).getAttribute("value"),"40 trung kinh");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='permanentAddress']")).getAttribute("value"),"18 ton that thuyet");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

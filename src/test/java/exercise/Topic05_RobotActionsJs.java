package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Topic05_RobotActionsJs {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void TC01() throws AWTException {
        String url = "https://spreadsheetpage.com/accounting/account-receivable/";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        Assert.assertEquals(s,url);
        Robot rb = new Robot();
        driver.findElement(By.xpath("//a[@rel='noindex nofollow']")).click();
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_O);
        rb.keyPress(KeyEvent.VK_W);
        rb.keyPress(KeyEvent.VK_N);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_L);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyRelease(KeyEvent.VK_O);
        rb.keyRelease(KeyEvent.VK_A);
        rb.keyRelease(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }
    @Test
    public void TC021() throws InterruptedException {
        String url = "https://demoqa.com/droppable";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        Assert.assertEquals(s,url);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(By.xpath("//div[@id='draggable']")), driver.findElement(By.xpath("//div[@id='droppableExample-tabpane-simple']/descendant::div[@id='droppable']")) ).build().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Dropped!']")).isDisplayed());
    }
    @Test
    public void TC022(){
        String url = "https://demoqa.com/droppable";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        Assert.assertEquals(s,url);
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.xpath("//div[@id='draggable']"))).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//div[@id='droppableExample-tabpane-simple']/descendant::div[@id='droppable']"))).build().perform();
        actions.release().build().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Dropped!']")).isDisplayed());
    }
    @Test
    public void TC03() {
        String url = "https://demoqa.com/text-box";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        Assert.assertEquals(s,url);
        js.executeScript("arguments[0].click;",driver.findElement(By.xpath("//input[@placeholder='Full Name']")));
        js.executeScript("arguments[0].value=arguments[1];",driver.findElement(By.xpath("//input[@placeholder='Full Name']")),"Ngo Anh Thai");
        js.executeScript("arguments[0].click;",driver.findElement(By.xpath("//input[@placeholder='name@example.com']")));
        js.executeScript("arguments[0].value=arguments[1];",driver.findElement(By.xpath("//input[@placeholder='name@example.com']")),"ngoanhthai100899@gmail.com");
        js.executeScript("arguments[0].click;",driver.findElement(By.xpath("//textarea[@placeholder='Current Address']")));
        js.executeScript("arguments[0].value=arguments[1];",driver.findElement(By.xpath("//textarea[@placeholder='Current Address']")),"Dinh Cong");
        js.executeScript("arguments[0].click;",driver.findElement(By.xpath("//textarea[@id='permanentAddress']")));
        js.executeScript("arguments[0].value=arguments[1];",driver.findElement(By.xpath("//textarea[@id='permanentAddress']")),"Ha Noi");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Full Name']")).getAttribute("value"),"Ngo Anh Thai");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='name@example.com']")).getAttribute("value"),"ngoanhthai100899@gmail.com");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder='Current Address']")).getAttribute("value"),"Dinh Cong");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='permanentAddress']")).getAttribute("value"),"Ha Noi");
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Topic05_InteractionsSession {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.prompt_for_download", true);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Test
    public void TC01_UsingRobotClass() throws AWTException, InterruptedException {
        Robot rb = new Robot();
        driver.get("https://spreadsheetpage.com/accounting/account-receivable/");
        Assert.assertEquals(driver.getTitle(),"Account Receivable Excel Template » The Spreadsheet Page");
        Assert.assertEquals(driver.getCurrentUrl(),"https://spreadsheetpage.com/accounting/account-receivable/");

        driver.findElement(By.xpath("//a[normalize-space()='Download this template for free']")).click();
        Thread.sleep(3000);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_O);
        rb.keyPress(KeyEvent.VK_W);
        rb.keyPress(KeyEvent.VK_N);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_L);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_O);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void TC02_UsingActionClass() {
        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/droppable");

        WebElement draggable = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement droppable = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));

        actions.dragAndDrop(draggable, droppable).build().perform();
        Assert.assertEquals(droppable.getText(), "Dropped!");
    }

    @Test
    public void TC03_UsingActionClass() {
        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/droppable");

        WebElement draggable = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement droppable = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));

        actions.clickAndHold(draggable).moveToElement(droppable).release().build().perform();
        Assert.assertEquals(droppable.getText(), "Dropped!");
    }

    @Test
    public void TC04_UsingJavascriptExecutor() throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/text-box");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/text-box");

        String fullName = "Haudt";
        String email = "haudt@vmodev.com";
        String currentAddress = "Thanh Tri";
        String permanentAddress = "Ha Noi";

        WebElement inputFullname = driver.findElement(By.xpath("//input[@id='userName']"));
        jsExecutor.executeScript("arguments[0].value = arguments[1];", inputFullname, fullName);

        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        jsExecutor.executeScript("arguments[0].value = arguments[1];", inputEmail, email);

        WebElement inputCurrentAdd = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        jsExecutor.executeScript("arguments[0].value = arguments[1];", inputCurrentAdd, currentAddress);

        WebElement inputAdd = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        jsExecutor.executeScript("arguments[0].value = arguments[1];", inputAdd, permanentAddress);

        jsExecutor.executeScript("document.getElementById('submit').click();");

        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='name']")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='email']")).getText().contains(email));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='currentAddress']")).getText().contains(currentAddress));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText().contains(permanentAddress));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

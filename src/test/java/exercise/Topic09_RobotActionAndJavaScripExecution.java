package exercise;

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

public class Topic09_RobotActionAndJavaScripExecution {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions opt = new ChromeOptions();
//        opt.addArguments("--incognito");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(ChromeOptions.CAPABILITY,opt);
//        opt.merge(capabilities);
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download",true);
        opt.setExperimentalOption("prefs",prefs);
        driver = new ChromeDriver(opt);
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_VerifyRenameFileUsingRobot() throws AWTException, InterruptedException {
        driver.get("https://spreadsheetpage.com/accounting/account-receivable/");
        Assert.assertEquals(driver.getTitle(),"Account Receivable Excel Template » The Spreadsheet Page");
        Assert.assertEquals(driver.getCurrentUrl(),"https://spreadsheetpage.com/accounting/account-receivable/");

        WebElement bntDownload = driver.findElement(By.xpath("//a[contains(text(),'Download this template for free')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", bntDownload);
        bntDownload.click();

        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_O);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_U);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_N);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_E);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_M);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_P);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_L);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_E);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    @Test
    public void TC021_VerifyDropAndDragByUsingAction(){
        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/droppable");

        Actions action = new Actions(driver);
        WebElement dragMe = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement dropHere = driver.findElement(By.xpath("//div[@id='droppable']"));
        action.dragAndDrop(dragMe,dropHere).build().perform();

        Assert.assertEquals(driver.findElement(By.xpath("(//div[@id='droppable']//p)[1]")).getText(),"Dropped!");
    }

    @Test
    public void TC022_VerifyDropAndDragByUsingAction(){
        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/droppable");

        Actions action = new Actions(driver);
        WebElement dragMe = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement dropHere = driver.findElement(By.xpath("//div[@id='droppable']"));
        action.clickAndHold(dragMe).build().perform();
        action.moveToElement(dropHere).build().perform();
        action.release(dropHere).build().perform();

        Assert.assertEquals(driver.findElement(By.xpath("(//div[@id='droppable']//p)[1]")).getText(),"Dropped!");
    }

    @Test
    public void TC03_VerifyTextboxUsingJavascriptExecutor(){
        driver.get("https://demoqa.com/text-box");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/text-box");

        WebElement useName = driver.findElement(By.xpath("//input[@id='userName']"));
        WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        WebElement bntSubmit = driver.findElement(By.xpath("//button[@id='submit']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", useName, "linhnguyen");
        js.executeScript("arguments[0].value = arguments[1];", email, "abc@gmail.com");
        js.executeScript("arguments[0].value = arguments[1];", currentAddress, "hanoi\n vietnam");
        js.executeScript("arguments[0].value = arguments[1];", permanentAddress, "hanoi\n vietnam");
        js.executeScript("arguments[0].scrollIntoView(true);", bntSubmit);
        js.executeScript("arguments[0].click();", bntSubmit);

        String lblName = driver.findElement(By.xpath("//p[@id='name']")).getText();
        Assert.assertEquals(lblName,"Name:linhnguyen");

        String lblEmail = driver.findElement(By.xpath("//p[@id='email']")).getText();
        Assert.assertTrue(lblEmail.contains("abc@gmail.com"));

        String lblCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        Assert.assertTrue(lblCurrentAddress.contains("hanoi\n vietnam".replace("\n", "")));

        String lblPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();
        Assert.assertTrue(lblPermanentAddress.contains("hanoi\n vietnam".replace("\n", "")));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

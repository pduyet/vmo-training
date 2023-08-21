package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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
    public void TC01_robotClass() throws Exception{
        Robot rb = new Robot();
        driver.get("https://spreadsheetpage.com/accounting/account-receivable/");
        Assert.assertEquals(driver.getTitle(),"Account Receivable Excel Template » The Spreadsheet Page");
        WebElement clickDownLoad = driver.findElement(By.xpath("//a[text() = 'Download this template for free']"));
        clickDownLoad.click();
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_U);
        rb.keyPress(KeyEvent.VK_N);
        rb.keyPress(KeyEvent.VK_G);
        rb.keyPress(KeyEvent.VK_ENTER);
    }
    @Test()
    public void TC02_1_action() throws Exception, InterruptedException{
        Actions action = new Actions(driver);
        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        WebElement dragMe = driver.findElement(By.xpath("//div[text()='Drag me']"));
        WebElement dropHere = driver.findElement(By.xpath("(//div[@id ='droppable'])[1]"));
        action.dragAndDrop(dragMe,dropHere).build().perform();
        Thread.sleep(5000);
        WebElement verify = driver.findElement(By.xpath("//p[text()='Dropped!']"));
        verify.isDisplayed();
    }

    @Test()
    public void TC02_2_action() throws Exception, InterruptedException{
        Actions action = new Actions(driver);
        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        WebElement dragMe = driver.findElement(By.xpath("//div[text()='Drag me']"));
        WebElement dropHere = driver.findElement(By.xpath("(//div[@id ='droppable'])[1]"));
        action.clickAndHold(dragMe).build().perform();
        action.moveToElement(dropHere).build().perform();
        action.release().build().perform();
        Thread.sleep(5000);
        WebElement verify = driver.findElement(By.xpath("//p[text()='Dropped!']"));
        verify.isDisplayed();
    }

    @Test()
    public void Executor() throws Exception, InterruptedException{
        String FullName = "Nguyen Thi Mai Dung";
        String Email = "maidung@gmail.com";
        String CurrentAddress = "Tran Cung";
        String PermanentAddress = "Vinh Phuc";
        driver.get("https://demoqa.com/text-box");
        Assert.assertEquals(driver.getTitle(),"DEMOQA");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement fullName = driver.findElement(By.xpath("//input[@id ='userName']"));
        js.executeScript("arguments[0].value = arguments[1];", fullName, FullName);

        WebElement email = driver.findElement(By.xpath("//input[@id ='userEmail']"));
        js.executeScript("arguments[0].value = arguments[1];", email, Email);

        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id ='currentAddress']"));
        js.executeScript("arguments[0].value = arguments[1];", currentAddress, CurrentAddress);

        WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id ='permanentAddress']"));
        js.executeScript("arguments[0].value = arguments[1];", permanentAddress, PermanentAddress);

        String valueName = fullName.getAttribute("value");
        Assert.assertEquals(valueName,FullName);
        String valueEmail = email.getAttribute("value");
        Assert.assertEquals(valueEmail,Email);
        String valueCurrentAddress = currentAddress.getAttribute("value");
        Assert.assertEquals(valueCurrentAddress,CurrentAddress);
        String valuePermanentAddress = permanentAddress.getAttribute("value");
        Assert.assertEquals(valuePermanentAddress,PermanentAddress);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}

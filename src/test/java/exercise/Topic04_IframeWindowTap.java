package exercise;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Topic04_IframeWindowTap {
    WebDriver driver;
    public void switchWindowByID(String parentWindow){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow:allWindows){
            if (!runWindow.equals(parentWindow)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void switchWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow:allWindows){
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)){
                break;
            }
        }
    }
    public boolean closeAllWindowsWithoutParent(String parentWindow){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow:allWindows){
            if (!runWindow.equals(parentWindow)){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size()==1){
            return true;
        }else {
            return false;
        }
    }
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01() throws InterruptedException {
        String url = "https://dictionary.cambridge.org/";
        driver.get(url);
        String parentWindow = driver.getWindowHandle();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        driver.findElement(By.xpath("//div[@class='pr hdib lpr-5']/descendant::span[text()='Log in']")).click();
        switchWindowByTitle("Login");
        Assert.assertEquals(driver.getTitle(),"Login");
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Facebook']")));
        driver.findElement(By.xpath("//button[@title='Facebook']")).click();
        switchWindowByTitle("Facebook");
        Assert.assertEquals(driver.getTitle(),"Facebook");
        switchWindowByTitle("Login");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@title='Google']")).click();
        switchWindowByTitle("Sign in - Google Accounts");
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Sign in - Google Accounts");
        driver.close();
        switchWindowByTitle("Facebook");
        driver.close();
        switchWindowByTitle("Login");
        driver.close();
        switchWindowByTitle("Cambridge Dictionary | English Dictionary, Translations & Thesaurus");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='twitter-widget-0']")));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(By.xpath("(//article)[1]")));
        String title = driver.findElement(By.xpath("((//article)[1]/descendant::div[@dir='auto'])[5]")).getText();
        driver.findElement(By.xpath("(//article)[1]")).click();
        switchWindowByID(parentWindow);
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("(//article[@role='article'])[1]/descendant::div[@dir='auto']")).getText(),title);
    }
    @Test
    public void TC02() throws InterruptedException {
        String url = "https://the-internet.herokuapp.com/nested_frames";
        driver.get(url);
        String parentWindow = driver.getWindowHandle();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        Thread.sleep(5000);
        int sizeIframe = driver.findElements(By.tagName("iframe")).size();
        int sizeFrame = driver.findElements(By.tagName("frame")).size();
        System.out.println("Total number of iframe: " + sizeIframe);
        System.out.println("Total number of frame: " + sizeFrame);
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
        Assert.assertEquals(driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText(),"LEFT");
        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='MIDDLE']")).getText(),"MIDDLE");
        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-right']")));
        Assert.assertEquals(driver.findElement(By.xpath("//body[contains(text(),'RIGHT')]")).getText(),"RIGHT");
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-bottom']")));
        Assert.assertEquals(driver.findElement(By.xpath("//body[contains(text(),'BOTTOM')]")).getText(),"BOTTOM");
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

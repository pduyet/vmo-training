package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic03_TextboxTextareaDropdown {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_HandleSelectDropdownWithSelectListDefault() {
        String url = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select";
        driver.get(url);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        driver.switchTo().frame("iframeResult");
        Select objSelect = new Select(driver.findElement(By.xpath("//select[@name='cars']")));
        objSelect.selectByIndex(1);
        objSelect.selectByValue("opel");
        objSelect.selectByVisibleText("Audi");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        sleepInSecond(5);
        String lblResult = driver.findElement(By.xpath("//h2[text()='Your input was received as:']/following::div[1]")).getText();
        System.out.println(lblResult);
        Assert.assertEquals(lblResult, "cars=audi ");
    }

    @Test
    public void TC02_HandleDropdownListCustom() {
        String parentXpathSpeed = "//span[@id='speed-button']";
        String parentXpathFile = "//span[@id='files-button']";
        String parentXpathNumber = "//span[@id='number-button']";
        String parentXpathTitle = "//span[@id='salutation-button']";
        String childXpathSpeed = "//ul[@id='speed-menu']/child::li[1]";
        String childXpathFile = "//ul[@id='files-menu']/child::li";
        String childXpathNumber = "//ul[@id='number-menu']/child::li[3]";
        String childXpathTitle = "//ul[@id='salutation-menu']/child::li";
        String url = "https://jqueryui.com/selectmenu/";
        driver.get(url);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath(parentXpathSpeed)).click();
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpathSpeed)));
        List<WebElement> allItemsSpeed = driver.findElements(By.xpath(childXpathSpeed));
        for (WebElement childElement : allItemsSpeed) {
            if (childElement.getText().equals("Slower")) {
                explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(childXpathSpeed)));
                childElement.click();
                sleepInSecond(1);
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath(parentXpathSpeed)).getText(), "Slower");
        driver.findElement(By.xpath(parentXpathFile)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpathFile)));
        List<WebElement> allItemsFile = driver.findElements(By.xpath(childXpathFile));
        for (WebElement childElement : allItemsFile) {
            if (childElement.getText().equals("jQuery.js")) {
                explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(childXpathFile)));
                childElement.click();
                sleepInSecond(1);

            }
        }
        Assert.assertEquals(driver.findElement(By.xpath(parentXpathFile)).getText(), "jQuery.js");
        driver.findElement(By.xpath(parentXpathNumber)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpathNumber)));
        List<WebElement> allItemsNumber = driver.findElements(By.xpath(childXpathNumber));
        for (WebElement childElement : allItemsNumber) {
            if (childElement.getText().equals("3")) {
                explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(childXpathNumber)));
                childElement.click();
                sleepInSecond(1);
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath(parentXpathNumber)).getText(), "3");
        driver.findElement(By.xpath(parentXpathTitle)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpathTitle)));
        List<WebElement> allItemsTitle = driver.findElements(By.xpath(childXpathTitle));
        for (WebElement childElement : allItemsTitle) {
            if (childElement.getText().equals("Prof.")) {
                explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(childXpathTitle)));
                childElement.click();
                sleepInSecond(1);
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath(parentXpathTitle)).getText(), "Prof.");
    }

    @Test
    public void TC03_VerifyText() {
        String url = "https://kenh14.vn";
        driver.get(url);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        String title1 = driver.findElement(By.xpath("//h2[@class='klwfnl-title inited']")).getText();
        driver.findElement(By.xpath("//p[@id='searchinput']")).sendKeys(title1);
        String lblTitle1 = driver.findElement(By.xpath("//p[@id='searchinput']")).getText();
        Assert.assertEquals(lblTitle1,title1);
        driver.findElement(By.xpath("//p[@id='searchinput']")).clear();
        String title2 = driver.findElement(By.xpath("//h2[@class='klwfnr-title inited']")).getText();
        driver.findElement(By.xpath("//p[@id='searchinput']")).sendKeys(title2);
        String lblTitle2 = driver.findElement(By.xpath("//p[@id='searchinput']")).getText();
        Assert.assertEquals(lblTitle2,title2);
    }
    public void sleepInSecond(int time) {
        long milli = (long) time * 1000;
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}


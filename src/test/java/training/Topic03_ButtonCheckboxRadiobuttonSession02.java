package training;

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

public class Topic03_ButtonCheckboxRadiobuttonSession02 {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        List<WebElement> allItems = driver.findElements(By.xpath(childXpathSpeed));
        for (WebElement childElement : allItems) {
            if (childElement.getText().equals("Slower")) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);
                sleepInSecond(1);
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
    public void isButtonEnabled() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        WebElement buttonLogIn = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(buttonLogIn.isEnabled());
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0392921517");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("0392921517");
        Assert.assertTrue(buttonLogIn.isEnabled());
    }

    @Test
    public void isElementSelected01() {
        driver.get("https://demoqa.com/checkbox");
        driver.findElement(By.xpath("//button[@class='rct-collapse rct-collapse-btn']")).click();
        driver.findElement(By.xpath("(//button[@class='rct-collapse rct-collapse-btn'])[3]")).click();
        driver.findElement(By.xpath("(//button[@class='rct-collapse rct-collapse-btn'])[5]")).click();
        List<WebElement> allItemsOffice = driver.findElements(By.xpath("//span[text()='Office']/../../..//ol//span[@class='rct-title']"));
        for (WebElement childElement : allItemsOffice) {
            childElement.click();
        }
        List<WebElement> allItemsOfficeInput = driver.findElements(By.xpath("//span[text()='Office']/parent::label/parent::span/parent::li/child::ol/descendant::input"));
        for (WebElement childElement : allItemsOfficeInput) {
            Assert.assertTrue(childElement.isSelected());
        }
    }

    @Test
    public void isElementSelected02() {
        driver.get("https://demoqa.com/radio-button");
        WebElement impressiveButton = driver.findElement(By.xpath("(//label[@class='custom-control-label'])[2]"));
        impressiveButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='impressiveRadio']")).isSelected());
        String lblText = driver.findElement(By.xpath("//p[@class='mt-3']")).getText();
        Assert.assertEquals(lblText, "You have selected Impressive");
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

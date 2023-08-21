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

public class Topic05_TextBoxTextAreaDropdownList {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Hw01_HandleDropdownListDefault() {
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='w3-bar']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@id='tryhome']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@id='menuButton']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Save code']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Change Orientation']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Change Theme']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='runbtn']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@id='getwebsitebtn']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='CodeMirror-lines']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//iframe[@id='iframeResult']")).isDisplayed());

        WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
        driver.switchTo().frame(iframeResult);

        Select select = new Select(driver.findElement(By.xpath("//select[@id='cars']")));
        select.selectByIndex(1);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Saab");
        select.selectByValue("opel");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Opel");
        select.selectByVisibleText("Audi");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Audi");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[@class='w3-container']//h2")));

        Assert.assertEquals(driver.findElement(By.xpath("//body[@class='w3-container']//h2")).getText(),
                "Your input was received as:");
        String result = driver.findElement(By.xpath("//body[@class='w3-container']//div[1]")).getText();
        Assert.assertTrue(result.contains("cars=audi"));
    }

    @Test
    public void Hw02_HandleDropdownListCustom() {
        driver.get("https://jqueryui.com/selectmenu/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://jqueryui.com/selectmenu/");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sidebar']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='entry-title']")).getText(),
                "Selectmenu");

        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']")).isDisplayed());

        selectOption("//span[@id='speed-button']", "//ul[@id='speed-menu']//li", "Fast");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[1]")).getText(), "Fast");

        selectOption("//span[@id='files-button']", "//ul[@id='files-menu']//li", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[2]")).getText(), "ui.jQuery.js");

        selectOption("//span[@id='number-button']", "//ul[@id='number-menu']//li", "19");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[3]")).getText(), "19");

        selectOption("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li", "Dr.");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[4]")).getText(), "Dr.");
    }

    @Test
    public void Hw03_VerifyText() {
        driver.get("https://kenh14.vn");
        Assert.assertEquals(driver.getTitle(), "Kênh tin tức giải trí - Xã hội - Kenh14.vn");
        Assert.assertEquals(driver.getCurrentUrl(), "https://kenh14.vn/");

        String firstTitle = driver.findElement(By.xpath("//h2[@class='klwfnl-title inited']")).getText();
        WebElement searchBox = driver.findElement(By.xpath("//p[@id='searchinput']"));
        searchBox.sendKeys(firstTitle);
        Assert.assertEquals(searchBox.getText(), firstTitle);

        String secondTitle = driver.findElement(By.xpath("//h2[@class='klwfnr-title inited']")).getText();
        searchBox.clear();
        searchBox.sendKeys(secondTitle);
        Assert.assertEquals(searchBox.getText(), secondTitle);
    }

    public void selectOption(String parentXpath, String childXpath, String selectedValue) {
        driver.findElement(By.xpath(parentXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(childXpath)));
        List<WebElement> allOptions = driver.findElements(By.xpath(childXpath));
        for (WebElement option : allOptions) {
            if (option.getText().equals(selectedValue)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

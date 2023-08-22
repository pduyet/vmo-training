package tranning;

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

public class Topic07_Locator {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_HandelDropdownListCustom() throws InterruptedException {
        driver.get("https://jqueryui.com/selectmenu/");
        Assert.assertEquals(driver.getTitle(), "Selectmenu | jQuery UI");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        selectItemInDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[3]", "Medium");

        selectItemInDropdown("//span[@id='files-button']", "//ul[@id='files-menu']//li[5]", "Some unknown file");

        selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[6]", "6");

        selectItemInDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li[4]", "Dr.");

    }

    @Test
    public void TC02_HandelButton() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("tiennt4@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("Tien123456@");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
    }

    @Test
    public void TC03_VerifyCheckbox() {
        driver.get("https://demoqa.com/elements");
        driver.findElement(By.xpath("//span[contains(text(),'Check Box')]")).click();
        driver.findElement(By.xpath("(//button[@title='Toggle'])[1]")).click();
        driver.findElement(By.xpath("(//button[@title='Toggle'])[3]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Office')]")).click();
        List<WebElement> checkbox = driver.findElements(By.xpath("//span[text()='Office']//parent::label//parent::span/parent::li//ol//label"));
        for (WebElement item : checkbox) {
            Assert.assertTrue(item.isSelected());
        }
    }
    @Test
    public void TC04_VerifyRadiobutton() {
        driver.get("https://demoqa.com/elements");
        driver.findElement(By.xpath("//span[contains(text(),'Radio Button')]")).click();
        driver.findElement(By.xpath("//label[@for='impressiveRadio']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='text-success']")).getText().contains("Impressive"));
    }


    protected void selectItemInDropdown(String parentXpath, String childXpath, String expectedTextItem) throws InterruptedException {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(parentXpath))));
        driver.findElement(By.xpath(parentXpath)).click();
        Thread.sleep(1000);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                if (item.isDisplayed()) {
                    item.click();
                } else {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    Thread.sleep(1000);
                    item.click();
                }

            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

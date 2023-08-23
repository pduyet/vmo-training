package Topic03;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic03_B02_Example {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void selectItemDropdown(String parentXpath, String childXpath, String expectedValue) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

        for (WebElement childElement:allItems){
            if (childElement.getText().equals(expectedValue)){
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);",childElement);
                Thread.sleep(1000);
                explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(childXpath)));
                childElement.click();
                Thread.sleep(1000);
            }
        }
    }
    @Test
    public void ex1() throws InterruptedException {
        String hw2_url = "https://jqueryui.com/selectmenu/";
        driver.get(hw2_url);
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        Assert.assertEquals(driver.getCurrentUrl(),hw2_url);

        selectItemDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']//li","Slow");
//        driver.findElement(By.xpath("//span[@id='speed-button']")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//ul[@id='speed-menu']//li[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']/span[text()='Slow']")).isDisplayed());

        selectItemDropdown("//span[@id='files-button']","//ul[@id='files-menu']//li","ui.jQuery.js");
//        driver.findElement(By.xpath("//span[@id='files-button']")).click();
//        driver.findElement(By.xpath("//ul[@id='files-menu']//li[3]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']/span[text()='ui.jQuery.js']")).isDisplayed());

        selectItemDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li","4");
//        driver.findElement(By.xpath("//span[@id='number-button']")).click();
//        driver.findElement(By.xpath("//ul[@id='number-menu']//li[4]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='4']")).isDisplayed());

        selectItemDropdown("//span[@id='salutation-button']","//ul[@id='salutation-menu']//li","Mr.");
//        driver.findElement(By.xpath("//span[@id='salutation-button']")).click();
//        driver.findElement(By.xpath("//ul[@id='salutation-menu']//li[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']/span[text()='Mr.']")).isDisplayed());
    }

    @Test
    public void ex2() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        WebElement buttonLogin = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(buttonLogin.isEnabled());
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0334797119");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("Uyen06102000");
        Assert.assertTrue(buttonLogin.isEnabled());
    }

    @Test
    public void ex3() throws InterruptedException {
        driver.get("https://demoqa.com/checkbox");
        driver.findElement(By.xpath("(//button[@title='Toggle'])[1]")).click();
        driver.findElement(By.xpath("(//button[@title='Toggle'])[3]")).click();
        driver.findElement(By.xpath("(//button[@title='Toggle'])[5]")).click();
        List<WebElement> spans = driver.findElements(By.xpath("//span[text()='Office']//following::ol//span[@class='rct-checkbox']"));
        for (WebElement s: spans)
        {
            s.click();
        }
        List<WebElement> checkboxs = driver.findElements(By.xpath("//span[text()='Office']//following::ol//input[@type='checkbox']"));
        for (WebElement cb: checkboxs)
        {
            Assert.assertTrue(cb.isEnabled());
        }
    }

    @Test
    public void ex4() throws InterruptedException {
        driver.get("https://demoqa.com/radio-button");
        driver.findElement(By.xpath("//label[@for='impressiveRadio']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='text-success']")).getText(),"Impressive");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

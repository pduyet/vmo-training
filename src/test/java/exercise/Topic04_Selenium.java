package exercise;

import org.openqa.selenium.By;
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

public class Topic04_Selenium {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void HandleNavigatorFunction() {
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='small-searchterms']")));

        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");

        List<WebElement> listProductName = driver.findElements(By.xpath("//h2[@class='product-title']"));
        String firstProductName = listProductName.get(0).getText();
        List<WebElement> listProductPrice = driver.findElements(By.xpath("//span[@class='price actual-price']"));
        String firstProductPrice = listProductPrice.get(0).getText();
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(firstProductName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='small-searchterms']")));
        WebElement productName = driver.findElement(By.xpath("//h2[@class='product-title']"));
        boolean isProductDisplayed = productName.isDisplayed();
        String name = productName.getText();
        Assert.assertTrue(isProductDisplayed);
        Assert.assertEquals(firstProductName, name);
        String productPrice = driver.findElement(By.xpath("//span[@class='price actual-price']")).getText();
        Assert.assertEquals(productPrice, firstProductPrice);

        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='small-searchterms']")));
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");

        driver.navigate().forward();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='small-searchterms']")));
        Assert.assertTrue(isProductDisplayed);
        Assert.assertEquals(firstProductName, name);
        Assert.assertEquals(productPrice, firstProductPrice);
        String currentURL = driver.getCurrentUrl();

        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='small-searchterms']")));
        String urlAfterRefresh = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,urlAfterRefresh);
        Assert.assertTrue(isProductDisplayed);
        Assert.assertEquals(firstProductName, name);
        Assert.assertEquals(productPrice, firstProductPrice);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

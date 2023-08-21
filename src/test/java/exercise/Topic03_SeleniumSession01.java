package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic03_SeleniumSession01 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void HandleNavigateFunctionInSelenium() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        String productName = driver.findElement(By.xpath("//div[@data-productid='1']/descendant::h2")).getText();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(productName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-productid='1']")).isDisplayed());
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        driver.navigate().forward();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-productid='1']")).isDisplayed());
        String url = driver.getCurrentUrl();
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }
    @AfterMethod
    public void teardown(){driver.quit();}
}

package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise05_Selenium {
    WebDriver driver;
    String url ="https://demo.nopcommerce.com/";
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }
    @Test
    public void TC01_HandleNavigateFucntion(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,"nopCommerce demo store");
        String firstProductName = driver.findElement(By.xpath("(//h2[@class='product-title']//a)[1]")).getText();
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(firstProductName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String productNameAfterSearch = driver.findElement(By.xpath("(//h2[@class='product-title']//a)[1]")).getText();
        Assert.assertEquals(productNameAfterSearch,firstProductName);


        driver.navigate().back();
        System.out.println("Url navigate back to back to homepage: " + driver.getCurrentUrl());
        Assert.assertEquals(actualTitle,driver.getTitle());

        Assert.assertEquals(driver.findElement(By.xpath("(//h2[@class='product-title']//a)[1]")).getText(),firstProductName);
        driver.navigate().forward();
        String urlBeforeRefresh = driver.getCurrentUrl();
        System.out.println("Search page before refresh: " +urlBeforeRefresh);
        driver.navigate().refresh();
        String urlAfterRefresh = driver.getCurrentUrl();
        System.out.println("Search page after refresh: " +urlAfterRefresh);
        Assert.assertEquals(urlBeforeRefresh, urlAfterRefresh);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }
}

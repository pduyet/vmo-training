package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchComputer {

    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void TC01_SearchComputer(){
        String actualTitlePage = "nopCommerce demo store";
        Assert.assertEquals(driver.getTitle(),actualTitlePage);
        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.sendKeys("computer");
        WebElement btnSearch = driver.findElement(By.className("search-box-button"));
        btnSearch.click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

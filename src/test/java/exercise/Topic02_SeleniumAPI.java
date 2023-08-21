package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_SeleniumAPI {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void Hw01_HandleNavigate() {
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        String firstProduct = driver.findElement(By.xpath("(//div[@class='details']/h2/a)[1]")).getText();
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(firstProduct);
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
        String resultSearch = driver.findElement(By.xpath("(//div[@class='details']/h2/a)[1]")).getText();
        Assert.assertEquals(resultSearch,firstProduct);
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        driver.navigate().forward();
        Assert.assertEquals(resultSearch,firstProduct);
        String urlBeforeRefresh = driver.getCurrentUrl();
        driver.navigate().refresh();
        String urlAfterRefresh = driver.getCurrentUrl();
        Assert.assertEquals(urlBeforeRefresh,urlAfterRefresh);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

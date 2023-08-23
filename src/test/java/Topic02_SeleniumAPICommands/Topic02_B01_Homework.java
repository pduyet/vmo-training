package Topic02_SeleniumAPICommands;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Topic02_B01_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void HandleNavigateFunction() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://demo.nopcommerce.com/");
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        js.executeScript("window.scrollBy(0,1000)", "");
        String searchKeyword = driver.findElement(By.xpath("(//strong[text()='Featured products']//following::h2/a)[1]")).getText();
        js.executeScript("window.scrollBy(0,-1000)", "");
        driver.findElement(By.xpath("//input[@placeholder='Search store']")).sendKeys(searchKeyword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[contains(@class,'search-text')]")).getAttribute("value"),searchKeyword);
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        driver.navigate().forward();
        Assert.assertEquals(driver.findElement(By.xpath("//input[contains(@class,'search-text')]")).getAttribute("value"),searchKeyword);
        String beforeURL = driver.getCurrentUrl();
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(),beforeURL);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

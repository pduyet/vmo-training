package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    public void TC01_HandleNavigateFunction() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.click();
        WebElement getTextSearch = driver.findElement(By.xpath("//h2[@class='product-title']"));
        String textSearch = getTextSearch.getText();
        searchBox.sendKeys(textSearch);
        WebElement btnSearch = driver.findElement((By.xpath("//button[@type='submit']")));
        btnSearch.click();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement verifySearch = driver.findElement(By.xpath("(//h2[@class ='product-title'])[1]/a"));
        String verifyTextSearch = verifySearch.getText();
        Assert.assertEquals(verifyTextSearch, textSearch);
        driver.navigate().back();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
        driver.navigate().forward();
        Thread.sleep(3000);
        WebElement verifyAfterForward = driver.findElement(By.xpath("//div[@class='search-results']//a[contains(text(),'Build your own computer')]"));
        verifyAfterForward.isDisplayed();
        String URLBefore = driver.getCurrentUrl();
        driver.navigate().refresh();
        Thread.sleep(3000);
        String URLAfter = driver.getCurrentUrl();
        Assert.assertEquals(URLAfter, URLBefore);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

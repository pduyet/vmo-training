package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Selenium_Session02_Homework01 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_HandleSelctDrowdownWithSelectListDefault() throws InterruptedException {
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");

        Assert.assertTrue(driver.findElement(By.id("tryhome")).isDisplayed());
        driver.switchTo().frame("iframeResult");
        Select select = new Select(driver.findElement(By.id("cars")));
        select.selectByIndex(1); // select saaba
        select.selectByValue("opel");
        select.selectByVisibleText("Audi");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//body//div[contains(.,'cars=audi')]")).isDisplayed());

    }

    @Test
    public void TC02_HandleDropdownListCustom() throws InterruptedException {
        driver.get("https://jqueryui.com/selectmenu/");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='menu-top-container']")).isDisplayed());
        driver.switchTo().frame(frame);
        driver.findElement(By.id("speed-button")).click();
        driver.findElement(By.xpath("(//ul[@id='speed-menu']/li)[4]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@id='speed-button']/span)[2]")).getText(),"Fast");
        driver.findElement(By.id("files-button")).click();
        driver.findElement(By.xpath("//ul[@id='files-menu']//li[3]/div")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@id='files-button']/span)[2]")).getText(),"ui.jQuery.js");
        driver.findElement(By.id("number-button")).click();
        driver.findElement(By.xpath("//ul[@id='number-menu']//li[6]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@id='number-button']/span)[2]")).getText(),"6");
        driver.findElement(By.id("salutation-button")).click();
        driver.findElement(By.xpath("//ul[@id='salutation-menu']//li[5]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@id='salutation-button']/span)[2]")).getText(),"Prof.");

    }
    @Test
    public void TC03_VerifyText(){
        driver.get("https://kenh14.vn");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='kenh14-header-wrapper']")).isDisplayed());
        String titleArticle = driver.findElement(By.xpath("(//div[@id='bindRegionNews']//a)[2]")).getText();
        System.out.println(titleArticle);
        WebElement textboxSearch = driver.findElement(By.id("searchinput"));
        textboxSearch.sendKeys(titleArticle);
        Assert.assertEquals(textboxSearch.getText(),titleArticle);
       // driver.findElement(By.xpath("//p[@id='searchinput']//following-sibling::a")).click();
       // driver.navigate().back();

        String titleSecondArticle = driver.findElement(By.xpath("(//div[@id='bindRegionNews']//a)[4]")).getText();
        System.out.println(titleSecondArticle);
        textboxSearch = driver.findElement(By.id("searchinput"));
        textboxSearch.clear();
        textboxSearch.sendKeys(titleSecondArticle);
        Assert.assertEquals(textboxSearch.getText(),titleSecondArticle);
      //  driver.findElement(By.xpath("//p[@id='searchinput']//following-sibling::a")).click();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

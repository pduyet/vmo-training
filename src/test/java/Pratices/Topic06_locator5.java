package Pratices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic06_locator5 {
  WebDriver driver;
  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }
  @Test
  public void TC01_handelSelectDropdownWithSelectListDefault() throws InterruptedException {
    driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
    Assert.assertEquals(driver.getTitle(), "W3Schools Tryit Editor");
    driver.switchTo().frame("iframeResult");
    Select select = new Select(driver.findElement(By.xpath("//select[@id='cars']")));
    select.selectByIndex(1);
    select.selectByValue("opel");
    select.selectByVisibleText("Audi");
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'cars=audi')]")).isDisplayed());
  }
  @Test
  public void TC02_HandelDropdownListCustom() throws InterruptedException {
    driver.get("https://jqueryui.com/selectmenu/");
    Assert.assertEquals(driver.getTitle(), "Selectmenu | jQuery UI");
    WebElement frame = driver.findElement(By.className("demo-frame"));
    driver.switchTo().frame(frame);
    driver.findElement(By.xpath("//span[@id='speed-button']")).click();
    driver.findElement(By.xpath("//ul[@id='speed-menu']//li[3]")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']")).getText().contains("Medium"));

    driver.findElement(By.xpath("//span[@id='files-button']")).click();
    driver.findElement(By.xpath("//ul[@id='files-menu']//li[6]")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']")).getText().contains("Some other file"));

    driver.findElement(By.xpath("//span[@id='number-button']")).click();
    driver.findElement(By.xpath("//ul[@id='number-menu']//li[6]")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']")).getText().contains("6"));

    driver.findElement(By.xpath("//span[@id='salutation-button']")).click();
    driver.findElement(By.xpath("//ul[@id='salutation-menu']//li[4]")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']")).getText().contains("Dr"));
  }
  @Test
  public void TC03_VerifyText() {
    driver.get("https://kenh14.vn");
    Assert.assertEquals(driver.getTitle(), "Kênh tin tức giải trí - Xã hội - Kenh14.vn");
    WebElement searchTextbox = driver.findElement(By.xpath("//p[@id='searchinput']"));
    String  firstArticle = driver.findElement(By.xpath("//h2[@class='klwfnl-title inited']/a")).getText();
    searchTextbox.sendKeys(firstArticle);
    Assert.assertEquals( searchTextbox.getText(),firstArticle);

    String  secondArticle = driver.findElement(By.xpath("//h2[@class='klwfnr-title inited']/a")).getText();
    searchTextbox.clear();
    searchTextbox.sendKeys(secondArticle);
    Assert.assertEquals( searchTextbox.getText(),secondArticle);
  }
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}

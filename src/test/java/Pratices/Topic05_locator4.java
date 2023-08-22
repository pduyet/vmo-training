package Pratices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic05_locator4 {
WebDriver driver;
  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }
  @Test
  public void TC01_Handle_Navigate_Function_In_Selenium () throws InterruptedException {
    driver.get("https://demo.nopcommerce.com/");
    Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
    WebElement firstProductInFeaturedProducts = driver.findElement(By.xpath("(//h2[@class='product-title']/a)[1]"));
    System.out.println( "Sp đầu tiên trong list Sp là: " + firstProductInFeaturedProducts.getText());
    String txtSearch = firstProductInFeaturedProducts.getText() ;
    driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(txtSearch);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(3000);
    Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='product-title']//a")).getText(),txtSearch);
    driver.navigate().back();
    Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
    driver.navigate().forward();
    Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='product-title']//a")).getText(),txtSearch);
    String url1 = driver.getCurrentUrl();
    driver.navigate().refresh();
    String url2 = driver.getCurrentUrl();
    Assert.assertEquals(url1,url2);
  }
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
  }

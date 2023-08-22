package tranning;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic11_TestNG {
  WebDriver driver;
  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
  }
  @DataProvider(name="UserInfo")
  public Object[][] getDataFromDataProvider(){
    return new Object[][]
        {
            { "Guru99", "India" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };
  }
  @Test(dataProvider="UserInfo")
  public void TC02_CreateANewCustomer(String UserId, String PassWord) {
    driver.get("https://demo.guru99.com/v4/index.php");
    Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
    driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(UserId);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PassWord);
//    driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
//    Assert.assertEquals(driver.findElement(By.xpath("//marquee[@behavior='alternate']")).getText(),
//        "Welcome To Manager's Page of Guru99 Bank");
//    driver.findElement(By.xpath("//a[text()='New Customer']")).click();
  }


  @AfterMethod
  public void tearDown() {
   // driver.quit();
  }

}

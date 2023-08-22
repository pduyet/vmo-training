package training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Topic07_TestNG {
    WebDriver driver;
    @Test(dataProvider = "SearchProvider")
    @Parameters({"browser","role"})
    public void testMethod(String username, String pw){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        driver.quit();
    }
    @DataProvider(name = "SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        {"Guru99","India"},
                        {"Krishna","UK"},
                        {"Bhupesh","USA"}
                };
    }
    @Test
    public void SoftAssert(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertTrue(true);
        softAssert.assertTrue(false);
        softAssert.assertEquals(1,1);
        softAssert.assertAll();
    }
}

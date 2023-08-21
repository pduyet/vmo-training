package Training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic07 {
    WebDriver driver = new ChromeDriver();
    @Test(dataProvider = "Login")
    public void TC01_HandleNavigateFunction(String UserID, String Password) {
        driver.get("https://demo.guru99.com/V4/");
        WebElement Username = driver.findElement((By.xpath("//input[@type = 'text']")));
        Username.sendKeys(UserID);
        WebElement PassWord = driver.findElement((By.xpath("//input[@type='password']")));
        PassWord.sendKeys(Password);

    }
    @DataProvider(name = "Login")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {"maidung", "India"},
                        {"VinhPhuc", "UK"},
                        {"VinhTuong", "USA"}
                };
    }

    @Test
    @Parameters ({"browser", "Test01"})
    public void TC01 (String browser, String Test01){
        System.out.println("Test" + browser);
    }

    @Test
    @Parameters ({"browser", "Test01"})
    public void test (@Optional("firefor") String browser,@Optional("user") String Test01){
        System.out.println("Test" + browser);
    }


}

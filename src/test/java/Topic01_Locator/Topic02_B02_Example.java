package Topic01_Locator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_B02_Example {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://demoqa.com/text-box");

    }

    @Test
    public void Example01() throws InterruptedException {
        String[] xpath =
                {
                        "//input[@id='userName']",
                        "//input[@id='userEmail']",
                        "//textarea[@id='currentAddress']",
                        "//textarea[@id='permanentAddress']"
                };
        String[] input =
                {
                        "uyen nguyen",
                        "uyenntt1@vmogroup.com",
                        "trung kinh\nha noi",
                        "ton that thuyet\nha noi"
                };
        for (int i = 0; i < xpath.length; i++) {
            driver.findElement(By.xpath(xpath[i])).sendKeys(input[i]);
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        driver.findElement(By.xpath("//button[@id='submit']")).click();

        String lblName = driver.findElement(By.xpath("//p[@id='name']")).getText();
        Assert.assertTrue(lblName.contains(input[0].replace("\n", " ")));

        String lblEmail = driver.findElement(By.xpath("//p[@id='email']")).getText();
        Assert.assertTrue(lblEmail.contains(input[1].replace("\n", " ")));

        String lblCAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        Assert.assertTrue(lblCAddress.contains(input[2].replace("\n", " ")));

        String lblPAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();
        Assert.assertTrue(lblPAddress.contains(input[3].replace("\n", " ")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

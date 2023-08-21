package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic02_LocatorSession02 {
    WebDriver driver;
    String fullName = "Nguyen Thi huong linh";
    String email = "linhnth86@gmail.com";
    String currentAddress = "Ha noi \nViet Nam";
    String permanentAddress = "Ha noi \nViet Nam";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Topic02_TextBox() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(fullName);
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(email);
        driver.findElement(By.xpath("//textarea[@id='currentAddress']")).sendKeys(currentAddress);
        driver.findElement(By.xpath("//textarea[@id='permanentAddress']")).sendKeys(permanentAddress);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        driver.findElement(By.xpath("//button[@id='submit']")).click();

        String lblName = driver.findElement(By.xpath("//p[@id='name']")).getText();
        Assert.assertTrue(lblName.contains(fullName));

        String lblEmail = driver.findElement(By.xpath("//p[@id='email']")).getText();
        Assert.assertTrue(lblEmail.contains(email));

        String lblCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        Assert.assertTrue(lblCurrentAddress.contains(currentAddress.replace("\n", "")));

        String lblPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();
        Assert.assertTrue(lblPermanentAddress.contains(permanentAddress.replace("\n", "")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

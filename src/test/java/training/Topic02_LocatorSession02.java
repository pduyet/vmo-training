package training;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_LocatorSession02 {
    WebDriver driver;
    String fullName;
    String email;
    String currentAddress;
    String permanentAddress;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        fullName = "Ngo Anh Thai";
        email = "ngoanhthai100899@gmail.com";
        currentAddress = "My Dinh\nHa Noi";
        permanentAddress = "Hoang Mai\nHa Noi";
    }

    @Test
    public void training() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(fullName);
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(email);
        driver.findElement(By.xpath("//textarea[@id='currentAddress']")).sendKeys(currentAddress);
        driver.findElement(By.xpath("//textarea[@id='permanentAddress']")).sendKeys(permanentAddress);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        driver.findElement(By.xpath("//button[@id='submit']")).click();

        String lblName = driver.findElement(By.xpath("//p[@id='name']")).getText();
        Assert.assertTrue(lblName.contains(fullName));
        String lblEmail = driver.findElement(By.xpath("//p[@id='email']")).getText();
        Assert.assertTrue(lblEmail.contains(email));
        String lblCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        Assert.assertTrue(lblCurrentAddress.contains(currentAddress.replace("\n", " ")));
        String lblPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();
        Assert.assertTrue(lblPermanentAddress.contains(permanentAddress.replace("\n", " ")));
    }

    @AfterMethod
    public void tearDown(){driver.quit();}
}

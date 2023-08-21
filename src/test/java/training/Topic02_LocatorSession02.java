package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_LocatorSession02 {
    WebDriver driver;
    String myName = "Do Thi Hau";
    String myText = "Thai Binh\nViet Nam";
    String myEmail = "haudt@vmogroup.com";
    String myAddress = "Thai Binh\nViet Nam";
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
    }
    @Test
    public void TC01_VerifyDemoQA() {
        //Ctrl+Alt+L
        WebElement inputFullName = driver.findElement(By.xpath("//input[@id='userName']"));
        inputFullName.sendKeys(myName);

        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        inputEmail.sendKeys(myEmail);

        WebElement inputCurrentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        inputCurrentAddress.sendKeys(myText);

        WebElement inputAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        inputAddress.sendKeys(myAddress);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submit']"));
        btnSubmit.click();
        WebElement verifyName = driver.findElement(By.xpath("//p[@id='name']"));
        Assert.assertTrue(verifyName.getText().contains(myName));

        WebElement verifyEmail = driver.findElement(By.xpath("//p[@id='email']"));
        Assert.assertTrue(verifyEmail.getText().contains(myEmail));

        WebElement verifyCurrentEmail = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        Assert.assertTrue(verifyCurrentEmail.getText().contains(myText.replace("\n"," ")));

        WebElement verifyAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        Assert.assertTrue(verifyAddress.getText().contains(myText.replace("\n"," ")));

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

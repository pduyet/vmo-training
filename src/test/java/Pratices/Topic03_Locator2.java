package Pratices;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic03_Locator2 {
    WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String fullName = "nguyen thuy tien";
    String email = "tiennt4@vmogroup.com";
    String currentAddress = "thanh xuan";
    String permanentAddress = "ha noi\nviet nam";

    @BeforeMethod
    public void setup() {
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void TC01_SubmitForm() {

        WebElement txtBox_Fullname = driver.findElement(By.xpath("//input[@id='userName']"));
        txtBox_Fullname.sendKeys(fullName);
        WebElement txtBox_Email = driver.findElement(By.xpath("//input[@id='userEmail']"));
        txtBox_Email.sendKeys(email);
        WebElement txtBox_CurrentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        txtBox_CurrentAddress.sendKeys(currentAddress);
        WebElement txtBox_PermanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        txtBox_PermanentAddress.sendKeys(permanentAddress);
        WebElement buttonSubmit = driver.findElement(By.xpath("//button[@id='submit']"));

        js.executeScript("window.scrollBy(0,350)", "");
        buttonSubmit.click();

        WebElement txtName = driver.findElement(By.xpath("//p[@id='name']"));
        Assert.assertTrue(txtName.getText().contains(fullName));
        WebElement txtEmail = driver.findElement(By.xpath("//p[@id='email']"));
        Assert.assertTrue(txtEmail.getText().contains(email));
        WebElement txtCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        Assert.assertTrue(txtCurrentAddress.getText().contains(currentAddress));
        WebElement txtPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        Assert.assertTrue(txtPermanentAddress.getText().contains(permanentAddress.replace("\n"," ")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}


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

// input thoong tin va verify thong tin hien thi tren man hinh
public class Locator_Session02 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_VerifyEnteredData() throws InterruptedException {
        driver.get("https://demoqa.com/text-box");
        WebElement inputFullname = driver.findElement(By.xpath("//input[@id='userName']"));
        String dataFullname = "Chung Nguyen";
        inputFullname.sendKeys(dataFullname);
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        String dataEmail = "chungnt@vmogroup.com";
        inputEmail.sendKeys(dataEmail);
        WebElement inputCurrentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        String dataCurrentAdress = "Cau giay\nHa Noi";
       // System.out.println(dataCurrentAdress);
        inputCurrentAddress.sendKeys(dataCurrentAdress);
        WebElement inputPaymentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        String dataPaymentAddress = "Dong da Ha noi";
        inputPaymentAddress.sendKeys(dataPaymentAddress);
        // Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submit']"));
        btnSubmit.click();

        WebElement actualName = driver.findElement(By.xpath("//p[@id='name']"));
        //   System.out.println("Fullname: " + actualName.getText());
        //  Assert.assertEquals(actualName.getText(),dataFullname);
        Assert.assertTrue(actualName.getText().contains(dataFullname));
        WebElement actualEmail = driver.findElement(By.xpath("//p[@id='email']"));
        Assert.assertTrue(actualEmail.getText().contains(dataEmail));

        WebElement actualCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']"));

        Assert.assertTrue(actualCurrentAddress.getText().contains(dataCurrentAdress.replace("\n"," ")));
        System.out.println("Actual current address: " + actualCurrentAddress.getText());
      //  System.out.println("Actual current address: " + dataCurrentAdress.replace("\n"," "));
        WebElement actualPaymentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        Assert.assertTrue(actualPaymentAddress.getText().contains(dataPaymentAddress.replace("\n"," ")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

package tranning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_Locator {
    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void setup() {
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC01_RegisterWithEmptyData(){

        WebElement buttonRegister = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonRegister.click();

        WebElement errorMsgFirstName = driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
        Assert.assertEquals(errorMsgFirstName.getText(),"Vui lòng nhập họ tên");

        WebElement errorMsgEmailName = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        Assert.assertEquals(errorMsgEmailName.getText(),"Vui lòng nhập email");

        WebElement errorMsgConfirmEmail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        Assert.assertEquals(errorMsgConfirmEmail.getText(),"Vui lòng nhập lại địa chỉ email");

        WebElement errorMsgPassword = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        Assert.assertEquals(errorMsgPassword.getText(),"Vui lòng nhập mật khẩu");

        WebElement errorMsgConfirmPassword = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        Assert.assertEquals(errorMsgConfirmPassword.getText(),"Vui lòng nhập lại mật khẩu");

        WebElement errorMsgPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        Assert.assertEquals(errorMsgPhone.getText(),"Vui lòng nhập số điện thoại.");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
        }

}

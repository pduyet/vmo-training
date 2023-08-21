package training;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Topic02_LocatorSession01 {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }
    @Test
    public void TC01() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement textName = driver.findElement(By.id("txtFirstname-error"));
        Assert.assertEquals(textName.getText(), "Vui lòng nhập họ tên");
        WebElement textEmail = driver.findElement(By.id("txtEmail-error"));
        Assert.assertEquals(textEmail.getText(), "Vui lòng nhập email");
        WebElement textCEmail = driver.findElement(By.id("txtCEmail-error"));
        Assert.assertEquals(textCEmail.getText(), "Vui lòng nhập lại địa chỉ email");
        WebElement errorMsgPassword = driver.findElement(By.id("txtPassword-error"));
        Assert.assertEquals(errorMsgPassword.getText(), "Vui lòng nhập mật khẩu");
        WebElement errorMsgCPassword = driver.findElement(By.id("txtCPassword-error"));
        Assert.assertEquals(errorMsgCPassword.getText(), "Vui lòng nhập lại mật khẩu");
        WebElement errorMsgPhone = driver.findElement(By.id("txtPhone-error"));
        Assert.assertEquals(errorMsgPhone.getText(), "Vui lòng nhập số điện thoại.");

    };
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

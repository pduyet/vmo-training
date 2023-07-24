package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginToOrangeHRMSystem {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void TC02_LoginToOrangeHRMSystem() throws InterruptedException {
        String titlePage = "OrangeHRM";
        Assert.assertEquals(driver.getTitle(), titlePage);
        Thread.sleep(3000);
        WebElement txtUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));

        txtUsername.sendKeys("Admin");
        WebElement txtPassword = driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]"));
        txtPassword.sendKeys("admin123");
        WebElement btnLogin = driver.findElement(By.className("orangehrm-login-button"));
        btnLogin.click();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

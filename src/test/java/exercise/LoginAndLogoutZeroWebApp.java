package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndLogoutZeroWebApp {

    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://zero.webappsecurity.com/");
    }

    @Test
    public void TC03_LoginAndLogoutZeroWebApp() throws InterruptedException {
        String actualTitlePage = "Zero - Personal Banking - Loans - Credit Cards";
        Assert.assertEquals(driver.getTitle(),actualTitlePage);
        WebElement btnSignin = driver.findElement(By.id("signin_button"));
        btnSignin.click();
        Thread.sleep(2000);
        WebElement txtLogin = driver.findElement(By.id("user_login"));
        txtLogin.sendKeys("username");
        WebElement txtPassword = driver.findElement(By.id("user_password"));
        txtPassword.sendKeys("password");
        WebElement btnLogin2 = driver.findElement(By.name("submit"));
        btnLogin2.click();
        //Thread.sleep(2000);
        //After login successfull forward to other page
        driver.navigate().to("http://zero.webappsecurity.com/bank/transfer-funds.html");
        String actualTitleAfterLogin = "Zero - Transfer Funds";
        Assert.assertEquals(driver.getTitle(),actualTitleAfterLogin);

        WebElement dropdownUsername = driver.findElement(By.className("icon-user"));
        dropdownUsername.click();
        WebElement btnLogout = driver.findElement(By.id("logout_link"));
        btnLogout.click();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

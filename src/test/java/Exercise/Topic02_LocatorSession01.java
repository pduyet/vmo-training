package Exercise;

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
    }
    @Test
    public void TC01_SearchComputer() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");

        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");

        WebElement Searchbox = driver.findElement(By.id("small-searchterms"));
        Searchbox.click();
        Searchbox.sendKeys("computer");
        WebElement Btnsearch = driver.findElement((By.xpath("//button[@type='submit']")));
        Btnsearch.click();
    }
    @Test
    public void TC02_LoginToOrangeHRMSystem()throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
        Thread.sleep(50000);
        WebElement userName = driver.findElement(By.name("username"));
        userName.click();
        userName.sendKeys("Admin");
        WebElement Password = driver.findElement(By.name("password"));
        Password.click();
        Password.sendKeys("admin123");
        WebElement Btnlogin = driver.findElement((By.xpath("//button[@type='submit']")));
        Btnlogin.click();
    }
    @Test
    public void TC03_LoginAndLogoutToZeroWebApp() {
        driver.get("http://zero.webappsecurity.com/");
        driver.manage().window().maximize();

        Assert.assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");
        WebElement Btnsignin = driver.findElement((By.id("signin_button")));
        Btnsignin.click();
        WebElement Login = driver.findElement(By.name("user_login"));
        Login.click();
        Login.sendKeys("username ");
        WebElement Password = driver.findElement(By.name("user_password"));
        Password.click();
        Password.sendKeys("password");
        WebElement Btnsignin1 = driver.findElement((By.name("submit")));
        Btnsignin1.click();
        driver.get("http://zero.webappsecurity.com/bank/transfer-funds.html");
        Assert.assertEquals(driver.getTitle(),"Zero - Transfer Funds");
        WebElement Dropdowusername = driver.findElement(By.xpath("//a[normalize-space()='username']"));
        Dropdowusername.click();
        WebElement Btnlogout = driver.findElement(By.id("logout_link"));
        Btnlogout.click();
        Assert.assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");
    }
    @AfterMethod
    public void tearDown(){driver.quit();}



}

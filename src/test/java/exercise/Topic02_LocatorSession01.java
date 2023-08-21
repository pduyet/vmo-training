package Topic02_Locators;

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
    public void TC01_SearchComputer(){
        driver.get("https://demo.nopcommerce.com/");
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));
        searchBox.sendKeys("computer");
        WebElement searchButton = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
        searchButton.click();
    }
    @Test
    public void TC02_LoginToOrangeHRMSystem(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
        WebElement username = driver.findElement(By.xpath("//input[@name=\"username\"]"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("admin123");
        WebElement buttonLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
    }
    @Test
    public void TC03_LoginAndLogoutToZeroWebApp(){
        driver.get("http://zero.webappsecurity.com/");
        Assert.assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");
        WebElement buttonSignin1 = driver.findElement(By.xpath("//button[@type='button']"));
        buttonSignin1.click();
        WebElement username = driver.findElement(By.xpath("//input[@name='user_login']"));
        username.sendKeys("username");
        WebElement password = driver.findElement(By.xpath("//input[@name='user_password']"));
        password.sendKeys("password");
        WebElement buttonSignin2 = driver.findElement(By.xpath("//input[@name='submit']"));
        buttonSignin2.click();
        driver.get("http://zero.webappsecurity.com/bank/transfer-funds.html");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(driver.getTitle(),"Zero - Transfer Funds");
        WebElement dropdownUsername = driver.findElement(By.xpath("//body/div[@class='wrapper']/div[@class='navbar navbar-fixed-top']/div[@class='navbar-inner']/div[@class='container']/div/ul[@class='nav float-right']/li[3]/a[1]"));
        dropdownUsername.click();
        WebElement buttonLogout = driver.findElement(By.xpath("//a[normalize-space()='Logout']"));
        buttonLogout.click();
        Assert.assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");
    }
    @AfterMethod
    public void tearDown(){driver.quit();}
}

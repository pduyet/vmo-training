package exercise;

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
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void TC01_SearchComputer() {
        driver.get("https://demo.nopcommerce.com/");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
        WebElement inputSearch = driver.findElement(By.xpath("//input[@placeholder='Search store']"));
        inputSearch.sendKeys("computer");
        //option 1: Get locator search button and click button
        WebElement btnSearch = driver.findElement(By.xpath("//button[@type='submit']"));
        btnSearch.click();
        //option 2:  press enter
        //inputSearch.sendKeys(Keys.ENTER);
    }
    @Test
    public void TC02_LoginToOrangeHRMSystem() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
//        Check UI:
//        WebElement labelUsername = driver.findElement(By.xpath("//label[normalize-space()='Username']"));
//        Assert.assertEquals(labelUsername.getText(),"Username");
//        Assert.assertEquals(labelUsername.getCssValue("color"),"rgba(100, 114, 140, 1)");
//        Assert.assertEquals(labelUsername.getCssValue("font-size"),"12px");
//        WebElement labelPassword = driver.findElement(By.xpath("//label[normalize-space()='Password']"));
//        Assert.assertEquals(labelPassword.getText(),"Password");
//        Assert.assertEquals(labelPassword.getCssValue("color"),"rgba(100, 114, 140, 1)");
//        Assert.assertEquals(labelPassword.getCssValue("font-size"),"12px");
        WebElement inputUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUsername.sendKeys("Admin");
        WebElement inputPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.sendKeys("admin123");
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnLogin.click();
        Thread.sleep(5000);
        WebElement displayDashboard = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
        Assert.assertEquals(displayDashboard.getText(), "Dashboard");
    }
    @Test
    public void TC03_LoginLogoutToZeroWebApp() throws InterruptedException {
        driver.get("http://zero.webappsecurity.com/");
        Assert.assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards");
        WebElement btnSignin = driver.findElement(By.xpath("//button[@id='signin_button']"));
        btnSignin.click();
        WebElement textboxLogin = driver.findElement(By.xpath("//input[@id='user_login']"));
        textboxLogin.sendKeys("username");
        WebElement textboxPassword = driver.findElement(By.xpath("//input[@id='user_password']"));
        textboxPassword.sendKeys("password");
        WebElement btnSigninn = driver.findElement(By.xpath("//input[@name='submit']"));
        btnSigninn.click();
        Thread.sleep(5000);
        driver.navigate().to("http://zero.webappsecurity.com/bank/transfer-funds.html");
        Assert.assertEquals(driver.getTitle(), "Zero - Transfer Funds");
        WebElement dropdownUsername = driver.findElement(By.xpath("//a[normalize-space()='username']"));
        dropdownUsername.click();
        WebElement linkLogout = driver.findElement(By.xpath("//a[@id='logout_link']"));
        linkLogout.click();
        Assert.assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package Topic01_Locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_B01_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_ValidateTitlePage() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.clear();
        searchBox.sendKeys("computer");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test
    public void TC02_LoginOrangeHRMSystem() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(10000);
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        WebElement txtUserName = driver.findElement(By.xpath("(//input[@placeholder='Username'])[1]"));
        txtUserName.clear();
        txtUserName.sendKeys("Admin");
        WebElement txtPassword = driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]"));
        txtPassword.clear();
        txtPassword.sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test
    public void TC03_LoginAndLogoutZeroWebApp() throws InterruptedException {
        driver.get("http://zero.webappsecurity.com/");
        Assert.assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards");
        driver.findElement(By.id("signin_button")).click();
        WebElement txtUserName = driver.findElement(By.id("user_login"));
        txtUserName.clear();
        txtUserName.sendKeys("username");
        WebElement txtPassword = driver.findElement(By.id("user_password"));
        txtPassword.clear();
        txtPassword.sendKeys("password");
        driver.findElement(By.name("submit")).click();
        Thread.sleep(5000);
        driver.get("http://zero.webappsecurity.com/bank/transfer-funds.html");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Zero - Transfer Funds");
        driver.findElement(By.xpath("(//li[@class='dropdown'])[2]")).click();
        driver.findElement(By.id("logout_link")).click();
        Assert.assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

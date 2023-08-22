package Pratices;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_Locator1 {
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

        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.sendKeys("computer");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
    }
    @Test
    public void TC02_LoginToOrangeHRMSystem() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
        Thread.sleep(3000);

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
    }

    @Test
    public void TC03_LoginAndLogoutToZeroWebApp(){
        driver.get("http://zero.webappsecurity.com/");
        Assert.assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");

        WebElement signinButton1 = driver.findElement(By.id("signin_button"));
        signinButton1.click();
        WebElement username = driver.findElement(By.name("user_login"));
        username.sendKeys("username");
        WebElement password = driver.findElement(By.name("user_password"));
        password.sendKeys("password");

        WebElement signinButton2 = driver.findElement(By.name("submit"));
        signinButton2.click();

        driver.get("http://zero.webappsecurity.com/bank/transfer-funds.html");
        Assert.assertEquals(driver.getTitle(),"Zero - Transfer Funds");
        WebElement usernameDropdown = driver.findElement(By.xpath("//i[@class='icon-user']"));
        usernameDropdown.click();
        WebElement logoutLink = driver.findElement(By.xpath("//a[@id='logout_link']"));
        logoutLink.click();
        Assert.assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

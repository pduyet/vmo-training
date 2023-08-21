package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic01_LocatorSession01 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_VerifySearchComputer() {
        String urlPage = "https://demo.nopcommerce.com/";
        driver.get(urlPage);
        System.out.println("page is opening");

        //verify title of page
        String expectedTitle = "nopCommerce demo store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        //Get locator search box and type “computer”
        WebElement txtBoxSearch = driver.findElement(By.id("small-searchterms"));
        txtBoxSearch.sendKeys("computer");

        //Get locator search button and click (or press enter)

        WebElement bntSearch = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
        bntSearch.click();
    }

    @Test
    public void TC02_LoginOrangeHRM() throws InterruptedException {
        String urlPage = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(urlPage);
        System.out.println("page is opening");

        //verify title of page
        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        //Get locator username and password and type username: Admin password: admin123
        Thread.sleep(5000);
        WebElement userName = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        userName.sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("admin123");

        //Get locator button Login and click

        WebElement bntLogin = driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));
        bntLogin.click();
    }

    @Test
    public void TC03_LoginLogOutZeroWebApp() {
        String urlPage = "http://zero.webappsecurity.com/";
        driver.get(urlPage);
        System.out.println("page is opening");

        //verify title of page
        String expectedTitle = "Zero - Personal Banking - Loans - Credit Cards";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        //Get locator button Signin and click
        WebElement bntSignin = driver.findElement(By.id("signin_button"));
        bntSignin.click();

        //Get locator login and password and type
        //login: username
        //password: password
        WebElement userName = driver.findElement(By.id("user_login"));
        userName.sendKeys("username");
        WebElement password = driver.findElement(By.id("user_password"));
        password.sendKeys("password");

        //Get locator button Signin and click
        WebElement bntLogin = driver.findElement(By.xpath("//input[@name='submit']"));
        bntLogin.click();
        driver.navigate().to("http://zero.webappsecurity.com/bank/transfer-funds.html");

        //Validate title “Zero - Transfer Funds”
        Assert.assertEquals(driver.getTitle(), "Zero - Transfer Funds");

        //Get locator dropdown username and click
        WebElement dropdownUserName = driver.findElement(By.xpath("//body/div/div/div/div/div/ul/li[3]/a[1]"));
        dropdownUserName.click();

        WebElement logOut = driver.findElement(By.xpath("//a[@id='logout_link']"));
        logOut.click();

        Assert.assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}



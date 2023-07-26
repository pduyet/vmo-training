package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Locator_Session02_Practice01 {
    WebDriver driver;
    String dataUsername = "Admin";
    String dataPassword = "admin123";
    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    String firstname = "Chung";
    String middlename = "Thi";
    String lastname = "Nguyen";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_VerifyLoginSuccessfull() throws InterruptedException {
        driver.get(url);
        Thread.sleep(3000);
        WebElement txtUsername = driver.findElement(By.xpath("//input[@name='username']"));
        txtUsername.sendKeys(dataUsername);

        WebElement txtPassword = driver.findElement(By.xpath("//input[@name='password']"));
        txtPassword.sendKeys(dataPassword);

        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnLogin.click();
        Thread.sleep(3000);
        WebElement checkDisplay = driver.findElement(By.xpath("//aside[@class='oxd-sidepanel']"));
        Assert.assertTrue(checkDisplay.isDisplayed());

        WebElement menuPim = driver.findElement(By.xpath("//a[contains(@href,'viewPimModule')]"));

       // menuPim.click();
       // System.out.println(menuPim.getCssValue("name"));
        // tại sao sau khi click rồi thì get attribute và cssvalue lại bị lỗi nhỉ

        System.out.println(menuPim.getAttribute("class"));
        if (menuPim.getAttribute("class").contains("active")) {
            System.out.println("yes");
            WebElement btnAddImployee = driver.findElement(By.xpath("//a[text()='Add Employee']"));
            btnAddImployee.click();

        } else {
            menuPim.click();
            Thread.sleep(3000);
            WebElement btnAddImployee = driver.findElement(By.xpath("//a[text()='Add Employee']"));
            btnAddImployee.click();

        }
        Thread.sleep(3000);
        WebElement txtFirstname = driver.findElement(By.xpath("//input[@name='firstName']"));
        txtFirstname.sendKeys(firstname);
        WebElement txtMiddlename = driver.findElement(By.xpath("//input[@name='middleName']"));
        txtMiddlename.sendKeys(middlename);
        WebElement txtLastname = driver.findElement(By.xpath("//input[@name='lastName']"));
        txtLastname.sendKeys(lastname);
        WebElement btnSave = driver.findElement(By.xpath("//button[@type='submit']"));
        btnSave.click();
        Thread.sleep(5000);
        WebElement lableFullname = driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']"));
        Assert.assertEquals(lableFullname.getText(), firstname +" "+ lastname);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

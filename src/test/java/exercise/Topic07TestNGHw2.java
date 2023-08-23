package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

@Listeners
public class Topic07TestNGHw2 {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        //noinspection deprecation
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }
    @Test
    public void TC01_LoginSuccessfully(){
        String username = "Admin";
        String password = "admin123";

        WebElement inputUsername = driver.findElement(By.xpath("//input[@name='username']"));
        inputUsername.clear();
        inputUsername.sendKeys(username);
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        System.out.println("username: "+ inputUsername.getAttribute("value"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(inputUsername.getAttribute("value"),username);
        softAssert.assertEquals(inputPassword.getAttribute("value"),password);
        softAssert.assertAll();
    }
    @Test
    @Parameters ({"username","password"})
    public void TC02_LoginWithWrongData(String usernameParam, String passwordParam){
        WebElement inputUsername = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));
        inputUsername.clear();
        inputPassword.clear();
        inputUsername.sendKeys(usernameParam);
        inputPassword.sendKeys(passwordParam);
        System.out.println("para: username: " + usernameParam);
        System.out.println("para: username: " + passwordParam);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(inputUsername.getAttribute("value"),usernameParam);
        softAssert.assertEquals(inputPassword.getAttribute("value"),passwordParam);
        softAssert.assertAll();

    }
    @Test (dataProvider = "Login_Account")
    public void TC03_LoginWithBlank(String username, String password){
        WebElement inputUsername = driver.findElement(By.xpath("//input[@name='username']"));
        inputUsername.clear();
        inputUsername.sendKeys(username);
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        SoftAssert softAssert = new SoftAssert();
        System.out.println("data provider: username: " + username);
        System.out.println("data provider: password: " + password);
        softAssert.assertEquals(inputUsername.getAttribute("value"),username);
        softAssert.assertEquals(inputPassword.getAttribute("value"),password);
        softAssert.assertAll();
    }
    @DataProvider (name = "Login_Account")
    public Object[][] getUserAndPassword(){
        return new Object[][]{
            {"",""}
        };
    }
    @AfterMethod ()

    public void tearDown(){
    }
}

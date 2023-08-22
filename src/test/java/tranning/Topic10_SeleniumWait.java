package tranning;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic10_SeleniumWait {
    WebDriver driver;
    WebDriverWait wait;
    String UserId;
    String PassWord;
    String CustomerName = "Nguyen Thuy Tien";
    String Gender = "female";
    String DateOfBirth = "1998-10-16";
    String Address = "Bui Xuong Trach";
    String City = "Thanh Xuan";
    String State = "Khuong Dinh";
    String PIN = "100000";
    String MobileNumber = "032974" + generateTelephone();
    String Email = generateEmail();
    String PassWordCustomer = "tien123456";


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @Test
    public void TC02_CreateANewCustomer() {
        driver.get("https://demo.guru99.com/");
        Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
        waitForElementVisible("//input[@name='emailid']");
        driver.findElement(getByXpath("//input[@name='emailid']")).sendKeys(generateEmail());
        waitForElementClickable("//input[@name='btnLogin']");
        driver.findElement(getByXpath("//input[@name='btnLogin']")).click();
        waitForElementPresence("//td[text()='User ID :']//following-sibling::td");
        UserId = driver.findElement(getByXpath("//td[text()='User ID :']//following-sibling::td")).getText();
        waitForElementPresence("//td[text()='Password :']//following-sibling::td");
        PassWord = driver.findElement(getByXpath("//td[text()='Password :']//following-sibling::td")).getText();

        driver.get("https://demo.guru99.com/v4/index.php");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
        waitForElementPresence("//input[@name='uid']");
        driver.findElement(getByXpath("//input[@name='uid']")).sendKeys(UserId);
        waitForElementPresence("//input[@name='password']");
        driver.findElement(getByXpath("//input[@name='password']")).sendKeys(PassWord);
        waitForElementClickable("//input[@name='btnLogin']");
        driver.findElement(getByXpath("//input[@name='btnLogin']")).click();
        waitForElementPresence("//marquee[@behavior='alternate']");
        Assert.assertEquals(driver.findElement(getByXpath("//marquee[@behavior='alternate']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
        waitForElementClickable("//a[text()='New Customer']");
        driver.findElement(getByXpath("//a[text()='New Customer']")).click();


        driver.findElement(getByXpath("//input[@name='name']")).sendKeys(CustomerName);
        driver.findElement(getByXpath("//input[@value='f']")).click();
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys(DateOfBirth);
        driver.findElement(getByXpath("//textarea[@name='addr']")).sendKeys(Address);
        driver.findElement(getByXpath("//input[@name='city']")).sendKeys(City);
        driver.findElement(getByXpath("//input[@name='state']")).sendKeys(State);
        driver.findElement(getByXpath("//input[@name='pinno']")).sendKeys(PIN);
        driver.findElement(getByXpath("//input[@name='telephoneno']")).sendKeys(MobileNumber);
        driver.findElement(getByXpath("//input[@name='emailid']")).sendKeys(Email);
        driver.findElement(getByXpath("//input[@name='password']")).sendKeys(PassWordCustomer);
        driver.findElement(getByXpath("//input[@name='sub']")).click();

        Assert.assertEquals(driver.findElement(getByXpath("//p[@align='center']")).getText(), "Customer Registered Successfully!!!");

        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DateOfBirth);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='City']/following-sibling::td")).getText(), City);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='State']/following-sibling::td")).getText(), State);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), MobileNumber);
        Assert.assertEquals(driver.findElement(getByXpath("//td[text()='Email']/following-sibling::td")).getText(), Email);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    private int generateTelephone() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public By getByXpath(String Locator) {
        return By.xpath(Locator);
    }
    

    public void waitForElementVisible(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated((getByXpath(locator))));
    }

    public void waitForElementInVisible(String locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }
    
    public void waitForElementClickable(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForElementPresence(String locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

}

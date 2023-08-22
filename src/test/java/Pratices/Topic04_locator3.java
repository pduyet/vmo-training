package Pratices;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic04_locator3 {
    WebDriver driver;
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
    String FirstName = "Nguyen";
    String LastName = "Thuy Tien";


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_RegisterUser() throws InterruptedException {
        driver.get("http://www.automationpractice.pl/index.php");
        Assert.assertEquals(driver.getTitle(), "My Shop");
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(Email);
        driver.findElement(By.xpath("//h1[@class='page-heading']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email_create']/parent::div")).getAttribute("class").contains("form-ok"));
        driver.findElement(By.xpath("//button[@id='SubmitCreate']//span")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h3[@class='page-subheading']")).getText(), "YOUR PERSONAL INFORMATION");

        driver.findElement(By.xpath("//label[@for='id_gender2']")).click();
        driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(FirstName);
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(LastName);
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(PassWordCustomer);
        driver.findElement(By.xpath("//h1[@class='page-heading']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='customer_firstname']/parent::div")).getAttribute("class").contains("form-ok"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='customer_lastname']/parent::div")).getAttribute("class").contains("form-ok"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='passwd']/parent::div")).getAttribute("class").contains("form-ok"));

        Select selectDay = new Select(driver.findElement(By.id("days")));
        selectDay.selectByValue("16");
        Select selectMonth = new Select(driver.findElement(By.id("months")));
        selectMonth.selectByValue("10");
        Select selectYear = new Select(driver.findElement(By.id("years")));
        selectYear.selectByValue("1998");
        driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText(), "Your account has been created.");
        driver.findElement(By.xpath("//a[@title='Information']//span")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"), FirstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"), LastName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"), Email);
        Assert.assertEquals("16", driver.findElement(By.xpath("//select[@id='days']//option[@selected]")).getAttribute("value"));
        Assert.assertEquals("10", driver.findElement(By.xpath("//select[@id='months']//option[@selected]")).getAttribute("value"));
        Assert.assertEquals("1998", driver.findElement(By.xpath("//select[@id='years']//option[@selected]")).getAttribute("value"));
    }


    @Test
    public void TC02_CreateANewCustomer() {
        driver.get("https://demo.guru99.com/");
        Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        UserId = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
        PassWord = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

        driver.get("https://demo.guru99.com/v4/index.php");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(UserId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PassWord);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@behavior='alternate']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(CustomerName);
        driver.findElement(By.xpath("//input[@value='f']")).click();
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys(DateOfBirth);
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(Address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(City);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(State);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(PIN);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(MobileNumber);
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PassWordCustomer);
        driver.findElement(By.xpath("//input[@name='sub']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@align='center']")).getText(), "Customer Registered Successfully!!!");

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DateOfBirth);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), MobileNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email);
    }

    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    private int generateTelephone() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

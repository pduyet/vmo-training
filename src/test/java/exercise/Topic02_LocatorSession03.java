package exercise;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Locale;

public class Topic02_LocatorSession03 {
    WebDriver driver;
    private String generateEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();}
    private String randomPhoneNumber(){
        Locale locale = new Locale("vi-VN");
        Faker faker = new Faker(locale);
        return faker.phoneNumber().cellPhone().replaceAll(" ","");
    }
    String email = generateEmail();
    String phoneNumber = randomPhoneNumber();
    String name = "Ngo Anh Thaii";
    String dob = "1999-01-01";
    String address = "Dinh Congg";
    String city = "Ha Noii";
    String state = "Texass";
    String pin = "100898";
    String pw = "4Youonly5";
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void HW03_RegisterUser() throws InterruptedException {
        driver.get("http://www.automationpractice.pl/index.php");
        Assert.assertEquals(driver.getTitle(),"My Shop");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
        driver.findElement(By.xpath("//html")).click();
        WebElement verifyCreateEmail = driver.findElement(By.xpath("//div[@class='form-group form-ok']"));
        Assert.assertTrue(verifyCreateEmail.isDisplayed());
        driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']")).click();
        Thread.sleep(3000);
        String lblCreateAnAccount = driver.findElement(By.xpath("//label[text()='Title']")).getText();
        Assert.assertTrue(lblCreateAnAccount.contains("Title"));
        driver.findElement(By.xpath("//label[text()='Title']/following::div[@class='radio-inline'][1]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'First name')]/following-sibling::input[@type='text']")).sendKeys("Thai");
        driver.findElement(By.xpath("//label[contains(text(),'Last name')]/following-sibling::input[@type='text']")).sendKeys("Ngo");
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following-sibling::input[@type='text']")).click();
        driver.findElement(By.xpath("//html")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::input[@type='password']")).sendKeys("4Youonly4");
        driver.findElement(By.xpath("(//option[@value='2']/preceding::option[@value='1'])[1]")).click();
        driver.findElement(By.xpath("(//option[@value='2']/preceding::option[@value='1'])[2]")).click();
        driver.findElement(By.xpath("//option[@value='1998']/preceding::option[@value='1999']")).click();
        WebElement verifyFirstName = driver.findElement(By.xpath("//label[@for='customer_firstname']/parent::div[@class='required form-group form-ok']"));
        Assert.assertTrue(verifyFirstName.isDisplayed());
        WebElement verifyLastName = driver.findElement(By.xpath("//label[@for='customer_lastname']/parent::div[@class='required form-group form-ok']"));
        Assert.assertTrue(verifyLastName.isDisplayed());
        WebElement verifyEmail = driver.findElement(By.xpath("//label[@for='email']/parent::div[@class='required form-group form-ok']"));
        Assert.assertTrue(verifyEmail.isDisplayed());
        WebElement verifyPassword = driver.findElement(By.xpath("//label[@for='passwd']/parent::div[@class='required password form-group form-ok']"));
        Assert.assertTrue(verifyPassword.isDisplayed());
        driver.findElement(By.xpath("//span[text()='Register']/parent::button")).click();
        Thread.sleep(3000);
        String titleCreateSuccessfully = driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
        Assert.assertTrue(titleCreateSuccessfully.contains("Your account has been created"));
    }
    @Test
    public void HW04_CreateANewCustomer() throws InterruptedException {
        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String userName = driver.findElement(By.xpath("//td[contains(text(),'User ID :')]/following-sibling::td")).getText();
        String password = driver.findElement(By.xpath("//td[contains(text(),'Password :')]/following-sibling::td")).getText();
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        WebElement title = driver.findElement(By.xpath("//marquee[@class='heading3']"));
        Assert.assertTrue(title.isDisplayed());
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@value='m']")).click();
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');",dobInput);
        dobInput.sendKeys("1999-01-01");
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        String lblName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertTrue(lblName.contains(name));
        String lblGender = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        Assert.assertTrue(lblGender.contains("male"));
        String lblBirthdate = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        Assert.assertTrue(lblBirthdate.contains(dob));
        String lblAddress = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertTrue(lblAddress.contains(address));
        String lblCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertTrue(lblCity.contains(city));
        String lblState = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertTrue(lblState.contains(state));
        String lblPin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertTrue(lblPin.contains(pin));
        String lblMobile = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertTrue(lblMobile.contains(phoneNumber));
        String lblEmail = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertTrue(lblEmail.contains(email));
    }
    @AfterMethod
    public void teardown(){driver.quit();}
}

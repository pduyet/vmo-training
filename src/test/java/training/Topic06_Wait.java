package training;

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
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic06_Wait {
    WebDriver driver;
    WebDriverWait wait;
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
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void HW04_CreateANewCustomer() throws InterruptedException {
        driver.get("https://demo.guru99.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='emailid']")));
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String userName = driver.findElement(By.xpath("//td[contains(text(),'User ID :')]/following-sibling::td")).getText();
        String password = driver.findElement(By.xpath("//td[contains(text(),'Password :')]/following-sibling::td")).getText();
        driver.get("https://demo.guru99.com/v4/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='uid']")));
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
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
    public void teardown(){
        driver.quit();
    }
}

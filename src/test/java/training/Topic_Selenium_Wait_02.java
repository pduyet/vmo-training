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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_Selenium_Wait_02 {

    WebDriver driver;
    String userId;
    String password;
    boolean isLogined; // khi init la false
    String customerName = "Chung NT";
    String dateOfBirth = "01/01/1996";
    String gender;
    String address = "Duy Tan\ncau giay";
    String city = "Ha Noi";
    String state = "Viet Nam";
    String pin = "123456";

    String mobileNumber;

    String emailCustomer;
    String passwordCustomer;
    WebDriverWait driverWait;


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

      //  String pathTextEmail = "By.xpath(\"//input[@name='emailid']\")";
        TC_getUsernamePw();

    }

    public void TC_getUsernamePw(){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='emailid']")));
        WebElement txtEmail = driver.findElement(By.xpath("//input[@name='emailid']"));
        txtEmail.sendKeys(generateEmail());

        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='btnLogin']"))).click();;

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td[@align='center']")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td[@align='center']")).getText();



    }

    @Test
    public void TC01_VerifyLogin() throws InterruptedException, ParseException {
        driver.get("https://demo.guru99.com/v4");

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='uid']")));
        WebElement txtUserId = driver.findElement(By.xpath("//input[@name='uid']"));
        txtUserId.sendKeys(userId);

        WebElement txtPassword = driver.findElement(By.xpath("//input[@name='password']"));
        txtPassword.sendKeys(password);
        WebElement btnLogin = driver.findElement(By.xpath("//input[@type='submit']"));
        btnLogin.click();
        WebElement labelSuccessfullyLogin = driver.findElement(By.xpath("//marquee[@behavior='alternate']"));
        System.out.println(isLogined);
        Assert.assertTrue(labelSuccessfullyLogin.getText().equals("Welcome To Manager's Page of Guru99 Bank"));
        isLogined = true;
        System.out.println(isLogined);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='New Customer']"))).click();
      //  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
      //  Thread.sleep(2000);
        WebElement txtName = driver.findElement(By.xpath("//input[@name='name']"));
        txtName.sendKeys(customerName);
        WebElement inputGender = driver.findElement((By.xpath("//input[@type='radio' and @value='f']")));
        // gender = inputGender.getText(); // sao hàm này trả ve empty nhi?
        gender = inputGender.getAttribute("value");
        System.out.println("Gender is " + gender);
        inputGender.click();
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys((CharSequence) dateOfBirth);
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
        mobileNumber = generatePhonenumber();
        // mobileNumber = generatePhonenumberByFaker();
        System.out.println(mobileNumber);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
        emailCustomer = generateEmail();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailCustomer);
        passwordCustomer = generatePassword();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordCustomer);
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        Thread.sleep(3000);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='customer']//p")));
        WebElement labelRegistered = driver.findElement(By.xpath("//table[@id='customer']//p"));
        Assert.assertEquals(labelRegistered.getText(), "Customer Registered Successfully!!!");

        //Verify customer
        String actualCustomerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertEquals(actualCustomerName, customerName);
        String actualGender = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        if (gender.equals("f")) gender = "female";
        Assert.assertEquals(actualGender, gender);
        String actualBirthdate = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        System.out.println(formatDate());
        Assert.assertEquals(actualBirthdate, formatDate());
        String actualAddress = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertTrue(actualAddress.contains(address.replace("\n", " ")));
        String actualCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertEquals(actualCity, city);
        String actualState = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertEquals(actualState, state);
        String actualPin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertEquals(actualPin, pin);
        String actualPhoneNumber = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertEquals(actualPhoneNumber, mobileNumber);
        String actualMail = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertEquals(actualMail, emailCustomer);

    }

    public boolean isElementNotDisplayed(By by, int timeout) {

        List<WebElement> elementList = driver.findElements(by);
        if (elementList.isEmpty()) {
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        return true;
    }

    public String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public String generatePassword() {
        Faker faker = new Faker();
        return faker.internet().password();
    }

    public String generatePhonenumber() {
        StringBuilder phoneNumber = new StringBuilder("03");
        Random rd = new Random();
        while (phoneNumber.length() < 10) {
            phoneNumber.append(rd.nextInt(9));
        }
        return phoneNumber.toString();
    }

    public String generatePhonenumberByFaker() {
        Locale locale = new Locale("vi-VN");
        Faker faker = new Faker();
        return faker.phoneNumber().cellPhone().replace(" ", "");
    }

    public String formatDate() throws ParseException {
        SimpleDateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateOut = new SimpleDateFormat("yyyy-MM-dd");
        return dateOut.format(dateIn.parse(dateOfBirth));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}



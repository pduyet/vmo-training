package training;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class Webdriver_Session02 {
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


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/");
        WebElement txtEmail = driver.findElement(By.xpath("//input[@name='emailid']"));
        txtEmail.sendKeys(generateEmail());
        WebElement btnSubmit = driver.findElement(By.xpath("//input[@name='btnLogin']"));
        btnSubmit.click();
        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td[@align='center']")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td[@align='center']")).getText();

    }

    @Test
    public void TC01_VerifyLogin() throws InterruptedException, ParseException {
        driver.get("https://demo.guru99.com/v4");
        WebElement txtUserId = driver.findElement(By.xpath("//input[@name='uid']"));
        txtUserId.sendKeys(userId);
        WebElement txtPassword = driver.findElement(By.xpath("//input[@name='password']"));
        txtPassword.sendKeys(password);
        System.out.println("user id: " + userId);
        System.out.println("password: " + password);
        WebElement btnLogin = driver.findElement(By.xpath("//input[@type='submit']"));
        btnLogin.click();
        WebElement labelSuccessfullyLogin = driver.findElement(By.xpath("//marquee[@behavior='alternate']"));
        System.out.println(isLogined);
        Assert.assertTrue(labelSuccessfullyLogin.getText().equals("Welcome To Manager's Page of Guru99 Bank"));
        isLogined = true;
        System.out.println(isLogined);

        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        Thread.sleep(2000);
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
        mobileNumber= generatePhonenumber();
        // mobileNumber = generatePhonenumberByFaker();
        System.out.println(mobileNumber);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
        emailCustomer = generateEmail();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailCustomer);
        passwordCustomer = generatePassword();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordCustomer);
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        Thread.sleep(3000);
        WebElement labelRegistered = driver.findElement(By.xpath("//table[@id='customer']//p"));
        Assert.assertEquals(labelRegistered.getText(), "Customer Registered Successfully!!!");

        //Verify customer

        String actualCustomerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertEquals(actualCustomerName,customerName);
        String actualGender = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        if (gender.equals("f")) gender = "female";
        Assert.assertEquals(actualGender,gender);
        String actualBirthdate = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        System.out.println(formatDate());
        Assert.assertEquals(actualBirthdate,formatDate());
        String actualAddress = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertTrue(actualAddress.contains(address.replace("\n"," ")));
        String actualCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertEquals(actualCity,city);
        String actualState = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertEquals(actualState,state);
        String actualPin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertEquals(actualPin,pin);
        String actualPhoneNumber = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertEquals(actualPhoneNumber,mobileNumber);
        String actualMail = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertEquals(actualMail,emailCustomer);

        String idCustomer = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
        System.out.println("customer id: " + idCustomer);

        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(idCustomer);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        WebElement customerNameInput= driver.findElement(By.name("name"));
        WebElement customerNameInputEdit = driver.findElement(By.xpath("//input[@name='name']"));
        js.executeScript("arguments[0].removeAttribute('disabled');", customerNameInputEdit);
        customerNameInputEdit.clear();
        customerNameInputEdit.sendKeys("Chung edit");

        Assert.assertEquals(customerNameInputEdit.getAttribute("value"),"Chung edit");


    }

    public String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public String generatePassword() {
        Faker faker = new Faker();
        return faker.internet().password();
    }
    public String generatePhonenumber(){
        StringBuilder phoneNumber = new StringBuilder("03");
        Random rd = new Random();
        while (phoneNumber.length()<10){
            phoneNumber.append(rd.nextInt(9));
        }
        return phoneNumber.toString();
    }
    public String generatePhonenumberByFaker(){
        Locale locale = new Locale("vi-VN");
        Faker faker = new Faker();
        return faker.phoneNumber().cellPhone().replace(" ","");
    }
    public String formatDate() throws ParseException {
        SimpleDateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateOut = new SimpleDateFormat("yyyy-MM-dd");
        return dateOut.format(dateIn.parse(dateOfBirth));
    }

//    @AfterMethod
//  //  public void tearDown() {
//        driver.quit();
//    }
}

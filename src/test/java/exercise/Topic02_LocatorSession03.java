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
    String userID;
    String password;
    String inputValue[] = {
            "Hau",
            "Do",
            "Pw12345"

    };

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void TC01_Loginsuccess() throws InterruptedException {
        driver.get("http://www.automationpractice.pl/index.php?");
        Assert.assertEquals(driver.getTitle(),"My Shop");
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//h3[normalize-space()='Create an account']")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'form-ok')]")).isDisplayed();
        driver.findElement(By.xpath("//span[normalize-space()='Create an account']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='Title']/following::div[@class='radio-inline'][2]")).click();
        driver.findElement(By.xpath("//label[text()='First name ']/following-sibling::input")).sendKeys(inputValue[0]);
        driver.findElement(By.xpath("//label[text()='Last name ']/following-sibling::input")).sendKeys(inputValue[1]);
        driver.findElement(By.xpath("//label[text()='Email ']/following-sibling::input")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::input")).sendKeys(inputValue[2]);
        driver.findElement(By.xpath("(//option[@value='13']/preceding::option[@value='12'])[1]")).click();
        driver.findElement(By.xpath("(//option[@value='11']/preceding::option[@value='10'])[2]")).click();
        driver.findElement(By.xpath("//option[@value='1999']/preceding-sibling::option[@value='2000']")).click();

        driver.findElement(By.xpath("//input[@onkeyup=\"$('#firstname').val(this.value);\"]/parent::div[@class='required form-group form-ok']")).isDisplayed();

        driver.findElement(By.xpath("//input[@onkeyup=\"$('#lastname').val(this.value);\"]/parent::div[@class='required form-group form-ok']")).isDisplayed();

        driver.findElement(By.xpath("//input[@id='email']/parent::div[@class='required form-group form-ok']")).isDisplayed();

        driver.findElement(By.xpath("//input[@id='passwd']/parent::div[@class='required password form-group form-ok']")).isDisplayed();

        driver.findElement(By.xpath("//span[normalize-space()='Register']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(),"My account - My Shop");
        String titleSuccess = driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
        Assert.assertTrue(titleSuccess.contains("Your account has been created"));

    }


    @Test
    public  void  TC02_CreateNewCustomer () throws InterruptedException {
        String customerName = "Haudt";
        String dateOfBirth = "2000-10-12";
        String address = "Thai Thuy";
        String city = "Thai binh";
        String state = "In a relationship";
        String pin = "111111";
        String numberPhone = generatePhone();
        String email = generateEmail();
        String password = "Pw12345";

        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

        driver.navigate().to("https://demo.guru99.com/v4/index.php");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        Assert.assertEquals(driver.getTitle(),"Guru99 Bank Manager HomePage");
        driver.findElement(By.cssSelector("marquee[class='heading3']")).isDisplayed();

        driver.findElement(By.xpath("//a[normalize-space()='New Customer']")).click();

        driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input")).sendKeys(customerName);
        driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td/input[@value='f']")).click();

        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');",dobInput);
        dobInput.sendKeys(dateOfBirth);

        driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).sendKeys(address);

        driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).sendKeys(city);

        driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).sendKeys(state);

        driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).sendKeys(pin);

        driver.findElement(By.xpath("//td[text()='Mobile Number']/following-sibling::td/input")).sendKeys(numberPhone);

        driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).sendKeys(email);

        driver.findElement(By.xpath("//td[text()='Password']/following-sibling::td/input")).sendKeys(password);

        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        Thread.sleep(3000);

        String verifyRegisterSuccess = driver.findElement(By.cssSelector(".heading3")).getText();
        Assert.assertTrue(verifyRegisterSuccess.contains("Customer Registered Successfully!!!"));

        String labelName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertTrue(labelName.contains(customerName));

        String labelGender = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        Assert.assertTrue(labelGender.contains("male"));

        String labelBirth = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        Assert.assertTrue(labelBirth.contains(dateOfBirth));

        String labelAdd = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertTrue(labelAdd.contains(address));

        String labelCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertTrue(labelCity.contains(city));

        String labelState = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertTrue(labelState.contains(state));

        String labelPin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertTrue(labelPin.contains(pin));

        String labelMobile = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertTrue(labelMobile.contains(numberPhone));

        String labelEmail = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertTrue(labelEmail.contains(email));

    }
    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    };
    private String generatePhone() {
        Locale locale = new Locale("vi-VN");
        Faker faker = new Faker(locale);
        return faker.phoneNumber().cellPhone().replaceAll(" ","");
    };
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

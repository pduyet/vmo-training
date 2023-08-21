package Exercise;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Topic02_LocatorSession03 {
    WebDriver driver;
    String UserID;
    String Password; 


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void HW03_RegisterUser() throws InterruptedException {

        String email = generateEmail();
        String firtname = "Mai";
        String lastname = "Dung";
        String password = "123456789";


        driver.get("http://www.automationpractice.pl/index.php");
        Assert.assertEquals(driver.getTitle(),"My Shop");
        Thread.sleep(5000);
        WebElement Sigin = driver.findElement(By.xpath("//a[@class='login']"));
        Sigin.click();
        Thread.sleep(5000);
        WebElement Username = driver.findElement((By.xpath("//input[@id='email_create']")));
        Username.sendKeys(email);
        Thread.sleep(5000);
        WebElement Clickoutside = driver.findElement(By.xpath("//p[contains(text(),'Please enter your email address to create an account.')]"));
        Clickoutside.click();
        Thread.sleep(5000);
        WebElement VerifyDisplayed = driver.findElement((By.xpath("//div[@class='form-group form-ok']")));
        VerifyDisplayed.isDisplayed();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement Btnsubmit = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
        Btnsubmit.click();
        Thread.sleep(5000);

        WebElement Firstname = driver.findElement((By.xpath("//input[@id='customer_firstname']")));
        Firstname.sendKeys(firtname);
        WebElement Lastname = driver.findElement((By.xpath("//input[@id='customer_lastname']")));
        Lastname.sendKeys(lastname);
        WebElement Email = driver.findElement((By.xpath("//input[@id='email']")));
        Email.click();
        WebElement Password = driver.findElement((By.xpath("//input[@id='passwd']")));
        Password.sendKeys(password);
        WebElement clickoutside = driver.findElement((By.xpath("//label[contains(text(),'Date of Birth')]")));
        clickoutside.click();

        WebElement Verify_Firstname = driver.findElement((By.xpath("(//div[@class='required form-group form-ok'])[1]")));
        Verify_Firstname.isDisplayed();
        WebElement Verify_Lastname = driver.findElement((By.xpath("(//div[@class='required form-group form-ok'])[2]")));
        Verify_Lastname.isDisplayed();
        WebElement Verify_Email = driver.findElement((By.xpath("(//div[@class='required form-group form-ok'])[3]")));
        Verify_Email.isDisplayed();
        WebElement Verify_Password = driver.findElement((By.xpath("//div[@class='required password form-group form-ok']")));
        Verify_Password.isDisplayed();

        WebElement BtnRigister = driver.findElement((By.xpath("//button[@id ='submitAccount']")));
        BtnRigister.click();
        Assert.assertEquals(driver.getTitle(),"My account - My Shop");
    };

    @Test
    public void HW04_CreateNewCustomer() throws InterruptedException, ParseException {
        String name = "Dung";
        String date = "15-08-2020";
        String address = "Tran Cung";
        String city = "HaNoi";
        String state = "TrungBinh";
        String pin = "222233";
        String phone = randomphone();
        String email = generateEmail();

        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//input[@name ='emailid']")).sendKeys(generateEmail());
        WebElement Submit = driver.findElement(By.xpath("//input[@type='submit']"));
        Submit.click();
        UserID = driver.findElement((By.xpath("//td[text() = 'User ID :']/following-sibling::td"))).getText().trim();
        Password = driver.findElement((By.xpath("//td[text() = 'Password :']/following-sibling::td"))).getText().trim();

        driver.get("https://demo.guru99.com/V4/");
        Thread.sleep(5000);

        WebElement Username = driver.findElement((By.xpath("//input[@type = 'text']")));
        Username.sendKeys(UserID);
        WebElement PassWord = driver.findElement((By.xpath("//input[@type='password']")));
        PassWord.sendKeys(Password);
        WebElement BtnLogin = driver.findElement((By.xpath("//input[@type='submit']")));
        BtnLogin.click();
        Assert.assertEquals(driver.getTitle(),"Guru99 Bank Manager HomePage");
        WebElement NewCustormer = driver.findElement((By.xpath("//a[contains(text(),'New Customer')]")));
        NewCustormer.click();
        Thread.sleep(5000);
        WebElement CustomerName = driver.findElement((By.xpath("//input[@name = 'name' ]")));
        CustomerName.sendKeys(name);
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys(date);
        WebElement Address  = driver.findElement((By.xpath("//textarea[@name = 'addr' ]")));
        Address.sendKeys(address);
        WebElement City  = driver.findElement((By.xpath("//input[@name = 'city' ]")));
        City.sendKeys(city);
        WebElement State  = driver.findElement((By.xpath("//input[@name = 'state' ]")));
        State.sendKeys(state);
        WebElement PIM  = driver.findElement((By.xpath("//input[@name = 'pinno' ]")));
        PIM.sendKeys(pin);
        WebElement Phone  = driver.findElement((By.xpath("//input[@name = 'telephoneno' ]")));
        Phone.sendKeys(phone);
        WebElement Email  = driver.findElement((By.xpath("//input[@name = 'emailid' ]")));
        Email.sendKeys(email);
        WebElement Pass = driver.findElement((By.xpath("//input[@name = 'password' ]")));
        Pass.sendKeys(Password);
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement BtnSubmit = driver.findElement((By.xpath("//input[@name = 'sub' ]")));
        BtnSubmit.click();
        Thread.sleep(5000);
        WebElement CreateCustomerSuccessfully = driver.findElement((By.xpath("//p[@class='heading3']")));
        Assert.assertTrue(CreateCustomerSuccessfully.getText().contains("Customer Registered Successfully!!!"));

        WebElement VerifyName = driver.findElement((By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")));
        Assert.assertTrue(VerifyName.getText().contains(name));

        WebElement VerifyGender = driver.findElement((By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")));
        Assert.assertTrue(VerifyGender.getText().contains("male"));

        WebElement VerifyBirthdate = driver.findElement((By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")));
        String VerifyDateStr = VerifyBirthdate.getText();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = dateFormat.parse(VerifyDateStr);
        String VerifyDateParse = dateFormat2.format(startDate);
        Assert.assertTrue(VerifyDateParse.contains(date));

        WebElement VerifyAddress = driver.findElement((By.xpath("//td[contains(text(),'Address')]/following-sibling::td")));
        Assert.assertTrue(VerifyAddress.getText().contains(address));

        WebElement VerifyCity = driver.findElement((By.xpath("//td[contains(text(),'City')]/following-sibling::td")));
        Assert.assertTrue(VerifyCity.getText().contains(city));

        WebElement VerifyState = driver.findElement((By.xpath("//td[contains(text(),'State')]/following-sibling::td")));
        Assert.assertTrue(VerifyState.getText().contains(state));

        WebElement VerifyPin = driver.findElement((By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")));
        Assert.assertTrue(VerifyPin.getText().contains(pin));

        WebElement VerifyPhone = driver.findElement((By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")));
        Assert.assertTrue(VerifyPhone.getText().contains(phone));

        WebElement VerifyEmail = driver.findElement((By.xpath("//td[contains(text(),'Email')]/following-sibling::td")));
        Assert.assertTrue(VerifyEmail.getText().contains(email));

    }

    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    };

    private String randomphone() {
        Locale locale = new Locale("vi-VN");
        Faker faker = new Faker(locale);
        return faker.phoneNumber().cellPhone().replaceAll(" ","");
    };





    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

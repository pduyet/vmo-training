package training;

import com.github.javafaker.Faker;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic05_TextBoxTextAreaDropdown01 {
    WebDriver driver;
    String firstName = "Alissa";
    String lastName = "Petteri";
    String password = "Ali1234#";
    String dob = "1977-07-07";
    String address = "34 Le Duc Tho";
    String city = "Ha Noi";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void CreateANewCustomer() {
        driver.get("https://demo.guru99.com/");
        Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");

        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        String userId = driver.findElement(By.xpath("//td[contains(text(),'User ID')]/following-sibling::td")).getText();
        String passId = driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();

        driver.get("https://demo.guru99.com/v4/index.php");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passId);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/manager/Managerhomepage.php");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText(), "Manger Id : " + userId);

        driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@value='f']")).click();

        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys(dob);

        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
        String phone = generatePhoneNo();
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phone);
        String email = generateEmail();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(generatePass(10));
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//p[text()]")).getText(), "Customer Registered Successfully!!!");

        WebElement lblCustomerId = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td"));
        Assert.assertTrue(lblCustomerId.isDisplayed());

        String lblCustomerName = driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText();
        Assert.assertEquals(lblCustomerName, firstName);

        String lblGerder = driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText();
        Assert.assertEquals(lblGerder, "female");

        String lblDob = driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText();
        Assert.assertEquals(lblDob, dob);

        String lblAddr = driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText();
        Assert.assertEquals(lblAddr, address);

        String lblCity = driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText();
        Assert.assertEquals(lblCity, city);

        String lblState = driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText();
        Assert.assertEquals(lblState, city);

        String lblPin = driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText();
        Assert.assertEquals(lblPin, "123456");

        String lblMobile = driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText();
        Assert.assertEquals(lblMobile, phone);

        String lblEmail = driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText();
        Assert.assertEquals(lblEmail, email);

        String customerId = lblCustomerId.getText();
        driver.get("https://demo.guru99.com/v4/manager/EditCustomer.php");
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
        driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

        WebElement customerNameEdit = driver.findElement(By.name("name"));
        js.executeScript("arguments[0].removeAttribute('disabled');", customerNameEdit);
        customerNameEdit.sendKeys("Test");
        Assert.assertEquals(customerNameEdit.getAttribute("value"), firstName + "Test");

        WebElement genderEdit = driver.findElement(By.name("gender"));
        js.executeScript("arguments[0].removeAttribute('disabled');", genderEdit);
        genderEdit.sendKeys("Test");
        Assert.assertEquals(genderEdit.getAttribute("value"), lblGerder + "Test");

        WebElement dateOfBirthEdit = driver.findElement(By.name("dob"));
        js.executeScript("arguments[0].removeAttribute('disabled');", dateOfBirthEdit);
        dateOfBirthEdit.sendKeys("Test");
        Assert.assertEquals(dateOfBirthEdit.getAttribute("value"), lblDob + "Test");

        WebElement addrEdit = driver.findElement(By.name("addr"));
        addrEdit.sendKeys("Test");
        Assert.assertEquals(addrEdit.getAttribute("value"), lblAddr + "Test");

        WebElement cityEdit = driver.findElement(By.name("city"));
        cityEdit.sendKeys("Test");
        Assert.assertEquals(cityEdit.getAttribute("value"), lblCity + "Test");

        WebElement stateEdit = driver.findElement(By.name("state"));
        stateEdit.sendKeys("Test");
        Assert.assertEquals(stateEdit.getAttribute("value"), lblState + "Test");

        WebElement phoneNoEdit = driver.findElement(By.name("telephoneno"));
        phoneNoEdit.sendKeys("5");
        Assert.assertEquals(phoneNoEdit.getAttribute("value"), lblMobile + "5");

        WebElement emailEdit = driver.findElement(By.name("emailid"));
        emailEdit.sendKeys("Test");
        Assert.assertEquals(emailEdit.getAttribute("value"), lblEmail + "Test");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public String generateEmail() {
        Faker fake = new Faker();
        return fake.internet().emailAddress();
    }

    public String generatePhoneNo() {
        StringBuilder phone = new StringBuilder(9);
        Random rd = new Random();
        while (phone.length() < 10) {
            phone.append(rd.nextInt(9));
        }
        return phone.toString();
    }

    public String generatePass(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        return pwdGenerator.generate(length);
    }
}

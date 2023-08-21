package training;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

public class Topic03_TextboxSession01 {

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

            String valueID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

            driver.findElement(By.xpath("//a[normalize-space()='Edit Customer']")).click();
            driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(valueID);
            driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

            String textName = "hienvtt";
            WebElement valueName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input"));

            js.executeScript("arguments[0].removeAttribute('disabled');", valueName);
            valueName.clear();
            valueName.sendKeys(textName);
            Assert.assertEquals(valueName.getAttribute("value"), textName);


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
//        @AfterMethod
//        public void tearDown(){
//            driver.quit();
//        }

}

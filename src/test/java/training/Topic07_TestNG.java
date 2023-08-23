package training;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class Topic07_TestNG {
    WebDriver driver;
    String userId;
    String password;
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite: ");
    }

    @BeforeMethod
    public void setup() {
  //      driver = new ChromeDriver();
  //      driver.manage().window().maximize();
//        driver.get("https://demo.guru99.com/");
//        WebElement txtEmail = driver.findElement(By.xpath("//input[@name='emailid']"));
//        txtEmail.sendKeys(generateEmail());
//        WebElement btnSubmit = driver.findElement(By.xpath("//input[@name='btnLogin']"));
//        btnSubmit.click();
//        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td[@align='center']")).getText();
//        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td[@align='center']")).getText();

    }
    @Test (dataProvider = "Loginid")
    public void TC_Login(String userId, String password){
        driver.get("https://demo.guru99.com/v4");
        WebElement txtUserId = driver.findElement(By.xpath("//input[@name='uid']"));
        txtUserId.sendKeys(userId);
        WebElement txtPassword = driver.findElement(By.xpath("//input[@name='password']"));
        txtPassword.sendKeys(password);
        WebElement btnLogin = driver.findElement(By.xpath("//input[@type='submit']"));
        btnLogin.click();
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
    @DataProvider (name = "Loginid")
    public Object[][] getIdAccount(){
        return  new Object[][]
        {
                {"Guru99","India"},
                {"Krishna","UK"}

        };
    }
    @Test
    @Parameters ({"chrome","role"})
    public void TC_para(@Optional("firefox") String chrome,@Optional("USER") String role){


    }
    SoftAssert sa = new SoftAssert();

    @Test
    public void softAssertTest()
    {
        sa.assertEquals("testing","testingDocs.com", "Soft Assert One");
        sa.assertEquals("testingDoc.com","testingDocs.com", "Soft Assert Two");
        sa.assertFalse(true, "Soft Assert Three");
        sa.assertEquals(2,3, "Soft Assert Four");
        sa.assertAll();
    }


}

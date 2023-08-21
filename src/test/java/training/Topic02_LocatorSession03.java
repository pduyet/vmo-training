package training;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_LocatorSession03 {
    WebDriver driver;
    String userID;
    String password;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }
    @Test
    public void TC01_Loginsuccess() {
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        driver.findElement(By.cssSelector("marquee[class='heading3']")).isDisplayed();
        driver.findElement(By.xpath("//a[normalize-space()='New Customer']")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys();
    }
    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}

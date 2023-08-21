package training;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic03_LocatorSession03 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void VerfyGuru() {
        driver.get("https://demo.guru99.com/");
        String email = generateEmail();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        String userId = driver.findElement(By.xpath("//td[contains(text(),'User')]/following-sibling::td")).getText();
        String password = driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();
        driver.get("https://demo.guru99.com/v4");

        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Anyone");
        driver.findElement(By.xpath("//input[@value='f']")).click();
        driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("07-09-2000");
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("test form");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("ABC");
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys("BCN");
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("034989282");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

    }

    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}

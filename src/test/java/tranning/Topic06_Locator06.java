package tranning;

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

import java.util.Random;

public class Topic06_Locator06 {
    WebDriver driver;
    String UserId;
    String PassWord;
    String CustomerName = "Nguyen Thuy Tien";
    String CustomerNameEdit = "Nguyen Thuy Tien Edit";
    String Gender = "female";
    String DateOfBirth = "1998-10-16";
    String Address = "Bui Xuong Trach";
    String City = "Thanh Xuan";
    String State = "Khuong Dinh";
    String PIN = "100000";
    String MobileNumber = "032974" + generateTelephone();
    String Email = generateEmail();
    String PassWordCustomer = "tien123456";


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_CreateANewCustomer() throws InterruptedException {
        driver.get("https://demo.guru99.com/");
        Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        UserId = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
        PassWord = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

        driver.get("https://demo.guru99.com/v4/index.php");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(UserId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PassWord);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@behavior='alternate']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(CustomerName);
        driver.findElement(By.xpath("//input[@value='f']")).click();
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys(DateOfBirth);
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(Address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(City);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(State);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(PIN);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(MobileNumber);
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PassWordCustomer);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='sub']")).click();
        Thread.sleep(2000);


        Assert.assertEquals(driver.findElement(By.xpath("//p[@align='center']")).getText(), "Customer Registered Successfully!!!");

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DateOfBirth);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), MobileNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email);

        String CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
        driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

        WebElement customerNameInput = driver.findElement(By.xpath("//input[@name='name']"));
        js.executeScript("arguments[0].removeAttribute('disabled');", customerNameInput);
        customerNameInput.clear();
        customerNameInput.sendKeys(CustomerNameEdit);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), CustomerNameEdit);

    }
    @Test
//        public void TC02_CreateUser(){
//        driver.get("");
//        Select select = new Select(driver.findElement(By.xpath("")));
//        select.selectByVisibleText("");
//        Assert.assertEquals(select.getFirstSelectedOption(),"");
//        Assert.assertEquals(Select(driver.findElement(By.xpath(""))),"");
//        }



    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    private int generateTelephone() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}


